package com.smxknife.algorithm;

import com.smxknife.algorithm.demo01.dijkstra.Dijkstra;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019-05-05
 */
public class TestDijkstra {

	@Test
	public void pathTest() {


		Map<String, Float> path = Dijkstra.path("start", "end", graph());

	}

	private LinkedHashMap<String, Map<String, Float>> graph() {
		LinkedHashMap<String, Map<String, Float>> graph = new LinkedHashMap<>();
		String start = "start";
		String target = "end";

		LinkedHashMap<String, Float> startMap = new LinkedHashMap<>();
		startMap.put("n1", 5f);
		startMap.put("n2", 2f);
		graph.put(start, startMap);

		LinkedHashMap<String, Float> n1Map = new LinkedHashMap<>();
		n1Map.put("m1", 4f);
		n1Map.put("m2", 2f);
		graph.put("n1", n1Map);

		LinkedHashMap<String, Float> n2Map = new LinkedHashMap<>();
		n2Map.put("n1", 8f);
		n2Map.put("m2", 7f);
		graph.put("n2", n2Map);

		LinkedHashMap<String, Float> m1Map = new LinkedHashMap<>();
		m1Map.put("end", 3f);
		m1Map.put("m2", 6f);
		graph.put("m1", m1Map);

		LinkedHashMap<String, Float> m2Map = new LinkedHashMap<>();
		m2Map.put("end", 1f);
		graph.put("m2", m2Map);

		LinkedHashMap<String, Float> endMap = new LinkedHashMap<>();
		graph.put("end", endMap);

		return graph;
	}
}
