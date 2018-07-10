package com.smxknife.jmx.demo02.server.agent;

import com.smxknife.jmx.demo02.server.instrument.User;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class UserAgent {
	private MBeanServer mBeanServer;

	public UserAgent() {


		mBeanServer = ManagementFactory.getPlatformMBeanServer();
//		mBeanServer = MBeanServerFactory.createMBeanServer("UserAgent");

		try {
			ObjectName name = new ObjectName("UserAgent:type=User");

			User user = new User();
			user.setName("test");
			user.setId(1);
			mBeanServer.registerMBean(user, name);

//			LocateRegistry.createRegistry(1099);
//			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
//			JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mBeanServer);
//			jcs.start();
//			Thread.sleep(Long.MAX_VALUE);

		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		catch (RemoteException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
