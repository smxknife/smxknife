# wait/notify/notifyAll

## wait

* 所有实例都拥有一个等待队列，它是在实例的wait方法执行后停止操作的线程队列
* wait之后线程将暂停操作，除非发生以下的操作，否则线程将一直处于休眠中
    * 有其他线程的notify方法唤醒线程
    * 其他线程的notifyAll方法唤醒线程
    * 其他线程的interrupt方法唤醒线程
    * wait超时
* wait会抛出InterruptException，但是请注意，
    - wait和notify等方法都需要获取到对象锁才能执行，否则会报错
    - 这就造成了，如果线程发生了interrupt，wait不会立刻进入异常，而是要先等待获取锁，然后才可以进入异常