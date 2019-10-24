package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019/10/14
 */
public class _Doc {
	/** java自带三个类加载器
	 * 1. Bootstrap ClassLoader
	 *      * 启动类加载器，最顶层的类加载器，加载核心类库，也就是我们环境变量下面%JRE_HOME%\lib下的rt.jar、resources.jar、charsets.jar和class等
	 *      * 可以通过启动jvm时指定-Xbootclasspath和路径来改变Bootstrap ClassLoader的加载目录
	 *
	 * 2. Extension ClassLoader
	 *      * 扩展类加载器，加载目录%JRE_HOME%\lib\ext目录下的jar包和class文件
	 *      * 还可以加载-D java.ext.dirs选项指定的目录
	 *
	 * 3. Application ClassLoader
	 *      * 应用加载器，加载当前应用的classpath的所有类
	 */

	/** 层次关系图
	 *  --------------------------
	 * |  Bootstrap ClassLoader   |
	 * |       启动类加载器         |
	 *  --------------------------
	 *            /\
	 *            ||
	 *  --------------------------
	 * |  Extension ClassLoader   |
	 * |       扩展类加载器         |
	 *  --------------------------
	 *            /\
	 *            ||
	 *  --------------------------
	 * |  Application ClassLoader |
	 * |       应用类加载器         |
	 *  --------------------------
	 *     /\                  /\
	 *     ||                  ||
	 *  ------------------   ------------------
	 * | User ClassLoader | | User ClassLoader |
	 * |   自定义类加载器   |  |   自定义类加载器   |
	 *  ------------------   ------------------
	 */

	/** 测试代码如下：
	 * ClassLoader classLoader = Demo.class.getClassLoader();
	 * System.out.println(classLoader);
	 * System.out.println(classLoader.getParent());
	 * System.out.println(classLoader.getParent().getParent());
	 * // 输出如下：
	 * sun.misc.Launcher$AppClassLoader@18b4aac2
	 * sun.misc.Launcher$ExtClassLoader@36baf30c
	 * null // 这里输出为null，是因为Bootstrap ClassLoader是c语言实现的，在java里面无法直接调用
	 */

	/** 类加载的三种方式
	 * 1. 启动时JVM初始化加载含有main()方法的类
	 * 2. 通过Class.forName(name)方法动态加载，会默认执行初始化块（static{}）,但是Class.forName(name, initialize, loader)可以通过initialize来指定是否执行static
	 * 3. 通过ClassLoader.loadClass动态加载不会初始化语句块
	 */

	/** 双亲委派模型
	 * 当一个类加载器要进行类加载时，会先交给其父类加载器去完成，因此最终的加载都会交给顶层的启动类加载器去完成
	 * 只有当父类加载器无法完成加载任务时，才会尝试自己加载
	 *
	 * 这样可以避免重复加载，比如说你自己定义一个java.lang.Object，那么交给父类加载器之后会发现已经存在，所以不会出现重复加载的问题
	 */

	/** 自定义类加载器
	 * 1. 遵守双亲委派模型，继承ClassLoader，重写findClass方法，通常建议
	 * 2. 破坏双亲委派模型，继承ClassLoader，重写loadClass方法
	 */
}
