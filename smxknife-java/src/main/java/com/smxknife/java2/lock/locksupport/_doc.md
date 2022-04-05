# LockSupport

## 大致用途

LockSupport在jdk中为创建锁或其他同步类提供了线程阻塞原语

### 提供的方法

```java
public static void park(Object blocker); // 暂停当前线程
public static void parkNanos(Object blocker, long nanos); // 暂停当前线程，不过有超时时间的限制
public static void parkUntil(Object blocker, long deadline); // 暂停当前线程，直到某个时间
public static void park(); // 无期限暂停当前线程
public static void parkNanos(long nanos); // 暂停当前线程，不过有超时时间的限制
public static void parkUntil(long deadline); // 暂停当前线程，直到某个时间
public static void unpark(Thread thread); // 恢复当前线程
public static Object getBlocker(Thread t);
```

### 优点

功能类似wait/notify，但是比之又灵活方便，前者需要在加锁条件下才能使用，每次还只能随机唤醒某一个线程。
后者可以在任何地方加锁，然后可以指定恢复某个线程

### 特点

先park()阻塞线程再unpark唤醒线程，这个没什么疑问（参加Demo01），那么如果先唤醒线程，再阻塞线程呢？会发生什么情况

> case 1: 先唤醒1次线程，再阻塞1次线程（参见Demo02）
    * 在case 1中，先唤醒1次，再阻塞，发现线程不会阻塞，而是直接运行通过
> case 2: 先唤醒2次线程，再阻塞2次线程（参见Demo03）
    * 在case 2中，先唤醒2次，再阻塞2次，发现线程在第一次不会挂起，但是第二次却是没法通过
    这就涉及到Unsafe类的底层实现，无论是park还是unpark都需要凭证，unpark增加一个凭证（最多1个，即使多次也是1个），
    park消费凭证，每次消费1个，多次park消费多个，所以，连续两次park，第二次线程被挂起

线程是可中断的，但是却不会抛出InterruptException，可以从Thread的interrupt status获取到状态，（参见Demo04）
而对应的wait在被interrupt时，会抛出InterruptException，而且通过Thread的Interrupt status获取不到状态（通过thread.isInterrupted()输出为false）

