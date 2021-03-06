## 贪婪算法

### 教室调度问题

    问题描述：同一间教室，一天内，尽量多的安排课程不能造成冲突
    
* step1：选出结束最早的课，这是第一堂课
* step2：第一堂课结束后才开始的课，并且结束最早的课
* step3：重复2

这种方法就是贪婪算法的实用

### 贪婪算法

Def：贪婪算法寻找局部最优解，企图以这种方式获得全局最优解

特点：
* 简单
* 每步采取最优做法，专业术语是"每步选择最优解，最终得到的就是全局最优解"
* 贪婪算法是用来解决近似问题，对于完美问题，可能就。。。

判断近似解的优劣程度：
* 速度有多快
* 得到近似解与最优解的近似程度

### NP完全问题

NP完全问题：如旅行商问题和集合覆盖问题

如果判断为NP完全问题，就不必寻找完美解决方案，采用近似算法即可

如何判断为NP完全问题：
* 元素较少时运行速度特别快，但是随着元素的增加，速度变得非常慢
* 涉及所有组合问题，通常是NP完全问题
* 不能将问题分解成小问题，必须考虑各种可能的情况。这可能是NP完全问题
* 如果问题涉及到序列（如旅行商中城市的序列）且难以解决，可能就是NP完全问题
* 如果问题涉及到集合（如广播台集合）且难以解决，可能就是NP完全问题
* 如果问题可以转化成集合覆盖或旅行商问题，肯定是NP完全问题
