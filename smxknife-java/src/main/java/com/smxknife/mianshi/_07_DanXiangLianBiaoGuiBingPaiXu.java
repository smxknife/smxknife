package com.smxknife.mianshi;

import java.util.Objects;

/**
 * 单向链表归并排序
 * 由于链表（LinkedList）不支持随机访问（Random Access），只允许顺序访问，
 * 因此对于链表的O(logn)时间复杂度的排序算法不可以采用诸如快速排序等基于随机访问的排序算法，而归并排序可以满足这一需求
 *
 * 分治思想
 * 1. 采用快慢指针找到中间元算
 * 2. 先排左表，再排右表
 * 3. 合并
 * @author smxknife
 * 2021/6/2
 */
public class _07_DanXiangLianBiaoGuiBingPaiXu {
	public static void main(String[] args) {
		Node head = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(4);
		Node node4 = new Node(3);
		head.next = node2;
		node2.next = node3;
		node3.next = node4;

		Node sortN = sort(head);
		for (;sortN != null; sortN = sortN.next) {
			System.out.println(sortN.value);
		}
	}

	private static Node sort(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		final Node middle = getMiddle(head);
		final Node right = middle.next;
		middle.next = null;
		return mergeSort(sort(head), sort(right));
	}

	/**
	 * @param leftP
	 * @param rightP
	 * @return
	 */
	private static Node mergeSort(Node leftP, Node rightP) {
		Node subHead = new Node(0);
		Node curr = subHead;

		Node left = leftP;
		Node right = rightP;

		while (left != null && right != null) {
			final int lv = left.value;
			final int rv = right.value;

			Node min = null;
			if (lv < rv) {
				min = left;
				left = left.next;
			} else {
				min = right;
				right = right.next;
			}
			min.next = null;

			curr.next = min;
			curr = min;
		}
		curr.next = (left == null) ? right : left;

		return subHead.next;
	}

	private static Node getMiddle(Node head) {
		if (Objects.isNull(head)) {
			return null;
		}

		Node slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;

	}

	private static class Node {
		int value;
		Node next;

		public Node(int value) {
			this.value = value;
		}
	}
}
