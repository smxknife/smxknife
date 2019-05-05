package com.smxknife.algorithm.breadthfirstsearch;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author smxknife
 * 2019-04-30
 */
@Slf4j
public class BreadthFirstSearch {

	public static <T extends Graph> Optional<T> find(Predicate<T> predicate, T start) {
		Deque<T> deque = new ArrayDeque<>();
		if (Objects.isNull(start)) return Optional.empty();
		if (CollectionUtils.isEmpty(start.generations())) return Optional.empty();
		deque.addAll(start.generations());

		while (CollectionUtils.isNotEmpty(deque)) {
			if (log.isInfoEnabled()) log.info("queue = {}, node = {}", deque, start);
			T node = deque.pollFirst();
			if (node.isChecked()) continue;
			if (predicate.test(node)) return Optional.of(node);
			node.setChecked(true);
			if (CollectionUtils.isEmpty(node.generations())) continue;
			deque.addAll(node.generations());
		}
		return Optional.empty();
	}

	public static <T extends Graph> Optional<T> find(Predicate<T> predicate, Deque<T> deque) {
		while (CollectionUtils.isNotEmpty(deque)) {
			T node = deque.pollFirst();
			if (Objects.isNull(node) || node.isChecked()) continue;
			if (predicate.test(node)) return Optional.of(node);
			node.setChecked(true);
			if (CollectionUtils.isEmpty(node.generations())) continue;
			deque.addAll(node.generations());
		}
		return Optional.empty();
	}

	public static <T extends Graph> Optional<T> findRecursive(Predicate<T> predicate, Deque<T> deque) {
		Preconditions.checkNotNull(deque);
		if (CollectionUtils.isEmpty(deque)) return Optional.empty();
		T node = deque.pollFirst();
		if (node.isChecked()) return findRecursive(predicate, deque);
		if (log.isInfoEnabled()) log.info("deque = {}, node = {}", deque, node);

		if (predicate.test(node)) return Optional.of(node);
		if (CollectionUtils.isNotEmpty(node.generations())) deque.addAll(node.generations());
		node.setChecked(true);
		return findRecursive(predicate, deque);
	}

	public static <T extends Graph> List<T> path(Predicate<T> predicate, Deque<T> deque) {
		List<T> path = new ArrayList<>();
		// 思路是看path中最后一个节点是不是循环中节点的父节点，如果不是从path中删除该节点
		while (CollectionUtils.isNotEmpty(deque)) {
			T node = deque.pollFirst();
			if (Objects.isNull(node) || node.isChecked()) continue;

			if (predicate.test(node)) {
				path.add(node);
				return path;
			}
			node.setChecked(true);

			path.add(node);
			if (log.isInfoEnabled()) log.info("add node = {}, path = {}", node, path);
			if (CollectionUtils.isEmpty(node.generations())) {
				path.remove(node);
				if (log.isInfoEnabled()) log.info("remove node = {}, path = {}", node, path);
				continue;
			}
			deque.addAll(node.generations());
		}
		return path;
	}
}
