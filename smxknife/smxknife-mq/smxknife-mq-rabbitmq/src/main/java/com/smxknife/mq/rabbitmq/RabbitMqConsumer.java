package com.smxknife.mq.rabbitmq;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/5/15
 */
@Slf4j
public class RabbitMqConsumer extends MqConsumer<RabbitmqBroker> {

	private Connection connection;

	public RabbitMqConsumer(String name, RabbitmqBroker broker, WorkerExecutor workerExecutor) {
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

	private void closeChannel(Channel channel) {
		try {
			channel.close();
		} catch (TimeoutException | IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void subscribe(String destination, List<String> paths, Map<String, Object> arguments, MessageHandler handler) {
		workerExecutor.execute(new ConsumerRunner(connection, destination, paths, broker.getQos(), arguments, handler));
	}

	class ConsumerRunner implements Consumer, Runnable {

		private Connection connection;
		private String exchange;
		private List<String> bindingKeys;
		private int qos;
		private MessageHandler handler;
		private Map<String, Object> arguments;
		private Channel channel;

		public ConsumerRunner(Connection connection, String destination, List<String> paths, int qos, Map<String, Object> arguments, MessageHandler handler) {
			this.connection = connection;
			this.exchange = destination;
			this.bindingKeys = paths;
			this.arguments = arguments;
			this.handler = handler;
			this.qos = qos;
		}

		@Override
		public void handleConsumeOk(String consumerTag) {
			log.info("{} consume ok", consumerTag);
		}

		@Override
		public void handleCancelOk(String consumerTag) {
			log.info("{} cancel ok", consumerTag);
		}

		@Override
		public void handleCancel(String consumerTag) throws IOException {
			log.info("{} handle cancel", consumerTag);
		}

		@Override
		public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
			log.info("{} shutdown reason is {}", consumerTag, sig.getReason());
		}

		@Override
		public void handleRecoverOk(String consumerTag) {
			log.info("{} recover ok", consumerTag);
		}

		@Override
		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
			String data = new String(body);
			String exchange = envelope.getExchange();
			String routingKey = envelope.getRoutingKey();

			Object tokenKey = properties.getHeaders().get(Constant.MESSAGE_HEADER_TOKEN_KEY);
			IdempotenceToken token = new IdempotenceToken();
			if (Objects.nonNull(tokenKey)) {
				getIdempotenceHandler().ifPresent(handler -> token.copy(handler.checkToken(String.valueOf(tokenKey))));
			}
			if (!token.checkSuccess()) {
				log.warn("Skipped!!! {} receive duplicate message from exchange = {}, routingKey = {}, msg = {}", consumerTag, exchange, routingKey, data);
				return;
			}

			log.info("{} receive message from exchange = {}, routingKey = {}, msg = {} ", consumerTag, exchange, routingKey, data);
			handler.handle(exchange, routingKey, data).whenComplete((v, th) -> {
				try {
					if (Objects.isNull(th)) {
						channel.basicAck(envelope.getDeliveryTag(), false);
					} else {
						channel.basicReject(envelope.getDeliveryTag(), false);
						log.warn("{} consume message fail, deliverTag = {}", consumerTag, envelope.getDeliveryTag());
					}
				} catch (IOException e) {
					log.error(e.getMessage());
				}

			});
		}

		@Override
		public void run() {
			try {
				channel = connection.createChannel();
				channel.queueDeclare(name, true, false, false, arguments);

				for (String bindingKey : bindingKeys) {
					channel.queueBind(name, exchange, bindingKey);
					log.info("queue {} binding exchange {} with key {}", name, exchange, bindingKey);
				}

				channel.basicQos(qos);
				channel.basicConsume(name, false, name, this);
			} catch (IOException e) {
				log.error(e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}
}
