package com.smxknife.game.hero.handler.result;

import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2021/5/7
 */
@Log4j2
public class ResultHandlerRepository {

	private static final String RESULT_HANDLER_PROPERTIES_NAME = "result.handler.properties";

	private Map<GameMsgProtocol.MsgCode, ResultHandler> handlers;
	private ServerContext serverContext;

	public ResultHandlerRepository(ServerContext serverContext) {
		this.serverContext = serverContext;
		handlers = new ConcurrentHashMap<>();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(RESULT_HANDLER_PROPERTIES_NAME)) {
			final Properties properties = new Properties();
			properties.load(is);
			final String handlerNames = properties.getProperty(ResultHandler.class.getName());
			final StringTokenizer tokenizer = new StringTokenizer(handlerNames, ",");
			while (tokenizer.hasMoreElements()) {
				final String resultHandlerClassName = tokenizer.nextToken();
				final Class<? extends ResultHandler> resultHandlerClass = Class.forName(resultHandlerClassName).asSubclass(ResultHandler.class);
				final ResultHandler resultHandler = resultHandlerClass.newInstance();
				final ResultHandler ifAbsent = handlers.putIfAbsent(resultHandler.code(), resultHandler);
				if (Objects.nonNull(ifAbsent)) {
					throw new Exception(String.format("ResultHandler with code %s is already exist [%s]",
							resultHandler.code(), ifAbsent.getClass().getName()));
				}
				log.info("load {}", resultHandlerClassName);
			}
		} catch (IOException e) {
			log.warn("{} is not exist, Result message can not be handled!!!");
		} catch (ClassNotFoundException |InstantiationException | IllegalAccessException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public Optional<ResultHandler> getHandler(GameMsgProtocol.MsgCode msgCode) {
		final Optional<ResultHandler> handlerOptional = Optional.ofNullable(this.handlers.get(msgCode));
		if (!handlerOptional.isPresent()) {
			log.warn("ResultHandler with code {} is not exist!", msgCode);
		}
		return handlerOptional;
	}


	public ServerContext getServerContext() {
		return serverContext;
	}
}
