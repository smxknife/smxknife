# Vector源码解读

## 继承实现体系

    public class Vector<E>
        extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable

## 构造方法

提供了四个构造函数，如下：
```java
Vector();
Vector(int initialCapacity);
Vector(int initialCapacity, int capacityIncrement);
Vector(Collection<? extends E> c);
```

构造函数主要初始化的就是两个值
* 初始容量
* 扩展容量

#### Vector()

* 默认情况下，初始容量为10，扩展容量为0

#### Vector(int initialCapacity)

* 指定初始容量initialCapacity，扩展容量为0

#### Vector(int initialCapacity, int capacityIncrement)

* 指定初始容量和扩展容量
* 如果初始容量<0，抛出IllegalArgumentException
* 用初始容量初始化一个数组：Object[] elementData，这是用来实际存储的容器

## 方法

### add(E e)

* 线程安全，因为该方法是被synchronized修饰
* 返回类型是boolean，要么抛出异常要么返回true，没有返回false的场景（但是VectorSet是可以返回false）
* modCount++，修改次数，每次修改都会+1
* 确保
* elementCount+1，元素数会+1
