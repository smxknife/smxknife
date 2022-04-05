package com.smxknife.bytebuddy.demo03;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import sun.nio.ch.SelectorImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author smxknife
 * 2020/11/2
 */
public class SelectorThreadTest {
	public static void main(String[] args) {
		ByteBuddyAgent.install();
		Class<? extends SelectorImpl> redefinedClass = new ByteBuddy()
				.redefine(SelectorImpl.class)
				.method(ElementMatchers.named("select").and(ElementMatchers.hasDescriptor("public int select()")))
				.intercept(MethodDelegation.to(SelectorSelectInterceptor.class))
				.make()
				.load(SelectorImpl.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
				.getLoaded();


		server();
	}

	public static void server() {
		try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		    Selector selector = Selector.open()) {
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress("localhost", 6666));

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			SocketChannel socketChannel = null;
			boolean isRun = true;
			while (isRun) {
				System.out.println("before select()");
				selector.select();
				System.out.println("after select()");

				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
				while (selectionKeyIterator.hasNext()) {
					SelectionKey key = selectionKeyIterator.next();
					selectionKeyIterator.remove();

//					if (key.isAcceptable()) {
//						socketChannel = serverSocketChannel.accept();
//						socketChannel.configureBlocking(false);
//						socketChannel.register(selector, SelectionKey.OP_WRITE);
//					}

				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
