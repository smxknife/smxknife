package com.smxknife.energy.services.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/24
 */
@RestController
@RequestMapping("/ext/spi")
public class ExtSPIController {

	@GetMapping
	public void t1() {
		final ExtService spiService = ExtensionLoader.getExtensionLoader(ExtService.class).getDefaultExtension();
		spiService.print();

		System.out.println("-------------------------");
		final ExtService adaptiveExtension = ExtensionLoader.getExtensionLoader(ExtService.class).getAdaptiveExtension();
		// 这种运行会报错
//		adaptiveExtension.printInfo0(URL.valueOf("test://localhost/hi?ext.service=dev"));
		adaptiveExtension.printInfo0(URL.valueOf("test://localhost/hi?ext.service=prod"));
		adaptiveExtension.printInfo1(URL.valueOf("test://localhost/hi?ext.service=prod"));

		System.out.println("-------------------------");
		final ExtService adaptiveExtension2 = ExtensionLoader.getExtensionLoader(ExtService.class).getAdaptiveExtension();
		adaptiveExtension2.printInfo1(URL.valueOf("test://localhost/hi?a1=prod"));
		adaptiveExtension2.printInfo1(URL.valueOf("test://localhost/hi?a2=dev1"));
		adaptiveExtension2.printInfo1(URL.valueOf("test://localhost/hi?ext.service=dev1"));
	}
}
