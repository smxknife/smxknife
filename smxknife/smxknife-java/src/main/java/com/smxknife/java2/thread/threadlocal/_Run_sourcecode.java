package com.smxknife.java2.thread.threadlocal;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_sourcecode {
	public static void main(String[] args) {

		// 看完了ThreadLocal的一些例子之后，开始看源码吧

		/**
		 * 拥有的属性如下：
		 * private final int threadLocalHashCode = nextHashCode();
		 * private static AtomicInteger nextHashCode =
		 *         new AtomicInteger();
		 * private static final int HASH_INCREMENT = 0x61c88647;
		 */

		/**
		 * 公共方法如下
		 * public T get()
		 * public void set(T value)
		 * public void remove()
		 */

		// 从构造方法来看，没有做任何处理
		ThreadLocal<Object> local = new ThreadLocal() {
			@Override
			protected Object initialValue() {
				return "test";
			}
		};
		// initialValue方法，是一个protected方法，这里用来设置初始值，看源码还是...挺随意的，哈哈，直接返回null
		/**
		 * protected T initialValue() {
		 *         return null;
		 *     }
		 */
		// 我们通过重写这个方法来获取默认值

		// get方法
		Object o = local.get();
		/**
		 * public T get() {
		 *         Thread t = Thread.currentThread();
		 *         ThreadLocalMap map = getMap(t);
		 *         if (map != null) {
		 *             ThreadLocalMap.Entry e = map.getEntry(this);
		 *             if (e != null) {
		 *                 @SuppressWarnings("unchecked")
		 *                 T result = (T)e.value;
		 *                 return result;
		 *             }
		 *         }
		 *         return setInitialValue();
		 *     }
		 *
		 * private T setInitialValue() {
		 *         T value = initialValue();
		 *         Thread t = Thread.currentThread();
		 *         ThreadLocalMap map = getMap(t);
		 *         if (map != null)
		 *             map.set(this, value);
		 *         else
		 *             createMap(t, value);
		 *         return value;
		 *     }
		 */
		// get方法的源码也很直观，获取当前线程，去一个ThreadLocalMap中获取key为当前线程的数据
		// 如果当前线程的key的Map不存在，直接返回setInitialValue，该源码也比较简单，
		//      先获取默认值，就是再从ThreadLocalMap中获取一次key为当前线程的值
		//      如果不存在呢，就把创建一个Map，把key=当前线程，value=默认值设置到创建的ThreadLocalMap中
		//      如果存在，就把获取的map重新赋值为初始值
		// 如果当前线程的key的Map存在，就获取该Map对象的Entry对象并获取value值，返回

		// 现在看看getMap(t)是一个什么方法
		/**
		 * ThreadLocalMap getMap(Thread t) {
		 *         return t.threadLocals;
		 *     }
		 */
		// 竟然是线程的threadLocals属性，在Thread类里面的threadLocals属性里面存储的，怪不得不会线程共享

		// get方法的核心结构为ThreadLocalMap结构，看看这是个什么
		// 看过源码后知道，ThreadLocalMap不是我想象中是Map的子类或者什么，而是ThreadLocal的一个内部类
		// 而ThreadLocalMap中又定义一个内部类Entry竟然继承了WeakReference
		// ThreadLocalMap中采用Entry[]数组的方式存储ThreadLocal，而不是线程
		// ThreadLocalMap虽然不是Map，但是内部结构与Map极为相似，但是与HashMap还是有一定的区别的，最外层采用的都是数组结构
		// 但是在解决hash碰撞时策略是不一样的，HashMap采用的是链表结构，而ThreadLocalMap采用的是线性探测的方式
		// 具体的就是在初始hash的基础上发现hash位置已经有元素了，利用简单步长加减1的方式，寻找下一个临近的位置，如下
		/**
		 * private static int nextIndex(int i, int len) {
		 *             return ((i + 1 < len) ? i + 1 : 0);
		 *         }
		 *
		 * private static int prevIndex(int i, int len) {
		 *             return ((i - 1 >= 0) ? i - 1 : len - 1);
		 *         }
		 */
		// 这种方式解决hash冲突的效率很低，如果有大量不同的ThreadLocal对象放入Map中，冲突的概率相当高，效率非常差

		// TODO 所以在这里建议，每个线程最好只存一个变量，这样的话所有线程存放在Map中的key都是相同的ThreadLocal，如果
		// TODO 一个线程要存储多个变量，就需要创建多个ThreadLocal，那么就会增加hash冲突的概率

		local.set("hh");
		// set方法就比较简单，与setInitialValue基本相似


	}
}
