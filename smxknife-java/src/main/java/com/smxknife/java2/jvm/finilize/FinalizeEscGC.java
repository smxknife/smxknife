package com.smxknife.java2.jvm.finilize;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/1/20
 */
public class FinalizeEscGC {

	private static FinalizeEscGC SAVE_HOOK = null;

	public static void main(String[] args) throws InterruptedException {
		FinalizeEscGC fgc = new FinalizeEscGC();

		SAVE_HOOK = null;
		System.gc();
		TimeUnit.SECONDS.sleep(5);

		if (Objects.nonNull(SAVE_HOOK)) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("第一次回收就挂了");
		}

		// ----------------------

		SAVE_HOOK = null;
		System.gc();
		TimeUnit.SECONDS.sleep(6);

		if (Objects.nonNull(SAVE_HOOK)) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("第二次回收就挂了");
		}

	}


	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method execute");
		SAVE_HOOK = this;
	}

	private void isAlive() {
		System.out.println("我还活着");
	}
}
