package com.smxknife.algorithm.quicksort;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2019-04-29
 */
@Slf4j
public class QuickSort {

	public static <T extends Comparable> List<T> sort(List<T> elements) {
		Preconditions.checkNotNull(elements);
//		List<T> org = new ArrayList<>(elements.size());
//		org.addAll(elements);
//		if (org.size() < 2) return org; // 基线条件
//		T pivot = org.remove(0); // 基准值
		if (elements.size() < 2) return new ArrayList<>(elements);
		T pivot = elements.get(0); // 基准值
		Map<Boolean, List<T>> partition = elements.stream()
				.filter(e -> !e.equals(pivot))
				.collect(Collectors.partitioningBy(t -> t.compareTo(pivot) <= 0));
		List<T> subList = new ArrayList<>(elements.size());
		if (log.isInfoEnabled()) log.info("elements = {}, pivot = {}, lf = {}, gt = {}", elements, pivot, partition.get(true), partition.get(false));
		subList.addAll(sort(partition.get(true))); // 小于基准值递归
		subList.add(pivot);
		subList.addAll(sort(partition.get(false))); // 大于基准值递归
		return subList;
	}
}
