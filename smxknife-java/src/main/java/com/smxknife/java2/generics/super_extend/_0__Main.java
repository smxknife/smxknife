package com.smxknife.java2.generics.super_extend;

/**
 * @author smxknife
 * 2020/11/5
 */
public class _0__Main {
	public static void main(String[] args) {
		 // Plate<_1_Fruit> plate = new Plate<_2_Apple>(new _2_Apple());// 编译报错，装苹果的盘子不允许成为装水果的盘子

		/**
		 * |------|
		 * |Fruit |
		 * |------|
		 *    *  *
		 *    *    *
		 *    *      *
		 * |-----|   |------|
		 * |Apple|   |Banana|
		 * |-----|   |------|
		 *
		 */
		// 这样是可以的，这里就是通过extends确定了上界（上界通配符）
		// Plate<? extends _1_Fruit>是Plate<_1_Fruit>和Plate<_2_Apple>的基类
		/**
		 * <? extends Fruit>这里说明？是继承Fruit，所以Fruit是基类，所以取出来的元素可以是Fruit或者是Fruit的父类
		 * 无法确定里面的元素是哪些子类，所以这里无法使用子类进行接收
		 */
		Plate<? extends _1_Fruit> ePlate = new Plate<_2_Apple>(new _2_Apple());
		/**
		 * 上界的副作用是，不能放，只能取
		 * 读取出来的东西只能存放在Fruit或它的基类里
 		 */
		//ePlate.setItem(new _2_Apple()); // 这里就编译报错了，不能放
		//ePlate.setItem(new _1_Fruit()); // 这里就编译报错了，不能放
		//ePlate.setItem(new _0_Food()); // 编译报错，不能放
		_1_Fruit item = ePlate.getItem(); // 不报错，说明可以取
		//_2_Apple item1 = ePlate.getItem(); // 编译报错。这里可以取，但是不能取到子类，只能取到基类
		_0_Food item2 = ePlate.getItem(); // 也没有报错，说明同样可以取，验证了上面，取出来的元素只能放在基类或者基类的父类
		_0_Thing item3 = ePlate.getItem(); // 也没有报错，说明同样可以取，验证了上面，取出来的元素只能放在基类或者基类的父类

		/**
		 *         |------|
		 *         |Food  |
		 *         |------|
		 *           *
		 *          *
		 *         *
		 *  |------|
		 *  |Fruit |
		 *  |------|
		 */
		// 这样也是可以的，通过super确定了下届（下届通配符）
		/**
		 * 这里的下还需要再解释一下：
		 * <? super _1_Fruit>说明？是Fruit的父类，
		 * 所以只要是Fruit的父类都满足条件，但是又不知道有多少父类，所以在获取的时候根本不知道最根的父类是谁，
		 * 所以这里这里只能采用java里面最顶端的元素Object，所有返回的结果只能是Object
		 */
		Plate<? super _1_Fruit> sPlate = new Plate<_1_Fruit>(new _2_Apple());
		/**
		 * 下界的副作用：不影响存，但是取出来只能放在Object
		 */
		// sPlate.setItem(new _0_Food()); // 编译报错，不能放
		sPlate.setItem(new _1_Fruit());
		sPlate.setItem(new _2_Apple());
		sPlate.setItem(new _3_GreenApple());
		Object item11 = sPlate.getItem();
		//_1_Fruit item22 = sPlate.getItem(); // 编译报错，不能取出为具体类型
		//_2_Apple item33 = sPlate.getItem(); // 编译报错，不能取出为具体类型
	}
}
