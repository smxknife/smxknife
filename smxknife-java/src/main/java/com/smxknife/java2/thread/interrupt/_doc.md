# interrupt

## 简介

虽然Thread中提供了stop、suspend方法，但是都已经声明为废弃，因为这些方法在使用上存在不安全性，不建议使用

推荐的几个方法是：
* interrupt(): 在一个线程中调用另一个线程的interrupt方法，即会先那个线程发出信号，线程的中断状态已被设置
    - 至于线程如何处理由线程具体代码实现
* isInterrupt(): 用来判断当前线程的中断状态（true，false）
* interrupted(): 是Thread的静态方法，用来恢复中断状态，**这个名字非常有歧义**

## 分析

为什么调用interrupt方法后，线程还是可以运行？这让人很费解？值得深入分析一下

interrupt源码如下：
```java
if (this != Thread.currentThread())
            checkAccess();

        synchronized (blockerLock) {
            Interruptible b = blocker;
            if (b != null) {
                interrupt0();           // Just to set the interrupt flag
                b.interrupt(this);
                return;
            }
        }
        interrupt0();
```


```java
private volatile Interruptible blocker; // 默认为空
```

```java
private native void interrupt0();
```

这三个就是interrupt实现的三个关键点，

* 当第一次调用interrupt方法后，blocker对象为空，那么就不会进入if判断，直接执行interrupt0
* interrupt0是一个本地方法，具有以下功能
    * 调用interrupt0之后，该线程将会被设置为中断状态
    * 再次调用interrupt0之后，该线程的中断状态被清除
 
所以对于线程中没有阻塞的的操作，如wait、sleep、join等，调用interrupt方法，只会运行一次interrupt0，这将会线程标记为中断状态，但是没有中断。

那么如果线程中调用了这些阻塞操作之后呢？

```java
    /**
     * 省略部分注释，下面这几句是关键，意思是说，当前线程sleep时，如果其他线程调用interrupt方法，sleep的本地方法会将
     * 当前线程的interrupt状态清除，并且抛出InterruptedException，这正好解释了，为什么通过while(!Thread.currentThread().isInterrupted())中如果
     * 包含阻塞操作，那么会一直为true正常运行
     * @throws  InterruptedException
     *          if any thread has interrupted the current thread. The
     *          <i>interrupted status</i> of the current thread is
     *          cleared when this exception is thrown.
     */
    public static native void sleep(long millis) throws InterruptedException;
```

其实阻塞sleep方法，再调用interrupt方法，blocker对象已经不为空，进入if中，调用了interrupt0，将线程设置为
中断状态，然后调用blocker.interrupt() 这是真正的中断方法，中断线程，抛出异常


## interrupted()

再看一下这个怪胎，这个名字实在让人招恨，不看源码实在是不知道，这个是什么意思

```java
/**
     * Tests whether the current thread has been interrupted.  The
     * <i>interrupted status</i> of the thread is cleared by this method.  In
     * other words, if this method were to be called twice in succession, the
     * second call would return false (unless the current thread were
     * interrupted again, after the first call had cleared its interrupted
     * status and before the second call had examined it).
     *
     * <p>A thread interruption ignored because a thread was not alive
     * at the time of the interrupt will be reflected by this method
     * returning false.
     *
     * @return  <code>true</code> if the current thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see #isInterrupted()
     * @revised 6.0
     */
    public static boolean interrupted() {
        return currentThread().isInterrupted(true);
    }
```

通过上面的注释可知，这个方法是用来清除线程的中断状态！！！！

在没有任何其他干扰的情况下，如果调用两次该方法，那么第二次返回false，即如果当前线程处于interrupted状态，调用该方法后会返回true，
但是当再次调用该方法后，就会返回false

## isInterrupted()

```java
/**
     * Tests whether this thread has been interrupted.  The <i>interrupted
     * status</i> of the thread is unaffected by this method.
     *
     * <p>A thread interruption ignored because a thread was not alive
     * at the time of the interrupt will be reflected by this method
     * returning false.
     *
     * @return  <code>true</code> if this thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see     #interrupted()
     * @revised 6.0
     */
    public boolean isInterrupted() {
        return isInterrupted(false);
    }
```

从注释来看，该方法不会影响线程的状态，如果线程是interrupted状态返回true，否则返回false

## isInterrupted(false|true)

从isInterrupted()和interrupted()方法可以看出来，两个方法都调用了isInterrupted(false|true)这个方法，只不过，一个传的参数为true，
另一个传的false，调用false就不会修改状态，而true会修改状态