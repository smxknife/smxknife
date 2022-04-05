package com.smxknife.java2.collections.set;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author smxknife
 * 2019/9/1
 */
public class TreeSetDemo {
	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<>();
		// region 模拟数据内容，可忽略不看
		set.add(2);
		set.add(1);
		set.add(10);
		set.add(9);
		set.add(4);
		set.add(3);
		set.add(3);
		set.add(8);
		set.add(3);
		set.add(7);
		// endregion

		System.out.println(set);
		// 从输出结构来看，TreeSet保证了元素的从大到小的顺序输出
		/**
		 * 1）有序
		 * 2）不重复
		 */
		// 继承了AbstractSet，实现了 NavigableSet，而 NavigableSet实现了SortedSet
		// 底层采用了NavigableMap作为存储结构
		// Set接口的底层存储Map都采用了transient关键字修饰
		// 提供了逆序输出元素的方法

		// 输出为7，ceiling(E e)的作用是返回集合中 大于等于 6的最小元素，
		// 集合元素为[1, 2, 3, 4, 7, 8, 9, 10]
		Integer ceiling = set.ceiling(6);
		System.out.println(ceiling);

		Integer ceilingEq = set.ceiling(6);
		System.out.println(ceilingEq);

		// 输出为4，floor(E e)为ceiling的反方法，作用是返回集合中 小于等于 给定的最小元素
		Integer floor = set.floor(6);
		System.out.println(floor);

		Integer floorEq = set.floor(4);
		System.out.println(floorEq);
		// 需要注意的是无论是ceiling还是floor，给定的元素如果在集合中，那么就输出该元素，两个方法都是包含方法

		// 与ceiling相对的一个方法，ceiling是可以包含给定元素，而higher方法就是返回大于给定的第一个元素
		Integer higherEq = set.higher(4);
		System.out.println(higherEq); // 输出结果为7
		Integer higher = set.higher(6);
		System.out.println(higher); // 输出结果为7
		System.out.println(set.higher(11)); // 输出null

		// 与higher相对，返回比给定元素小的第一个元素，不包含，如果包含相当于floor方法了
		Integer lowerEq = set.lower(4);
		System.out.println(lowerEq); // 输出为3
		Integer lower = set.lower(6);
		System.out.println(lower); // 输出为4

		// 逆序输出
		// descendingIterator就是逆序迭代器
		Iterator<Integer> descendingIterator = set.descendingIterator();
		while (descendingIterator.hasNext()) {
			System.out.print(descendingIterator.next() + " ");
		}
		System.out.println();

		// first方法，返回有序集合中第一个元素
		Integer first = set.first();
		System.out.println(first);

		// last方法，返回有序集合中最后一个元素
		Integer last = set.last();
		System.out.println(last);

		// 集合为[1, 2, 3, 4, 7, 8, 9, 10]
		// headSet(E e) 返回集合中小于给定元素的子集合，注意即使相等也不包含
		// 如果想要包含给定元素，采用另外一个方法headSet(E e, boolean inclusive) inclusive设置为true即可
		SortedSet<Integer> headSetEq = set.headSet(4);
		System.out.println(headSetEq); // 输出[1, 2, 3]
		SortedSet<Integer> headSet = set.headSet(6);
		System.out.println(headSet); // 输出[1, 2, 3, 4]
		NavigableSet<Integer> integers = set.headSet(4, true);
		System.out.println(integers); // 输出[1, 2, 3, 4]

		// tailSet(E e)返回集合中大于等于给定元素的子集合，注意这个是包含
		// 如果想要不包含，采用tailSet(E e, boolean inclusive) 将inclusive设置为false
		SortedSet<Integer> tailSetEq = set.tailSet(4);
		System.out.println(tailSetEq); // 输出[4, 7, 8, 9, 10]
		SortedSet<Integer> tailSet = set.tailSet(6);
		System.out.println(tailSet); // 输出[7, 8, 9, 10]
		NavigableSet<Integer> integers1 = set.tailSet(4, false);
		System.out.println(integers1); // 输出[7, 8, 9, 10]

		// poll系列就是从集合中弹出元素，pollFirst弹出第一个，并从集合中删除第一个
		// pollLast弹出最后一个，并从集合中删除最后一个
		Integer pollFirst = set.pollFirst();
		System.out.println(pollFirst); // 输出1
		System.out.println(set); // 输出[2, 3, 4, 7, 8, 9, 10]
		Integer pollLast = set.pollLast();
		System.out.println(pollLast); // 输出10
		System.out.println(set); // 输出[2, 3, 4, 7, 8, 9]

	}
}
