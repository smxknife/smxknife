package com.smxknife.java.ex4.innerupcast;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Host host = new Host();
		// Host.Bird bird = host.new Bird(); 编译出错
		Host.Friend friend = host.new Friend();

		System.out.println(host.getBird().getPetName());
		System.out.println(host.getFriend().getPeopleName());

		List<String> test = new ArrayList<String>();
	}
}
