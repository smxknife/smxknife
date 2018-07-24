package com.smxknife.jvm.demo01;

/**
 * 模拟java虚拟机抛出StackOverflowError
 * 1 前提条件：
 * 1.1 栈容量大小是固定的，通过-Xss128k来控制jvm为每个线程分配的内存大小
 * 2 模拟的原理：
 * 2.1 jvm对java虚拟机栈只会有两种操作，以栈帧为单位入栈和出栈
 * 2.2 那么只需要控制每个线程内存的大小，禁止出栈，不断进行入栈操作，就会出现该错误
 * 3 栈帧是在何时产生：
 * 3.1 栈帧是随着方法调用而创建，方法结束而销毁（无论是否异常）
 * 4 结论：
 * 4.1 基于以上思路，采用递归的方式，不断的进行方法调用而不进行方法结束，肯定会模拟出该错误
 */
public class JvmStackOverflow {

    int count = 0;

    public static void main(String[] args) {
        JvmStackOverflow jvm = new JvmStackOverflow();
        try {
            jvm.mockMethod();
        } catch (StackOverflowError e) {
            System.out.println("count : " + jvm.count);
            e.printStackTrace();
        }
    }

    public void mockMethod() {
        count++;
        mockMethod();
    }
}
