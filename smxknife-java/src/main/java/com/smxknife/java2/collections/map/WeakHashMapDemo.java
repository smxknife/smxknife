package com.smxknife.java2.collections.map;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/10/31
 */
public class WeakHashMapDemo {
	public static void main(String[] args) throws InterruptedException {

		//intKeyTest();
		strKeyTest();

	}

	private static void strKeyTest() throws InterruptedException {
		WeakHashMap<String, Integer> weakHashMap = new WeakHashMap<>();
		weakHashMap.put("1", 1);
		weakHashMap.put("2", 2);
		weakHashMap.put("3", 3);

		for (int i = 0; i < 5; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("特殊的数据除了[-128, 127]外，还存在字符串，为什么字符串会特殊，因为字符串会缓存在常量池中，上面的实验就验证了想法");
		System.out.println("在上面的实验中，采用基本的字符串作为key，经过gc后，元素并没有被回收，继续实验");

		weakHashMap.put(new String("4"), 4);
		weakHashMap.put(new String("5"), 5);
		weakHashMap.put(new String("6"), 6);
		for (int i = 0; i < 5; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("果然，封装成对象后，经过gc后对象被回收了，再实验一下看看");

		weakHashMap.put("1" + "2", 12);
		weakHashMap.put("1" + "3", 13);
		for (int i = 0; i < 5; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("该结果也从侧面验证了，简单的字符串相加的结果与相加之后的结果是相等的");
		System.out.println(("a" + "b").equals("ab"));

		String key20 = "20";
		String key21 = "21";
		weakHashMap.put(key20, 20);
		weakHashMap.put(key21, 21);
		for (int i = 0; i < 5; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("将key定义为普通的字符串变量，结果等同于直接使用简单的字符串，不会被回收");

		weakHashMap.put(key20 + "0", 200);
		weakHashMap.put(key21 + "0", 210);
		String key22 = "22";
		weakHashMap.put(key22 + "", 22);
		for (int i = 0; i < 5; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("虽然直接使用定义好的普通字符串，结果等同于普通字符串，但是如果变量经过运算后，那么就不一样了，这就是另外一个效果，当变量经过运算后，这在编译期是无法做到预测，所以只能在运行期间创建新的对象，这就类似于new String了，所以，经过gc之后是可以回收的");
	}

	private static void intKeyTest() throws InterruptedException {
		WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap<>();

		weakHashMap.put(1, 1);
		weakHashMap.put(2, 2);
		weakHashMap.put(3, 3);
		weakHashMap.put(4, 4);
		weakHashMap.put(5, 5);

		for (int i = 0; i < 10; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("循环了10次，手动调用了5次gc，但是结果应该是没有被回收掉，继续做下面的实验");

		weakHashMap.put(new Integer(10), 10);
		weakHashMap.put(new Integer(11), 11);
		weakHashMap.put(new Integer(12), 12);
		weakHashMap.put(new Integer(13), 13);
		weakHashMap.put(new Integer(14), 14);

		for (int i = 0; i < 10; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("有意思了，这第二批key>10的，在经过第一次gc之后，全部被回收掉了，剩下的还是第一批的，只是因为key不是基本类型，而是基本类型的封装类型，继续实验");

		weakHashMap.put(128, 128);
		weakHashMap.put(129, 129);
		weakHashMap.put(130, 130);
		weakHashMap.put(131, 131);
		weakHashMap.put(132, 132);

		for (int i = 0; i < 10; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}

		System.out.println("再次出现有意思的事件了，这次的数据key同样是基本的数据类型，只不过是key大于127的存在，那么这次，在经过第一次gc之后依然回收了，这跟-128~127，这几个特殊数字有关应该，这256个数字在Integer中缓存好了，所以永远存在引用");
		System.out.println("简单验证一下，几个key，-129，-128，127，128");

		weakHashMap.put(-128, -128);
		weakHashMap.put(-129, -129);
		weakHashMap.put(127, 127);
		weakHashMap.put(128, 128);

		for (int i = 0; i < 10; i++) {
			System.out.println("i = " + i + " - " + weakHashMap);
			TimeUnit.MILLISECONDS.sleep(500);
			if (i % 2 == 0) {
				System.gc();
			}
		}
		System.out.println("经过验证上面的猜想是对的，-128到127之间的key是不会被回收的，但是如果将这些key封装成对象，那么还是可以顺利回收");

	}
}
