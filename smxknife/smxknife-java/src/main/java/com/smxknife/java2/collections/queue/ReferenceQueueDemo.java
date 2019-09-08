package com.smxknife.java2.collections.queue;

import java.lang.ref.ReferenceQueue;

/**
 * @author smxknife
 * 2019/9/6
 */
public class ReferenceQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		ReferenceQueue<String> queue = new ReferenceQueue<>();

		queue.poll();
		queue.remove();
		queue.remove(1000);

		// ReferenceQueue只有这三个公共方法，都是删除队列中元素的操作，没有入队操作
		// 但是看源码是有enqueue方法的，但是权限却是默认包权限，外面无法直接访问
		// 既然是包访问权限，那么看看该包下都有什么吧，Finalizer、FinalReference、PhantomReference、Reference、SoftReference、WeakReference
		// 明了了，该队列就是为了引用服务的，而且enqueue方法入队的对象也是Reference

		// enqueue的源码也比较有意思，
		/**
		 * ReferenceQueue<?> queue = r.queue;
		 *             if ((queue == NULL) || (queue == ENQUEUED)) {
		 *                 return false;
		 *             }
		 *             assert queue == this;
		 */
		// r是参数中的Reference对象，该对象中包含一个ReferenceQueue
		// 所以，ReferenceQueue假设可以入队，但是如果这个队列不是Reference中的那个队列，也是入不进去的

		/**
		 * r.queue = ENQUEUED;
		 *             r.next = (head == null) ? r : head;
		 *             head = r;
		 */
		// 另外，看上面这一段代码，这段代码的入队操作竟然是入在head的位置....赶紧看一下出队操作是什么操作
		/**
		 * Reference<? extends T> r = head;
		 *         if (r != null) {
		 *             head = (r.next == r) ?
		 *                 null :
		 *                 r.next; // Unchecked due to the next field having a raw type in Reference
		 *             r.queue = NULL;
		 *             r.next = r;
		 *             queueLength--;
		 *             if (r instanceof FinalReference) {
		 *                 sun.misc.VM.addFinalRefCount(-1);
		 *             }
		 *             return r;
		 *         }
		 *         return null;
		 */
		// 真是一个神奇的操作，出队也是先出head，那么这个ReferenceQueue，直接就是一个先入后出操作，
		// 你敢信吗！！！！这不是一个栈的结构起了一个队列的名字！！！
		//
	}
}
