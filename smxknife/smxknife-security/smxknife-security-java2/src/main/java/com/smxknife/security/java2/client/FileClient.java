package com.smxknife.security.java2.client;

import com.smxknife.security.java.core.PrivilegedFileUtil;

import java.security.AccessControlException;

/**
 * cd bin
 * java -cp . -Djava.security.policy=/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-security/smxknife-security-java2/src/main/java/com/smxknife/security/java2/client/MyPolicy com/smxknife/security/java2/client/FileClient
 * @author smxknife
 * 2019-08-14
 */
public class FileClient {
	public static void main(String[] args) {
		// 打开系统安全检查开关
		System.setSecurityManager(new SecurityManager());

		String pathName = "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-security/smxknife-security-java/src/main/resources/file/SourceFile.txt";
		try {
			System.out.println(PrivilegedFileUtil.canRead(pathName));
		} catch (AccessControlException ex) {
			System.out.println("canRead =================================");
			System.out.println(ex.getPermission().getActions());
			System.out.println(ex.getPermission().getName());
			System.out.println("canRead: " + ex.getMessage());
		}

		try {
			pathName = "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-security/smxknife-security-java/src/main/resources/file/NewFile.txt";
			PrivilegedFileUtil.makeFile(pathName);
		} catch (AccessControlException ex) {
			System.out.println("makeFile =================================");
			System.out.println(ex.getPermission().getActions());
			System.out.println(ex.getPermission().getName());
			System.out.println("makeFile: " + ex.getMessage());
		}

		try {
			pathName = "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-security/smxknife-security-java/src/main/resources/file/PrivilegeFile.txt";
			PrivilegedFileUtil.doPrivilegedAction(pathName);
		} catch (AccessControlException ex) {
			System.out.println("doPrivilegedAction =================================");
			System.out.println(ex.getPermission().getActions());
			System.out.println(ex.getPermission().getName());
			System.out.println("doPrivilegedAction: " + ex.getMessage());
		}

	}
}
