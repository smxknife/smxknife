package com.smxknife.java2.collections.queue;

import java.util.PriorityQueue;

/**
 * @author smxknife
 * 2019/9/1
 */
public class PriorityQueueDemo {
	public static void main(String[] args) {
		// PriorityQueue是一个juc工具类
		// 优先队列的作用是能保证每次取出的元素都是队列中权值最小的
		PriorityQueue<String> queue = new PriorityQueue<>();

		queue.add("a");
		queue.add("dd");
		queue.add("b");

		System.out.println(queue); // 输出为[a, dd, b]

		/**
		 * 1）DEFAULT_INITIAL_CAPACITY = 11
		 * 2）transient Object[] queue
		 */
		// 从上面的两个属性来看，如果没有指定容量，默认为11，
		// 底层采用的存储结构是数组
		// 增加元素采用了两个方法add和offer，但是add就是调用了offer，所以，两个方法作用相同，异常处理不同而已

		queue.add("c");
		queue.offer("cc");
		System.out.println(queue);
		// 从这里可以看出输出序列变化了，乍看无规律，看源码
		/**
		 * 这里k，是queue本次添加之前的size大小，上面的例子中就是3，x就是要添加的元素
		 * private void siftUpComparable(int k, E x) {
		 *         Comparable<? super E> key = (Comparable<? super E>) x;
		 *         while (k > 0) {
		 *              // 这一句就是寻找父节点，父节点就是索引的二分之一处，
		 *              // 下面的判断就是，如果要添加的元素比父元素大，那么直接添加就可以，退出循环
		 *              // 如果比父元素小，那么就将父元素与该元素互换位置，k等于父元素的位置
		 *              // 循环上面直至k的父元素小于x，将x放置在k的位置
		 *             int parent = (k - 1) >>> 1;
		 *             Object e = queue[parent];
		 *             if (key.compareTo((E) e) >= 0)
		 *                 break;
		 *             queue[k] = e;
		 *             k = parent;
		 *         }
		 *         queue[k] = key;
		 *     }
		 */

		System.out.println("peek-----------");
		System.out.println("// peek就是获取全局最小的那个元素，不删除，如果不存在返回null");
		// peek就是获取全局最小的那个元素，不删除，如果不存在返回null
		System.out.println(new PriorityQueue<>().peek());
		String peek = queue.peek();
		System.out.println(peek);
		System.out.println(queue);

		System.out.println("element ------------");
		System.out.println("// element 与peek类似也是获取全局权重最小的元素，不删除，但是如果不存在的情况就会抛出异常，这与peek有所不同");
		// element 与peek类似也是获取全局权重最小的元素，不删除，但是如果不存在的情况就会抛出异常，这与peek有所不同
		String element = queue.element();
		System.out.println(element);
		System.out.println(queue);
//		System.out.println(new PriorityQueue<>().element()); 这句运行会报错，为了不影响运行，注释掉

		System.out.println("poll --------------");
		// poll弹出队列中最小的元素，如果队列中没有元素，弹出null
		System.out.println("// poll弹出队列中最小的元素，如果队列中没有元素，弹出null");
		String poll = queue.poll();
		System.out.println(poll);
		System.out.println(queue);
		System.out.println(new PriorityQueue<>().poll());

		while (queue.size() > 0) {
			System.out.print(queue.poll() + " ");
		}
		System.out.println();
		System.out.println(queue);
		// 从上面的输出结果看，queue并不是一成不变的，而是每弹出一个元素都会进行重新排列
		// 下面是核心代码 k = 0，x = 最后一个元素
		/**
		 * private void siftDownComparable(int k, E x) {
		 *         Comparable<? super E> key = (Comparable<? super E>)x;
		 *         int half = size >>> 1;        // loop while a non-leaf
		 *         while (k < half) {
		 *             int child = (k << 1) + 1; // assume left child is least
		 *             Object c = queue[child];
		 *             int right = child + 1;
		 *             if (right < size &&
		 *                 ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
		 *                 c = queue[child = right];
		 *             if (key.compareTo((E) c) <= 0)
		 *                 break;
		 *             queue[k] = c;
		 *             k = child;
		 *         }
		 *         queue[k] = key;
		 *     }
		 */
	}
}
