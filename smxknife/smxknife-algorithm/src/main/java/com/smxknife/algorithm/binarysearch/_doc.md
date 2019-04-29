## 二分法查找

### 关键点

* 输入有序的元素列表，必须有序
* 查找元素安在列表中的位置，否则返回null
* 二分法查找每次找中间的点，与目标值进行判断，因为有序，所以每次查找都可以排除剩下部分的一半
* 对于n个元素，二分法查找最多需要log2^n(对数运算)
* 时间复杂的O(log2^n)，表示操作数
* 因为二分法需要随机查找，所以最好采用数组结构来实习

### 算法

```java
public static <T extends Comparable> int find(T t, List<T> elements) {
		Preconditions.checkArgument(Objects.nonNull(elements) && elements.size() >= 0);
		if (log.isInfoEnabled()) log.info("elements = {}", elements.toString());
		int low = 0;
		int high = elements.size() - 1;

		while (low <= high) {
			int mid = (low + high) / 2; // 如果不是偶数，向下取整
			if (log.isInfoEnabled()) log.info("low = {}, mid = {}, high = {}", low, mid, high);
			if (t.compareTo(elements.get(mid)) == 0) {
				return mid;
			} else if (t.compareTo(elements.get(mid)) > 0) {
				low = mid + 1;
				if (low > high) return -1;
			} else {
				high = mid;
				if (high < low) return -1;
			}
		}
		return -1;
	}
```

### 大O运行时间

大O表示法指出了最糟糕情况下运行的时间

常见的五种大O运行时间：
* O(log n) 也叫对数时间，包括二分法查找
* O(n) 也叫线性时间，意味着查看了每一个元素，包括简单查找
* O(n * log n) 包括快速排序法(较快的排序算法)
* O(n^2) 包括选择排序（较慢的排序算法）
* O(n!) 旅行商问题解决方案（非常慢的算法），这里n！代表n的阶乘

![](https://i.loli.net/2019/04/29/5cc693c8c19df.jpg)

### 算法的一些概念

> 算法的速度

    算法的速度并非指的是时间，而是操作数的增速

### 附录
>* 2^1 = 2
>* 2^2 = 4
>* 2^3 = 8
>* 2^4 = 16
>* 2^5 = 32
>* 2^6 = 64
>* 2^7 = 128
>* 2^8 = 256
>* 2^9 = 512
>* 2^10 = 1024

