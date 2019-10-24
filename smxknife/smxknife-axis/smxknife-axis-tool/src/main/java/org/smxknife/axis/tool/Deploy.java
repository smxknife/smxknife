package org.smxknife.axis.tool;

import org.apache.axis.client.AdminClient;

/**
 * @author smxknife
 * 2019/10/18
 */
public class Deploy {
	public static void main(String[] args) throws Exception {
		AdminClient client = new AdminClient();
		String[] params = new String[]{
				"-lhttp://localhost:12345/server/services/UserService",
				"-lhttp://localhost:12345/server/services/PasswordService",
				"/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-axis/smxknife-axis-server/target/classes/deploy.wsdd"
		};
		client.process(params);
	}
}
