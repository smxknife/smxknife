package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019/10/31
 */
public class Demo_ClassLoader_default_path {
	public static void main(String[] args) {
		System.out.println("BootstrapClassLoader - " + System.getProperty("sun.boot.class.path"));
		System.out.println("-------------------");
		System.out.println("ExtendClassLoader - " + System.getProperty("java.ext.dirs"));
		System.out.println("-------------------");
		System.out.println("AppClassLoader - " + System.getProperty("java.class.path"));

		String rest = "BootstrapClassLoader - \r\n" +
				" - /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/resources.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/sunrsasign.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jsse.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jce.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/charsets.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jfr.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/classes\r\n" +
				"-------------------\n" +
				"ExtendClassLoader - \r\n" +
				" - /Users/ShaoYun/Library/Java/Extensions:\r\n" +
				" - /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext:\r\n" +
				" - /Library/Java/Extensions:\r\n" +
				" - /Network/Library/Java/Extensions:\r\n" +
				" - /System/Library/Java/Extensions:\r\n" +
				" - /usr/lib/java\r\n" +
				"-------------------\n" +
				"AppClassLoader - \r\n" +
				" - /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/charsets.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/deploy.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/cldrdata.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/dnsns.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/jaccess.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/jfxrt.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/localedata.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/nashorn.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/sunec.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/zipfs.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/javaws.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jce.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jfr.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jfxswt.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jsse.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/management-agent.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/plugin.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/resources.jar\r\n" +
				" - :/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/tools.jar:/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes:/Users/ShaoYun/local/workstation/repository/repository/com/google/auto/service/auto-service/1.0-rc5/auto-service-1.0-rc5.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/auto/service/auto-service-annotations/1.0-rc5/auto-service-annotations-1.0-rc5.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/auto/auto-common/0.10/auto-common-0.10.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/guava/guava/27.0.1-jre/guava-27.0.1-jre.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/Users/ShaoYun/local/workstation/repository/repository/org/checkerframework/checker-qual/2.5.2/checker-qual-2.5.2.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/errorprone/error_prone_annotations/2.2.0/error_prone_annotations-2.2.0.jar:/Users/ShaoYun/local/workstation/repository/repository/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar:/Users/ShaoYun/local/workstation/repository/repository/org/codehaus/mojo/animal-sniffer-annotations/1.17/animal-sniffer-annotations-1.17.jar:/Users/ShaoYun/local/workstation/repository/repository/cglib/cglib/3.2.6/cglib-3.2.6.jar:/Users/ShaoYun/local/workstation/repository/repository/org/ow2/asm/asm/6.0/asm-6.0.jar:/Users/ShaoYun/local/workstation/repository/repository/org/apache/ant/ant/1.9.6/ant-1.9.6.jar:/Users/ShaoYun/local/workstation/repository/repository/org/apache/ant/ant-launcher/1.9.6/ant-launcher-1.9.6.jar:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar\n";
	}
}
