package com.smxknife.java2.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author smxknife
 * 2021/7/19
 */
public interface IHello extends Remote {
	String sayHelloToSomeBody(String someBodyName) throws RemoteException;
}
