package com.smxknife.game.hero.handler.cmd;

import com.smxknife.game.hero.ServerContext;
import com.smxknife.game.hero.handler.MsgWrapper;
import com.smxknife.game.hero.protocol.GameMsgProtocol;
import io.netty.channel.ChannelHandlerContext;
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
public class CmdHandlerRepository {

	private static final String CMD_HANDLER_PROPERTIES_NAME = "cmd.handler.properties";

	private Map<GameMsgProtocol.MsgCode, CmdHandler> handlerMap;
	private ServerContext serverContext;

	public CmdHandlerRepository(ServerContext serverContext) {
		this.serverContext = serverContext;
		handlerMap = new ConcurrentHashMap<>();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(CMD_HANDLER_PROPERTIES_NAME)) {
			final Properties properties = new Properties();
			properties.load(is);
			final String handlerNames = properties.getProperty(CmdHandler.class.getName());
			final StringTokenizer tokenizer = new StringTokenizer(handlerNames, ",");
			while (tokenizer.hasMoreElements()) {
				final String cmdHandlerClassName = tokenizer.nextToken();
				final Class<? extends CmdHandler> cmdHandlerClass = Class.forName(cmdHandlerClassName).asSubclass(CmdHandler.class);
				final CmdHandler cmdHandler = cmdHandlerClass.newInstance();
				final CmdHandler ifAbsent = handlerMap.putIfAbsent(cmdHandler.code(), cmdHandler);
				if (Objects.nonNull(ifAbsent)) {
					throw new Exception(String.format("CmdHandler with code %s is already exist [%s]",
							cmdHandler.code(), ifAbsent.getClass().getName()));
				}
				log.info("load {}", cmdHandlerClassName);
			}
		} catch (IOException e) {
			log.warn("{} is not exist, Cmd message can not be handled!!!");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			log.error(e);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public Optional<CmdHandler> getHandler(GameMsgProtocol.MsgCode msgCode) {
		final Optional<CmdHandler> cmdHandlerOptional = Optional.ofNullable(this.handlerMap.get(msgCode));
		if (!cmdHandlerOptional.isPresent()) {
			log.warn("CmdHandler with code {} is not exist!", msgCode);
		}
		return cmdHandlerOptional;
	}

	public void handle(ChannelHandlerContext ctx, MsgWrapper msgWrapper) {
		this.getHandler(msgWrapper.getMsgCode())
				.ifPresent(handler -> handler.handle(this.serverContext, ctx, msgWrapper.getMessage()));
	}

	public ServerContext getServerContext() {
		return serverContext;
	}
}
