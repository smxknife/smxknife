# ThreadPoolExecutor总结

## Executors

该类通过三个内部类封装了ThreadPoolExecutor的一些方法，方便开发，但是封装不一定好，屏蔽了底层，容易让人不明所以

* Executors.newCachedThreadPool()

```java
return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
```

参数什么意思？如果不知道，先假装知道，看看热闹

* Executors.newFixedThreadPool(int nThreads)

```java
return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
```

会与不会，继续往下

* Executors.newSingleThreadExecutor()

```java
return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
```

哎，不一样了，进去看看抛开设计模式不讲，还是ThreadPoolExecutor相关

### 具体实现不谈，先谈效果

* Executors.newCachedThreadPool()

> * 无界线程池（虽说无界，其实上面传的是Interger.MAX_VALUE，但是受限于CAPACITY，达不到MAX_VALUE）
> * 线程可以自动回收

* Executors.newFixedThreadPool(int nThreads)

> * 有界线程池，可以指定最大线程数

* Executors.newSingleThreadExecutor()

> * 创建单一线程池

用法大概如此，可是实现就是通过几个参数的控制，那么这几个参数就显得尤为重要了

## 参数含义

这个问题很关键，搞不清这个，线程池肯定用不好，bug肯定要偷偷找你麻烦

* corePoolSize
* maximumPoolSize
* keepAliveTime
* unit
* workeQueue
* threadFactory
* rejectedExecutionHandler

最后两个是为了定制特殊需求处理，暂且先放一下，看看前5个。

另外，unit比较单纯，就是处理keepAliveTime的时间单位，也比较好理解，暂且忽略，剩下corePooleSize、maximumPoolSize、keepAliveTime、workeQueue，这四个参数的相互配合，产生的神奇作用

* A: execute(Runnable)想要执行的线程数
* B: corePoolSize 核心线程数
* C: maximumPoolSize 最大线程数
* D: A - B （当A >= B）
* E: workQueue = new LinkedBlockingDeque(); 无构造函数
* F: workQueue 代表SynchronousQueue
* G: keepAliveTime 

### A < B

马上创建线程运行任务，不会放入扩展队列中，其他功能参数忽略

### A > B && A <= C && E

忽略C和G，把D放入E中等待执行

### A > B && A <= C && F

C和G有效，马上创建和运行A，不把D放入F中，D在执行完任务后，发生超时时间G后，将D清除

### A > B && A > C && E

C和G忽略，把D放入E等待执行

### A > B && A > C && F

处理C以内的部分，其他大于D的不处理，抛出异常

### 当keepAliveTime有效，且为0时

任务执行完后立即从队列删除

### 如果workQueue是有参数的

当workQueue是E时，大于workQueue容器+maximumPoolSize的线程将会抛出异常

当workQueue是F，大于maximumPoolSize，大于的部分抛出异常


