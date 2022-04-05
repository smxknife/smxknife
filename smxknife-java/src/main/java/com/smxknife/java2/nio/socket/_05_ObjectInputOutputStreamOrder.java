package com.smxknife.java2.nio.socket;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author smxknife
 * 2020/10/8
 */
public class _05_ObjectInputOutputStreamOrder {

	// 服务端，先获取ObjectInputStream，再获取ObjectOutputStream
	@Test
	public void serverTest1() {
		try(ServerSocket serverSocket = new ServerSocket(6666);
		    Socket socket = serverSocket.accept();
		    InputStream inputStream = socket.getInputStream();
		    OutputStream outputStream = socket.getOutputStream();
			// TODO: 注意这里两个的顺序
		    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {

			UserInfo userInfo = (UserInfo)objectInputStream.readObject();
			System.out.println("服务端收到UserInfo = " + userInfo);

			UserInfo newUserInfo = new UserInfo(userInfo);
			newUserInfo.setNo(userInfo.getNo() + 1);
			objectOutputStream.writeObject(newUserInfo);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 客户端，先获取ObjectOutputStream，再获取ObjectInputStream
	@Test
	public void clientTest1() {
		try(Socket socket = new Socket("localhost", 6666);
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		// TODO: 注意这里两个的顺序
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);) {

			UserInfo newUserInfo = new UserInfo(1, "hello", 10);
			System.out.println("客户端创建UserInfo = " + newUserInfo);

			objectOutputStream.writeObject(newUserInfo);
			UserInfo userInfo = (UserInfo) objectInputStream.readObject();
			System.out.println("服务端发送的UserInfo = " + userInfo);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 客户端，先获取ObjectInputStream，再获取ObjectOutputStream，与服务端一致，造成阻塞
	@Test
	public void clientTest1_Block() {
		try(Socket socket = new Socket("localhost", 6666);
		    InputStream inputStream = socket.getInputStream();
		    OutputStream outputStream = socket.getOutputStream();
		    // TODO: 注意这里两个的顺序
		    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {

			UserInfo newUserInfo = new UserInfo(1, "hello", 10);
			System.out.println("客户端创建UserInfo = " + newUserInfo);

			objectOutputStream.writeObject(newUserInfo);
			UserInfo userInfo = (UserInfo) objectInputStream.readObject();
			System.out.println("服务端发送的UserInfo = " + userInfo);

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int no;
	private String name;
	private int age;

	public UserInfo() {}

	public UserInfo(int no, String name, int age) {
		this.no = no;
		this.name = name;
		this.age = age;
	}

	public UserInfo(UserInfo info) {
		this.no = info.no;
		this.name = info.name;
		this.age = info.age;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"no=" + no +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
