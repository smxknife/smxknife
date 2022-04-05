# 结论

## _Run_ObjectLock

* Thread.holdsLock(obj)在线程获得锁之后，返回true
* 无锁方法调用上面的返回false

## _Run_ObjectHasLock

* synchronized 方法和synchronized(this)的效果是一样的，都是锁定当前对象
* 在对象中增加一个lock对象，采用synchronized(lock)这个就不是锁定当前的对象，在方法中使用这种方式会比锁定this效率高

## _Run_staticInstanceObj

* synchronized(StaticInstanceObj.class)与synchronized(this)锁定的不是一个对象，所以如果是静态方法上加synchronized和实例方法上加synchronized，这两个实际锁定的不是一个对象，所以无法做到同步，这点需要注意

## _Run_staticInstanceObj2

* synchronized 两个实例方法，是可以做到同步