package com.smxknife.jmx.demo02.client;

import com.smxknife.jmx.demo02.server.agent.UserAgent;
import com.smxknife.jmx.demo02.server.instrument.UserMBean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

public class ClientBoot {
	public static void main(String[] args) {
		try {
			UserAgent userAgent = new UserAgent();
			System.setProperty("com.sun.management.jmxremote.port", "9999");
			System.setProperty("com.sun.management.jmxremote.ssl", "false");
			System.setProperty("com.sun.management.jmxremote.authenticate", "false");

			ClientListener listener = new ClientListener();

			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			MBeanServerConnection mBeanServerConnection = jmxc.getMBeanServerConnection();

			// domains
			String[] domains = mBeanServerConnection.getDomains();
			System.out.println(domains);

			for(int i = 0; i < domains.length; i++) {
				System.out.println("domain[" + i + "]=" + domains[i] );
			}

			// object names
			Set<ObjectName> objectNames = mBeanServerConnection.queryNames(null, null);
			for (ObjectName objName : objectNames) {
				System.out.println("=========== " + objName + " ============");
				MBeanInfo mBeanInfo = mBeanServerConnection.getMBeanInfo(objName);
				System.out.println("[Attributes]");
				for (MBeanAttributeInfo attr : mBeanInfo.getAttributes()) {
					Object value = null;
					try {
						value = attr.isReadable() ? mBeanServerConnection.getAttribute(objName, attr.getName()) : "";
					} catch (Exception e) {
						value = e.getMessage();
					}
					System.out.println(attr.getName() + ":" + value);
				}
				System.out.println("[Operations]");
				for (MBeanOperationInfo oper : mBeanInfo.getOperations()) {
					System.out.println(oper.getName() + ":" + oper.getDescription());
				}
				System.out.println("[Notifications]");
				for (MBeanNotificationInfo notice : mBeanInfo.getNotifications()) {
					System.out.println(notice.getName() + ":" + notice.getDescription());
				}
			}

			ObjectName name = new ObjectName("UserAgent:type=User");
			mBeanServerConnection.addNotificationListener(name, listener, null, null);

			mBeanServerConnection.setAttribute(name, new Attribute("Id",1234567));
			mBeanServerConnection.setAttribute(name, new Attribute("Name", "finish"));

			Object userId = mBeanServerConnection.getAttribute(name, "Id");
			Object userName = mBeanServerConnection.getAttribute(name, "Name");

			System.out.printf("userId=%s, userName=%s", userId, userName);

			UserMBean userMBean = MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnection, name, UserMBean.class, false);
			userMBean.setPassword("pppppppp");
			userMBean.printUserInfo();

			mBeanServerConnection.invoke(name, "printUserInfo", null, null);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class ClientListener implements NotificationListener {

		public void handleNotification(Notification notification,
		                               Object handback) {
			echo("\nReceived notification:");
			echo("\tClassName: " + notification.getClass().getName());
			echo("\tSource: " + notification.getSource());
			echo("\tType: " + notification.getType());
			echo("\tMessage: " + notification.getMessage());
			if (notification instanceof AttributeChangeNotification) {
				AttributeChangeNotification acn =
						(AttributeChangeNotification) notification;
				echo("\tAttributeName: " + acn.getAttributeName());
				echo("\tAttributeType: " + acn.getAttributeType());
				echo("\tNewValue: " + acn.getNewValue());
				echo("\tOldValue: " + acn.getOldValue());
			}
		}

		private void echo(String s) {
			System.out.println(s);
		}
	}
}
