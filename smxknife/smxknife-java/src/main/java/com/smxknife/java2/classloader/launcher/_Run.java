package com.smxknife.java2.classloader.launcher;

import sun.misc.Launcher;

/**
 * @author smxknife
 * 2019/11/6
 */
public class _Run {
	public static void main(String[] args) {
		// Launcher是java程序的入口，根据双亲委派模型，Launcher是由JVM创建的，类的加载器应该是BootStrapClassLoader
		ClassLoader classLoader = Launcher.class.getClassLoader();
		System.out.println("classloader is " + classLoader);
		System.out.println("classloader 是null，说明是BootStrapClassLoader");

		// ExtClassloader就是在Launcher中创建的
		/**
		 *         Launcher.ExtClassLoader var1;
		 *         try {
		 *             var1 = Launcher.ExtClassLoader.getExtClassLoader();
		 *         } catch (IOException var10) {
		 *             throw new InternalError("Could not create extension class loader", var10);
		 *         }
		 */

		// ApplicationLoader通用也是在Launcher中创建
		/**
		 *         private ClassLoader loader;
		 *         ...
		 *         try {
		 *             this.loader = Launcher.AppClassLoader.getAppClassLoader(var1); // 这个var1就是上面的ExtClassLoader
		 *         } catch (IOException var9) {
		 *             throw new InternalError("Could not create application class loader", var9);
		 *         }
		 */

		ClassLoader appClassloader = Launcher.getLauncher().getClassLoader();
		System.out.println("通过Launcher获取的AppClassLoader | " + appClassloader);
		ClassLoader extClassLoader = appClassloader.getParent();
		System.out.println("因为ExtClassLoader不是Launcher的成员，所以无法直接获取，但是在构造AppClassLoader的时候，将Ext作为了App的参数，所以可以通过getParent获取，如下：");
		System.out.println("ExtClassLoader = " + extClassLoader);

		/**
		 * 初始化三个ClassLoader之后，Launcher又做了下一步操作，将当前线程的ContextClassLoader设置为AppClassLoader
		 * Thread.currentThread().setContextClassLoader(this.loader);
		 * 所以从这里看出，Thread的上下文ClassLoader是在这里设置的
		 * 这里为什么使用了ContextClassLoader，这就是双亲委派模型的一次破坏，但是也是没有办法的事情，在某些情况下，需要调用业务代码
		 * 而不得不做一些妥协，SPI就是典型的应用
		 */
	}
}
