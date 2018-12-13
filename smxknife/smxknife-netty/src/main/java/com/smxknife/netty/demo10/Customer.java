package com.smxknife.netty.demo10;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author smxknife
 * 2018-12-10
 */
@Getter
@Setter
public class Customer {
	private long customerNumber;
	private String firstName;
	private String lastName;
	private List<String> middleNames;
}
