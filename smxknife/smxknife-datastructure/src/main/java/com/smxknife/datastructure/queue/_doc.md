## 队列

### TODO

* 添加一个Ceque环形队列，头尾相连
    * 可以用于实现**环形缓冲区（RingBuffer）**，环形缓冲区是一种定长为N的先进先出的数据结构
        * 在进程间异步数据传输或记录日志文件十分有用。当缓冲区为空时，消费者会在数据存储到缓冲区前等待；当缓冲区满时，生产者会等待将数据存入缓冲区
        * RingBuffer的api
* 添加一个Steque，以栈为目标的队列，支持push、pop、enqueue
* 添加一个Deque，双向队列，同时支持两端添加或删除元素
    * int size()
    * void pushLeft(T t)
    * void pushRight(T t)
    * T popLeft(T t)
    * T popRight(T t)
* 添加一个随机队列RandomQueue
    * boolean isEmpty()
    * void enqueue(T t)
    * T dequeue()
    * T sample() 随机返回一个元素但不删除

* 前移编码策略，输入一个标准的字符串，使用链表保存该字符串，并删除重复的。当读取一个从未见过的字符串，将其插入表头；当读取一个已经存在的字符串，将它从链表中删除，并再加入表头。这种策略的理论是，最近访问过的元素很有可能再次访问，因此适用于缓存和数据压缩等许多场景

* 添加复制队列功能，将队列的构造函数增加一个队列的参数，将参数队列中的所有元素复制一份，不相互影响
    