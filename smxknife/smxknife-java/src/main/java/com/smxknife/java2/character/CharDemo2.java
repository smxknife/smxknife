package com.smxknife.java2.character;

/**
 * @author smxknife
 * 2019-04-04
 */
public class CharDemo2 {
	public static void main(String[] args) {
		char c = '中';
		Character character = new Character(c);
		System.out.println(Character.getDirectionality(c));
		System.out.println(Character.getName(c));
		System.out.println(Character.isDefined(c));
		System.out.println(Character.isLetter(c));
		System.out.println(c);
	}
}
