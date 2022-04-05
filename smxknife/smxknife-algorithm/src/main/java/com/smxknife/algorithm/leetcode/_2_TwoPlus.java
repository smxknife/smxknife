package com.smxknife.algorithm.leetcode;

import lombok.ToString;

import java.util.Stack;

/**
 * @author smxknife
 * 2021/6/16
 */
public class _2_TwoPlus {
	public static void main(String[] args) {
		final Solution solution = new Solution();
		ListNode l1 = new ListNode(2);
		final ListNode l11 = new ListNode(4);
		l1.next = l11;
		l11.next = new ListNode(3);

		ListNode l2 = new ListNode(5);
		ListNode l21 = new ListNode(6);
		l2.next = l21;
		l21.next = new ListNode(4);
		final ListNode listNode = solution.addTwoNumbers(l1, l2);
		System.out.println(listNode);
	}

	static class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			ListNode t1 = l1;
			ListNode t2 = l2;

			Stack<Integer> stack1 = new Stack();
			Stack<Integer> stack2 = new Stack();

			while( t1 != null || (t2 != null)) {
				if (t1 != null) {
					stack1.push(t1.val);
					t1 = t1.next;
				} else {
					stack1.push(0);
				}
				if (t2 != null) {
					stack2.push(t2.val);
					t2 = t2.next;
				} else {
					stack2.push(0);
				}

			}

			System.out.println(stack1);
			System.out.println(stack2);


			int result = 0;
			final int size = stack1.size();
			for (int i = 0; i < size; i++) {
				final Integer s1 = stack1.pop();
				final Integer s2 = stack2.pop();
				final int pow = (int) Math.pow(10, i);
				result += s1 * pow + s2 * pow;
			}

			ListNode res = new ListNode();
			ListNode node = res;
			STOP:
			while (true) {
				int mod = result % 10;
				result = result / 10;

				node.val = mod;
				node.next = new ListNode();
				node = node.next;

				if (result == 0 && mod != 0) {
					break STOP;
				}

			}

			return res;
		}
	}

	@ToString
	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
