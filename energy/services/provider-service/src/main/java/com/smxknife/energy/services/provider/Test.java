package com.smxknife.energy.services.provider;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/7/9
 */
public class Test {

	public static void main( String[] args) {

		Node head = new Node(0);
		Node node1  = new Node(1);
		head.next = node1;

		Node node2 = new Node(2);
		node1.next = node2;
		Node newHead = reverse(head);

		while (newHead != null) {
			System.out.println(newHead.val);
			newHead = newHead.next;
		}
	}

	public static Node reverse(Node head) {
		if (Objects.isNull(head)) {
			return null;
		}
		if (Objects.isNull(head.next)) {
			return head;
		}
		Node curr = head;
		Node pre = null;
		Node next = null;

		while (curr != null) {
			next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}
		return pre;
	}

}


class Node {
	int val;
	Node next;

	public Node(int val) {
		this.val = val;
	}
}
