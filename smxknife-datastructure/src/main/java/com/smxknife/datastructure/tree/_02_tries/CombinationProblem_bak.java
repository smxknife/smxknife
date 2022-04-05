package com.smxknife.datastructure.tree._02_tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 穷尽集合之间的元素组合。
 * input: list of list
 *      [ [‘a’, ‘b’, ‘c’], [‘1’, ‘2’, ‘3’, ‘4’, ‘5’, ‘6’] , [‘A’, ‘B’, ‘C’, ‘D’] ]
 * output:
 *      [
 *      	[‘a’, ‘1’, ‘A’],
 *      	[‘a’, ‘1’, ‘B’],
 *      	[‘a’, ‘1’, ‘C’],
 *      	[‘a’, ‘1’, ‘D’],
 *      	...
 *      	[‘c’, ‘6’, ‘D’]
 *  ]
 * 实现下面的方法, 要求不要用递归的方法。
 * @author smxknife
 * 2021/6/27
 */
public class CombinationProblem_bak {

	public ArrayList<List<String>> getSetCombination(ArrayList<List<String>> input) {
		ArrayList<List<String>> lists = new ArrayList<>();

		// 总大小
		int size = 1;
		// input中每个集合有多少元素
		for (int i = 0; i < input.size(); i++) {
			size *= input.get(i).size();
		}


		for (int i = 0; i < size; i++) {
			// a * b * c = size;
			// 3 * 3 * 4 = 36
			//
			// i = 0: input.get(0).get(0) , input.get(1).get(0), inout.get(2).get(0)
			// i = 1: input.get(0).get(0) , input.get(1).get(0), inout.get(2).get(1)
			// i = 2: input.get(0).get(0) , input.get(1).get(0), inout.get(2).get(2)
			// i = 3: input.get(0).get(0) , input.get(1).get(0), inout.get(2).get(3)
			//   ---------
			// i = 4: input.get(0).get(0) , input.get(1).get(1), inout.get(2).get(0)
			// i = 5: input.get(0).get(0) , input.get(1).get(1), inout.get(2).get(1)
			// i = 6: input.get(0).get(0) , input.get(1).get(1), inout.get(2).get(2)
			// i = 7: input.get(0).get(0) , input.get(1).get(1), inout.get(2).get(3)

			// i = 8: input.get(0).get(0) , input.get(1).get(2), inout.get(2).get(0)
			// i = 9: input.get(0).get(0) , input.get(1).get(2), inout.get(2).get(1)
			// i = 10: input.get(0).get(0) , input.get(1).get(2), inout.get(2).get(2)
			// i = 11: input.get(0).get(0) , input.get(1).get(2), inout.get(2).get(3)

			// i = 12: input.get(0).get(1) , input.get(1).get(0), inout.get(2).get(0)

			String[] eachList = new String[input.size()];
			int div = i;
			for (int j = input.size() - 1; j >= 0; j--) {
				final List<String> list = input.get(j);

				int eachSize = list.size();

				int mod = div % eachSize;
				div = div / eachSize;

				eachList[j] = list.get(mod);
			}
			lists.add(Arrays.stream(eachList).collect(Collectors.toList()));
		}

		return lists ;
	}
	public static void main(String [] args) {
		//TODO some test case
		CombinationProblem_bak c = new CombinationProblem_bak();

		ArrayList<List<String>> list = new ArrayList<>();
		list.add(Arrays.asList("a", "b", "c"));
		list.add(Arrays.asList("1", "2", "3", "4", "5", "6"));
		list.add(Arrays.asList("A", "B", "C", "D"));

		System.out.println(c.getSetCombination(list));;
	}
}
