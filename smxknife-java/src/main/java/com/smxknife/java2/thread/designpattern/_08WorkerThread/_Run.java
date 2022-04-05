package com.smxknife.java2.thread.designpattern._08WorkerThread;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {
	public static void main(String[] args) {
		// Worker Thread模式：工作没来就一直等，工作来了就干活

		/** 角色
		 * 1. Client(委托者)
		 * 2. Channel(通信线路)：接收来自client角色的Request角色，并将其传给worker角色
		 * 3. Worker(工人)：worker从channel中获取request角色，并进行工作。当工作完成后，会继续获取另外的工作
		 * 4. Request
		 */

		/** 优点
		 * 1. 提高吞吐量
		 * 2. 容量控制：java.util.concurrent.ThreadPoolExecutor实际上也是容量控制
		 * 3. 调度与执行分离：
		 *      - 提高响应速度
		 *      - 控制执行顺序
		 *      - 可以取消和反复执行
		 *      - 通往分布式之路
		 */

		/** 相关的设计模式
		 * 1. Procedure-Consumer模式
		 * 2. Thread-Per-Message模式
		 * 3. Command模式
		 * 4. Future模式
		 * 5. Flyweight模式
		 * 6. Thread-Specific Storage模式
		 * 7. Active Object模式
		 */
	}
}
