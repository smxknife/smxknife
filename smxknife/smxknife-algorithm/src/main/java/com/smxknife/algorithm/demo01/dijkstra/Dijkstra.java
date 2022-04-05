package com.smxknife.algorithm.demo01.dijkstra;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2019-05-05
 */
@Slf4j
public class Dijkstra {

	public static <T> Map<T, Float> path(T start, T end, final LinkedHashMap<T, Map<T, Float>> graph) {
		Preconditions.checkArgument(Objects.nonNull(graph) || Objects.nonNull(start));
		final Map<T, Float> costs = graph.keySet().stream().collect(Collectors.toMap(k -> k, k -> Float.POSITIVE_INFINITY, (v1, v2) -> v1, LinkedHashMap::new));
		final Map<T, T> parents = new HashMap<>();
		final List<T> processed = new ArrayList<>();

		Map<T, Float> startMap = graph.get(start);
		if (Objects.isNull(startMap) || startMap.size() == 0) return new HashMap<>();
		costs.put(start, 0f);

		T t = minCost(costs, processed);
		while (Objects.nonNull(t)) {
			Float cost = costs.get(t);
			Map<T, Float> neighbours = graph.get(t);
//			if (log.isInfoEnabled()) log.info("node = {}, neighbour = {}", t, neighbours);
			for (Map.Entry<T, Float> entry : neighbours.entrySet()) {
				T key = entry.getKey();
				Float value = entry.getValue();

				float newCost = cost + value;
				if (Objects.nonNull(costs.get(key)) && costs.get(key).compareTo(newCost) > 0) {
					costs.put(key, newCost);
					parents.put(key, t);
				}
			}
			processed.add(t);
			t = minCost(costs, processed);
		}
		System.out.println("costs: " + costs);
		System.out.println("parents: " + parents);

		Map<T, Float> res = new LinkedHashMap<>();
		T node = parents.get(end);
		res.put(end, costs.get(end));
		while (Objects.nonNull(node)) {
			res.put(node, costs.get(node));
			node = parents.get(node);
		}

		System.out.println("path: " + res);
		return res;
	}

	private static <T> T minCost(Map<T, Float> costs, List<T> processed) {
		Optional<Map.Entry<T, Float>> min = costs.entrySet()
				.stream()
				.filter(entry -> entry.getValue().compareTo(Float.POSITIVE_INFINITY) < 0 && !processed.contains(entry.getKey()))
				.findFirst();
		if (min.isPresent()) {
//			if (log.isInfoEnabled()) log.info("min cost node = {}, weight = {}", min.get().getKey(), min.get().getValue());
			return min.get().getKey();
		}
		return null;
	}
}
