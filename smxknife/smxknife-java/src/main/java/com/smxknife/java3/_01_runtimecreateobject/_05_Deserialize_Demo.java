package com.smxknife.java3._01_runtimecreateobject;

import java.io.*;

/**
 * @author smxknife
 * 2019/12/24
 */
public class _05_Deserialize_Demo {
	public static void main(String[] args) {
		_0_CommObj commObj = new _0_CommObj();

		try(FileOutputStream fos = new FileOutputStream("./_0_CommObj.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(commObj);
			System.out.println("序列化完毕，开始反序列化---------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try(FileInputStream fis = new FileInputStream("./_0_CommObj.ser");
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			Object o = ois.readObject();
			System.out.println("反序列化完毕");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
