package com.smxknife.jvm.demo01;

/**
 * 模拟java虚拟机栈OutOfMemoryError
 * 1 分析：
 * 1.1 java虚拟机栈会报该错误有两种情况：
 *      1）虚拟机栈是动态扩展的，在扩展时无法申请足够的内存
 *      2）线程创建的时候没有足够的内存去创建java虚拟机栈
 * 2 前置条件
 * 2.1 设置jvm使用内存 -Xmx（最大可用内存）-Xms（最小内存）
 * 3 模拟原理
 *      1）扩展无法申请足够的内存就用递归方法来试，理论如此，但是在Hotspot中是无法模拟此情况，依然会报StackOverflowError
 *      2）通过不断创建线程
 */
public class JvmOutOfMemory {
    int count = 0;
    public static void main(String[] args) {
        JvmOutOfMemory jvm = new JvmOutOfMemory();
        try {
            // jvm.mockRecursive();
            jvm.mockMultiThread();
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
    }

    // 模拟递归
    public void mockRecursive() {
        count++;
        mockRecursive();
    }

    // 模拟线程
    public void mockMultiThread() {
        while (true) {
            Runnable runnable = () -> {
                count++;
                System.out.println(Thread.currentThread().getName() + " count: " + count);
                while (true) {}
            };
            new Thread(runnable).start();
        }
    }
}
