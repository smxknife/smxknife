package com.smxknife.flink.ceo.demo.loginfail;

import org.apache.flink.cep.PatternSelectFunction;

import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/8/31
 */
public class LoginFailMatch implements PatternSelectFunction<LoginEvent, Warning> {

	@Override
	// 检测到所有的事件序列存成Map，相当于第一次的firstFail对应的LoginEvent和nextFail对应的LoginEvent
	public Warning select(Map<String, List<LoginEvent>> map) throws Exception {
//		System.out.println("    | " + map);
		// 从map按照名称取出对应的事件
		// firstFail
		List<LoginEvent> firstFail = map.get("firstFail");
		LoginEvent firstFailEvent = firstFail.iterator().next();
		// nextFail
		List<LoginEvent> nextFail = map.get("nextFail");
		LoginEvent nextFailEvent = nextFail.iterator().next();
		return new Warning(firstFailEvent.getUserId(), firstFailEvent.getTimestamp(), nextFailEvent.getTimestamp(), "login fail");
	}
}
