package com.smxknife.java2.generics;

import com.smxknife.java2.generics._class.Group;

/**
 * @author smxknife
 * 2019-04-19
 */
public class SupperGeneric {

	public void set() {
		Group<? super Cat> supperGroup = new Group<>();
		supperGroup.setObj(new Cat());
//		supperGroup.setObj(new Animal());
		supperGroup.setObj(new Bosi());
		supperGroup.setObj(null);
//		Animal supAnimal = supperGroup.getObj();
//		Cat supCat = supperGroup.getObj();
//		Bosi supBosi = supperGroup.getObj();
		Object obj = supperGroup.getObj();

		Group<? extends Cat> extendsGroup = new Group<>();
//		extendsGroup.setObj(new Cat());
//		extendsGroup.setObj(new Animal());
//		extendsGroup.setObj(new Bosi());
		extendsGroup.setObj(null);
		Animal obj1 = extendsGroup.getObj();
		Cat cat = extendsGroup.getObj();
//		Bosi bosi = extendsGroup.getObj();
	}
}
