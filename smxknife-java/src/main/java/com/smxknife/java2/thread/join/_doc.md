# join

## 简介

join方法的简单作用就是：让当前线程暂停，等待调用的线程执行完毕后，再继续执行

```java
Thread th1 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + "- no." + i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "th1");

		th1.start();
		th1.join();

		System.out.println("end main");
```

上面代码的效果就是main线程在join的地方暂停，等待th1执行完毕后，再继续执行

输出结果如下：
```properties
th1- no.0
th1- no.1
th1- no.2
th1- no.3
th1- no.4
end main
```

## 解析

这是如何实现的？其实就是通过wait和notify来实现

下面这是join的核心代码，默认时间间隔是0
```java
public final synchronized void join(long millis)
    throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            while (isAlive()) {
                wait(0); // 时间为0的情况下，执行的就是这一段，就是调用的wait方法
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }
```

因为wait方法是需要对象锁的，所以，join方法采用synchronized修饰

```java
有很多人不理解join为什么阻塞的是主线程呢? 不理解的原因是阻塞主线程的方法是放在previousThread这个实例作用，
让大家误以为应该阻塞previousThread线程。实际上主线程会持有previousThread这个对象的锁，然后调用wait方法去阻塞，
而这个方法的调用者是在主线程中的。所以造成主线程阻塞
```