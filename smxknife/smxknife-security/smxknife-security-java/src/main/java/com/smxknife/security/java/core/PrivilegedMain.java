package com.smxknife.security.java.core;

import java.security.AccessControlException;

/**
 * @author smxknife
 * 2019-08-14
 */
public class PrivilegedMain {
	public static void main(String[] args) {

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
