package com.smxknife.datastructure.msb.newer;

/**
 * 链表反转
 * @author smxknife
 * 2021/6/23
 */
public class _03_LinkNodeReverse {
	public static void main(String[] args) {
		Node head = new Node(1, new Node(2, new Node(3, new Node(4, null))));
		printNode(head);

//		Node node = reverse(head);
		Node node = reverse2(head);
		printNode(node);


		System.out.println("双向链表--------------------");

	}

	private static Node reverse(Node head) {

		if (head == null) {
			return null;
		}

		if (head.next == null) {
			return head;
		}

		Node pre  = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	/*
	1 -》2 -》3 -》4
	head = 1，pre = null, next = null
	1) head = 1
	- next = 2
	- 1 -> null(pre=null)
	- pre = 1
	- head = 2
	 */
	private static Node reverse2(Node head) {
		Node pre = null;
		Node next = null;

		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}

		return pre;
	}

	private static void printNode(Node head) {
		if (head == null) {
			System.out.println("null");
			return;
		}
		while (head != null) {
			System.out.println(head);
			head = head.next;
		}
	}

	public static class Node {
		int val;
		Node next;

		Node(int val, Node node) {
			this.val = val;
			this.next = node;
		}

		@Override
		public String toString() {
			return "Node{" +
					"val=" + val +
					", next=" + next +
					'}';
		}
	}

	public static class Node2 {
		int val;
		Node2 pre;
		Node2 next;


		Node2(int val, Node2 pre, Node2 next) {
			this.val = val;
			this.pre = pre;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node2{" +
					"val=" + val +
					", pre=" + pre +
					", next=" + next +
					'}';
		}
	}
}
