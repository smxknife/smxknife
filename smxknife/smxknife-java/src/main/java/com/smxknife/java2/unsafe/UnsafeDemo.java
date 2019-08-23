package com.smxknife.java2.unsafe;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2019-08-20
 */
public class UnsafeDemo {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//		Unsafe unsafe = Unsafe.getUnsafe(); // 运行时会报错，只能是引导类加载器才能使用
		Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) theUnsafeField.get(null);
		System.out.println(unsafe);

		// allocateInstance方法，不调用构造方法生成对象
		allocateInstance(unsafe);

		// objectFieldOffset方法，返回成员属性在内存中的地址相对于对象内存地址的偏移量
		objectFieldOffset(unsafe);

		// copyMemory 内存数据拷贝
//		copyMemory(unsafe);

		// 直接分配内存
		allocateMemory(unsafe);

		// defineClass
		defineClass(unsafe);

		// wrapperException 不希望捕获受检查异常
		// wrapperUncheckException(unsafe);

		// 堆外内存创建大数组
		hugeArrayCreate(unsafe);

		// cas
		casCounter(unsafe);
	}

	private static void casCounter(Unsafe unsafe) {
		CASCounter casCounter = new CASCounter(unsafe);

		ExecutorService service = Executors.newFixedThreadPool(3);

		IntStream.iterate(0, i -> i + 1).limit(5).boxed().map(idx -> new Runnable() {
			@Override
			public void run() {
				long increment = casCounter.increment();
				System.out.println(Thread.currentThread().getName() + " | increment = " + increment);
				System.out.println(Thread.currentThread().getName() + " | finish");
			}
		}).forEach(service::execute);
		service.shutdown();
	}

	private static void hugeArrayCreate(Unsafe unsafe) {
		System.out.println("hugeArrayCreate test ---------------");
		HugeArray hugeArray = null;
		System.out.println("init: " + hugeArray);
		try {
			hugeArray = new HugeArray(100, unsafe);
			System.out.println("allocate: " + hugeArray);
		} catch (FinalException e) {

		} finally {
			hugeArray.release();
			System.out.println("release: " + hugeArray);
		}
	}

	private static void wrapperUncheckException(Unsafe unsafe) {
		System.out.println("wrapperUncheckException test -----------");
		unsafe.throwException(new UndefineException("xxxxx"));
		System.out.println("------------------------------");
	}

	private static void defineClass(Unsafe unsafe) {
		System.out.println("defineClass test ----------------");
		File classFile = new File("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes/com/smxknife/java2/beans/BeanDescriptorDemo.class");
		try (FileInputStream fileInputStream = new FileInputStream(classFile)) {
			byte[] content = new byte[(int) classFile.length()];
			fileInputStream.read(content);

			Class<?> defineClass = unsafe.defineClass(null, content, 0, content.length, UnsafeDemo.class.getClassLoader(), null);
			System.out.println("defineClass: " + defineClass.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------");
	}

	private static void allocateMemory(Unsafe unsafe) {
		System.out.println("allocateMemory test --------------");
		// TODO 这里存在疑问，一直没找到解答，这里分配了1字节的内存，为什么下面可以存储4字节的int数据
		long memoryAddress = unsafe.allocateMemory(1);
		System.out.println(memoryAddress);
		unsafe.putAddress(memoryAddress, Integer.MAX_VALUE);
		long memoryVal = unsafe.getInt(memoryAddress);
		System.out.println("memory value = " + memoryVal);
		unsafe.freeMemory(memoryAddress);
		System.out.println("-------------------------------");
	}

	private static void copyMemory(Unsafe unsafe) {
		System.out.println("copyMemory test -------------");
		try {
//			Animal animal = (Animal)unsafe.allocateInstance(Animal.class);
			Animal animal = new Animal("hello", 10);
			System.out.println("orgAnimal: " + animal + " | " + animal.string());

			System.out.println("animal size: " + sizeOf(unsafe, animal));

			long size = sizeOf(unsafe, animal);
			long start = toAddress(unsafe, animal);
			System.out.println("start: " + start);
			long address = unsafe.allocateMemory(size);
			System.out.println("address: " + address);
			unsafe.copyMemory(start, address, size);
			Animal newAnimal = (Animal)fromAddress(unsafe, address);
			System.out.println("newAnimal: " + newAnimal + " | " + newAnimal.string());

		} finally {
			System.out.println("------------------------------");
		}

	}

//	private static long sizeOf(Unsafe unsafe, Object object) {
//		return unsafe.getAddress(normalize(unsafe.getInt(object, 4L)) + 12L);
//	}

	private static long toAddress(Unsafe unsafe, Object object) {
		Object[] array = new Object[] {object};
		int baseOffset = unsafe.arrayBaseOffset(Object[].class);
		System.out.println("baseOffset = " + baseOffset);
		int arrayInt = unsafe.getInt(array, baseOffset);
		System.out.println("memory int: " + arrayInt);
		return normalize(arrayInt);
	}

	private static Object fromAddress(Unsafe unsafe, long address) {
		Object[] array = new Object[] {null};
		int baseOffset = unsafe.arrayBaseOffset(Object[].class);
		unsafe.putLong(array, baseOffset, address);
		return array[0];
	}

	private static long sizeOf(Unsafe unsafe, Object object) {
		Class<?> aClass = object.getClass();
		List<Field> fields = new ArrayList<>();
		while (aClass != Object.class) {
			for (Field field : aClass.getDeclaredFields()) {
				if ((field.getModifiers() & Modifier.STATIC) == 0) {
					fields.add(field);
				}
			}
			aClass = aClass.getSuperclass();
		}

		long maxSize = 0;
		for (Field field : fields) {
			long offset = unsafe.objectFieldOffset(field);
			if (offset > maxSize) {
				maxSize = offset;
			}
		}

		return ((maxSize / 8) + 1) * 8; // padding
	}

	/**
	 * normalize是一个为了正确内存地址使用，将有符号的int类型强制转换成无符号的long类型的方法
	 * @param value
	 * @return
	 */
	private static long normalize(int value) {
		if (value > 0) {
			return value;
		} else {
			return (~0L >>> 32) & value;
		}

	}

	private static void objectFieldOffset(Unsafe unsafe) {
		System.out.println("objectFieldOffset test -------------");
		try {
			Animal animal = (Animal)unsafe.allocateInstance(Animal.class);
			System.out.println(animal + " | " + animal.string());

			Class<? extends Animal> animalClass = animal.getClass();
			Field name = animalClass.getDeclaredField("name");
			Field legs = animalClass.getDeclaredField("legs");

			long nameOffset = unsafe.objectFieldOffset(name);
			long legsOffset = unsafe.objectFieldOffset(legs);
			System.out.println("nameOffset: " + nameOffset);
			System.out.println("legsOffset: " + legsOffset);

			unsafe.putObject(animal, nameOffset, "unsafe-modify name");
			unsafe.putInt(animal, legsOffset, 5);
			System.out.println(animal + " | " + animal.string());

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} finally {
			System.out.println("-------------------------");
		}
	}

	private static void allocateInstance(Unsafe unsafe) {
		System.out.println("allocateInstance test ----------------");
		try {
			// 不会调用Animal的实例构造方法
			Animal a1 = (Animal)unsafe.allocateInstance(Animal.class);
			System.out.println("a1: " + a1 + " | " + a1.string());

			// 正常构造
			Animal a2 = new Animal();
			System.out.println("a2: " + a2 + " | " + a2.string());

		} catch (InstantiationException e) {
			e.printStackTrace();
		} finally {
			System.out.println("------------------------");
		}
	}
}
