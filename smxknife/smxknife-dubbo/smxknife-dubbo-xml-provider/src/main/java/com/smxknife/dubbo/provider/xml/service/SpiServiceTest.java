package com.smxknife.dubbo.provider.xml.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author smxknife
 * 2019/12/12
 */
public class SpiServiceTest {

	public static void main(String[] args) {
		// 默认的
		test1();
		// 指定spring
		test2();
		// 实现类上指定Adaptive, test3和test2不能同时执行，如果要测试test2，需要将test3注释掉
		// test3();

	}

	private static void test3() {
		ExtensionLoader<SpiService> loader = ExtensionLoader
				.getExtensionLoader(SpiService.class);
		SpiService spiService = loader.getAdaptiveExtension();

		URL url = URL.valueOf("test://localhost:8080?spi.service=spring");
		spiService.echo(url);
		System.out.println("因为这里调用的是getAdaptiveExtension");
		System.out.println("url指定的是spring，但是在OtherSpiService类上加了@Adaptive注解");
		System.out.println("那么无论url是否指定，都无效，都将使用OtherSpiService");
		System.out.println("=========== test3 other ===================");
	}

	private static void test2() {
		ExtensionLoader<SpiService> loader = ExtensionLoader
				.getExtensionLoader(SpiService.class);
		SpiService spiService = loader.getAdaptiveExtension();

		URL url = URL.valueOf("test://localhost:8080?spi.service=spring");
		spiService.echo(url);
		System.out.println("因为这里调用的是getAdaptiveExtension");
		System.out.println("url指定的是spring，返回的都是spring的实现");
		System.out.println("=========== test2 spring ===================");
	}

	public static void test1() {
		/**
		 * @SPI("java")
		 * public interface SpiService {
		 *        @Adaptive
		 *    void echo();
		 * }
		 */
		ExtensionLoader<SpiService> loader = ExtensionLoader
				.getExtensionLoader(SpiService.class);
		SpiService spiService = loader.getDefaultExtension();

		URL url = URL.valueOf("test://localhost:8080?spi.service=other");
		spiService.echo(url);
		System.out.println("因为这里调用的是getDefaultExtension，返回的是SPI里面指定的默认值java");
		System.out.println("所以，这里无论是传的哪个url，返回的都是默认的");
		System.out.println("=========== test1 默认的===================");
	}
}
