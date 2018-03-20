package com.smxknife.softmarket;

import com.smxknife.softmarket.common.comm.ReqMsgType;
import org.junit.Test;

public class ReqMsgTypeEnumTest {

	@Test
	public void enumTest() {
		String type = "text";
		ReqMsgType reqMsgType = ReqMsgType.valueOf(type);
		System.out.println(reqMsgType);
	}
}
