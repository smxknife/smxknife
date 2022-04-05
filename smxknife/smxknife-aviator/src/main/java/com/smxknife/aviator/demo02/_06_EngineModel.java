package com.smxknife.aviator.demo02;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;

/**
 * @author smxknife
 * 2020/9/8
 */
public class _06_EngineModel {
	public static void main(String[] args) {
		// 默认情况下， AviatorScript 的运行模式是运行期优化优先，
		// 会在编译阶段做更多优化，你可以通过选项 Options.OPTIMIZE_LEVEL 来修改，
		// 默认是 AviatorEvaluator.EVAL
		AviatorEvaluator.getInstance().setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE); // 这样就修改为编译优先模式。
	}
}
