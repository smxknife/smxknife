package com.smxknife.java2.nio.demo01;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2019/9/10
 */
@AllArgsConstructor
public class SelectorTask implements Runnable {

	private Nio nio;

	@Override
	public void run() {
		Selector selector = nio.getSelector();

		SelectionKeyHandler selectionKeyHandler = nio.getSelectionKeyHandler();
		Objects.requireNonNull(selectionKeyHandler);

		while (!nio.isStop()) {
			try {
				int select = selector.select(nio.getSelectTimeout());
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				if (select > 0) {
					String keys = selectionKeys.stream().map(SelectionKey::interestOps).map(String::valueOf).collect(Collectors.joining(", "));
					System.out.println("Selector select = " + select + ", keysOps = " + keys + " | " + Thread.currentThread().getName());
				}
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					iterator.remove();
					try {
						selectionKeyHandler.handle(key, nio);
					} catch (Exception e) {
						System.out.println("SelectionKey handle exception | error : " + e.getMessage());
						selectionKeyCancel(key);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (selector != null) {
			try {
				selector.selectedKeys().forEach(key -> {
					selectionKeyCancel(key);
				});
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void selectionKeyCancel(SelectionKey key) {
		try {
			if (Objects.nonNull(key)) {
				if (Objects.nonNull(key.channel())) {
					key.channel().close();
				}
				key.cancel();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
