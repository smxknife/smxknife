package com.smxknife.mq.rabbitmq;

import com.rabbitmq.client.*;
import com.smxknife.mq.api.*;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/5/15
 */
@Slf4j
public class RabbitMqProducer extends MqProducer<RabbitmqBroker> {

	private Connection connection;

	public RabbitMqProducer(RabbitmqBroker broker, WorkerExecutor workerExecutor) {
		super("default", broker, workerExecutor);
	}

	public RabbitMqProducer(String name, RabbitmqBroker broker, WorkerExecutor workerExecutor) {
		super(name, broker, workerExecutor);
	}

	@Override
	protected void init() throws Exception {
		ConnectionFactory connectionFactory = broker.getConnectionFactory();
		connection = connectionFactory.newConnection();
		connection.addShutdownListener(new ShutdownListener() {
			@Override
			public void shutdownCompleted(ShutdownSignalException cause) {
				String hardError = "";
				String appInit = "";
				if (cause.isHardError()) {
					hardError = "connection";
				} else {
					hardError = "channel";
				}

				if (cause.isInitiatedByApplication()) {
					appInit = "application";
				} else {
					appInit = "broker";
				}
				log.error("Connectivity to server failed! It was caused by {} at the {} level. Reason received {}", appInit, hardError, cause.getReason());
			}
		});
		((Recoverable)connection).addRecoveryListener(new RecoveryListener() {
			@Override
			public void handleRecovery(Recoverable recoverable) {
				if( recoverable instanceof Connection ) {
					log.info("Connection was recovered.");
				} else if ( recoverable instanceof Channel ) {
					int channelNumber = ((Channel) recoverable).getChannelNumber();
					log.info( "Connection to channel #{} was recovered.", channelNumber);
				}
			}

			@Override
			public void handleRecoveryStarted(Recoverable recoverable) {
			}
		});
		String sig = System.getProperty("os.name").toLowerCase().startsWith("win") ? "INT" : "TERM";
		Signal signal = new Signal(sig);
		Signal.handle(signal, new SignalHandler() {
			@Override
			public void handle(Signal signal) {
				System.exit(0);
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				connection.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}, "rabbitmq_shutdown_hook_thread"));
	}

	@Override
	public void doPublish(String destination, String path, String message, Map<String, Object> arguments, IdempotenceToken token) {
		workerExecutor.execute(() -> {
			try {
				Channel channel = connection.createChannel();
				channel.exchangeDeclare(destination, "topic", true, false, arguments);
				final Optional<ProducerListener> producerListener = getProducerListener();
				final Optional<RetryHandler> retryHandler = getRetryHandler();
				Observable.create(new ObservableOnSubscribe<Integer>() {
					@Override
					public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
						retryHandler.ifPresent(handler -> {
							boolean enableRetry = handler.enableRetry();
							int maxAttempts = handler.maxAttempts();
							AtomicInteger retryNum = new AtomicInteger(enableRetry ? maxAttempts : 0);

							channel.addReturnListener(new ReturnListener() {
								@Override
								public void handleReturn(int replyCode, String replyText, String exchange,
								                         String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
									log.warn("没有找到消费者 exchange = {} | routingKey = {} | replyText = {} | data = {}", exchange, routingKey, replyText, new String(body));
									producerListener.ifPresent(listener -> listener.publishReturn(body));
									emitter.onError(new RuntimeException());
								}
							});

							if (handler.enableConfirm()) {
								try {
									channel.confirmSelect();
								} catch (IOException e) {
									log.error(e.getMessage(), e);
								}
								channel.addConfirmListener(new ConfirmListener() {
									@Override
									public void handleAck(long deliveryTag, boolean multiple) throws IOException {
										log.info("exchange {} 数据发送成功", destination);
										producerListener.ifPresent(listener -> listener.publishSuccess(deliveryTag, multiple));
										emitter.onComplete();
									}

									@Override
									public void handleNack(long deliveryTag, boolean multiple) throws IOException {
										log.warn("exchange {} 数据发送失败, deliverTag={}, multiple={}", destination, deliveryTag, multiple);
										producerListener.ifPresent(listener -> listener.publishFail(deliveryTag, multiple));
										emitter.onNext(retryNum.incrementAndGet());
									}
								});
							}
						});

						doPublish(channel, destination, path, message, token);
					}
				}).observeOn(Schedulers.single()).subscribe(new Observer<Integer>() {
					@Override
					public void onSubscribe(Disposable disposable) {
					}

					@Override
					public void onNext(Integer integer) {
						retryHandler.ifPresent(handler -> {
							boolean enableRetry = handler.enableRetry();
							int maxAttempts = handler.maxAttempts();
							AtomicInteger retryNum = new AtomicInteger(enableRetry ? maxAttempts : 0);

							if (enableRetry && integer < maxAttempts) {
								try {
									TimeUnit.MILLISECONDS.sleep(handler.retryInterval());
									doPublish(channel, destination, path, message, token);
								} catch (InterruptedException e) {
									log.error(e.getMessage(), e);
								}
							} else {
								getIdempotenceHandler().ifPresent(idempotenceHandler -> idempotenceHandler.deleteToken(token.getKey()));
							}
						});
					}

					@Override
					public void onError(Throwable throwable) {
						closeChannel(channel);
						getIdempotenceHandler().ifPresent(handler -> handler.deleteToken(token.getKey()));
					}

					@Override
					public void onComplete() {
						closeChannel(channel);
					}
				});

			} catch (IOException e) {
				if (log.isDebugEnabled()) {
					log.error(e.getMessage(), e);
				} else {
					log.error(e.getMessage());
				}
			}
		});
	}

	private void doPublish(Channel channel, String exchange, String routingKey, String data, IdempotenceToken token) {
		try {
			Map<String, Object> headers = new HashMap<>();
			if (token.isEnable()) {
				headers.put(Constant.MESSAGE_HEADER_TOKEN_KEY, token.getToken());
			}
			AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
			builder.headers(headers);
			builder.deliveryMode(2);
			builder.contentType("text/plain");

			channel.basicPublish(exchange, routingKey, true, builder.build(),
					data.getBytes());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			getProducerListener().ifPresent(listener -> listener.unexpectException(e));
		}
	}

	private void closeChannel(Channel channel) {
		try {
			channel.close();
		} catch (TimeoutException | IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
