package com.smxknife.jmh.demo02;

import lombok.Getter;
import lombok.Setter;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

/**
 * @author smxknife
 * 2018-12-19
 */
@Getter
@Setter
@State(Scope.Thread)
public class MyState {

	public int a = 1;
	public int b = 2;

	@Setup
	public void doSetup() {
		System.out.println("setup");
	}

	@TearDown
	public void doTearDown() {
		System.out.println("tearDown");
	}
}
