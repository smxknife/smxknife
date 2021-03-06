## 散列表

### 关键点

* 快速查找
* 防止重复
* 可以用于缓存
* 解决冲突，使用数组加链表的结构，对于key冲突，将数据存储在链表结构中
* 因为冲突的存在，使得存在性能问题，极端情况下，所有数据的key值是一样的，值全部存在链表结构中，操作时间变为O(n)
* 正常情况操作时间为O(1)

![](https://i.loli.net/2019/04/30/5cc7befe63da9.jpg)

![](https://i.loli.net/2019/04/30/5cc7bf4880c72.jpg)

### 如何避开最糟糕的情况

对于散列表来说，避开最糟糕的情况是至关重要的。为此，需要避免冲突

> 较低的填装因子

> 良好的散列函数

### 填装因子

填装因子 = 散列表包含的元素数 / 位置总数

一个不错的经验是：一旦填充因子大于0.7就调整散列表的长度

### 散列函数

良好的散列函数可以让数组中的值均匀分布，SHA函数就是一个不错的散列函数
