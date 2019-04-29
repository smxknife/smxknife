## 快速排序

快速排序是一种常用的优雅排序算法。使用**分而治之**的策略

### 分而治之
divid and conquer 简称D&C，一种著名的递归式问题解决方法

D&C算法是递归的，所以使用D&C解决问题过程包括两个步骤：
* 找出基线条件，这种条件必须尽可能简单
* 不断将问题分解（或者说缩小规模），直到符合基线条件

所以每次递归调用必须缩小问题规模

    注：编写涉及数组的递归条件时，基线条件通常为数组为空或只包含一个元素
    
### 快速排序

原理：
* 选择基准值pivot
* 遍历数组根据基准值进行分片，分为大于基准值的数组和小于基准值的数组
* 递归调用两个数组

### 代码

```java
public static <T extends Comparable> List<T> sort(List<T> elements) {
		Preconditions.checkNotNull(elements);
		List<T> org = new ArrayList<>(elements.size());
		org.addAll(elements);
		if (org.size() < 2) return org; // 基线条件
		T pivot = org.remove(0); // 基准值
		Map<Boolean, List<T>> partition = org.stream()
				.collect(Collectors.partitioningBy(t -> t.compareTo(pivot) <= 0));
		List<T> subList = new ArrayList<>(elements.size());
		if (log.isInfoEnabled()) log.info("elements = {}, pivot = {}, lf = {}, gt = {}", elements, pivot, partition.get(true), partition.get(false));
		subList.addAll(sort(partition.get(true))); // 小于基准值递归
		subList.add(pivot);
		subList.addAll(sort(partition.get(false))); // 大于基准值递归
		return subList;
	}
```

### 大O表示法

> 在最糟糕的情况下，快速排序运行时间为O(n^2)

> 在平均情况下，快速排序运行时间为O(n log n)

因为快速排序法与基准值的选择有很大的关系