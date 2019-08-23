# sun.misc.Unsafe

## 简介

* 可以用来在任意内存地址位置处读写数据
* 单例
* 不能通过调用Unsafe.getUnsafe()获取，因为getUnsafe被设计成只能从引导类加载器（bootstrap class loader）加载，从getUnsafe的源码中也可以看出来
* 可以通过theUnsafe，通过反射获取该字段获得实例

## 用例

* 绕过类初始化方法。当你想要绕过对象构造方法、安全检查器或者没有public的构造方法时，allocateInstance()方法变得非常有用。
* 内存修改，内存修改在c语言中是比较常见的，在Java中，可以用它绕过安全检查器
* 实现类似C语言的sizeOf()函数
* 实现Java浅复制
* 消去内存中的密码
* 动态加载类
* 包装受检异常为运行时异常
* 快速序列化
* 在非Java堆中分配内存
* Java并发中的应用 cas
* 线程挂起与恢复