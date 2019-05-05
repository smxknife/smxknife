package com.smxknife.algorithm;

import com.smxknife.algorithm.breadthfirstsearch.BreadthFirstSearch;
import com.smxknife.algorithm.breadthfirstsearch.Relation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author smxknife
 * 2019-04-30
 */
public class TestBreadthFirstSearch {

	@Test
	public void findTest() {
		Optional<Relation> relation = BreadthFirstSearch.find(Relation::isTarget, root());
		Assertions.assertTrue(relation.isPresent() && relation.get().getName().equals("3_4"));
	}

	@Test
	public void find2Test() {
		Deque<Relation> deque = new ArrayDeque<>();
		deque.add(root());
		Optional<Relation> relation = BreadthFirstSearch.find(Relation::isTarget, deque);
		Assertions.assertTrue(relation.isPresent() && relation.get().getName().equals("3_4"));
	}

	@Test
	public void findRecursiveTest() {
		Deque<Relation> deque = new ArrayDeque<>();
		deque.add(root());
		Optional<Relation> relation = BreadthFirstSearch.findRecursive(Relation::isTarget, deque);
		Assertions.assertTrue(relation.isPresent() && relation.get().getName().equals("3_4"));
	}

	@Test
	public void pathTest() {
		Deque<Relation> deque = new ArrayDeque<>();
		deque.add(root());
		List<Relation> path = BreadthFirstSearch.path(Relation::isTarget, deque);
		System.out.println(path);
		List<Relation> expectList = Arrays.asList(new Relation("root"), new Relation("2_2"), new Relation("3_4"));
		Assertions.assertIterableEquals(expectList, path);
	}

	private Relation root() {
		Relation level31 = new Relation();
		level31.setName("3_1");

		Relation level32 = new Relation();
		level32.setName("3_2");

		Relation level33 = new Relation();
		level33.setName("3_3");

		Relation level34 = new Relation();
		level34.setName("3_4");
		level34.setTarget(true);

		Relation level21 = new Relation();
		level21.setName("2_1");
		level32.setFriends(Arrays.asList(level31, level32));

		Relation level22 = new Relation();
		level22.setName("2_2");
		level22.setFriends(Arrays.asList(level31, level33, level34));

		Relation root = new Relation();
		root.setName("root");
		root.setFriends(Arrays.asList(level21, level22));

		return root;
	}
}
