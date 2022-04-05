package com.smxknife.java2.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author smxknife
 * 2021/7/19
 */
public class HelloImpl extends UnicastRemoteObject implements IHello {
	protected HelloImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHelloToSomeBody(String someBodyName) throws RemoteException {
		return null;
	}
}
