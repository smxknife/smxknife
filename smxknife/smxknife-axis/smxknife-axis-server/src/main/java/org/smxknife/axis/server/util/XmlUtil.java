package org.smxknife.axis.server.util;

import java.util.Map;

/**
 * @author smxknife
 * 2019/10/18
 */
public class XmlUtil {
	public static String makeXML(String methodName, Map<String, Object> todoInfo) {
		StringBuffer sb = new StringBuffer();

		String xmlHeader = "<?xml version=\"1.0\" encoding=\"GB2312\"?>\n" +
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
				"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
				"  <soapenv:Body>\n" +
				"    <" + methodName + ">\n";

		sb.append(xmlHeader);

		for (String key : todoInfo.keySet()) {
			sb.append("      <" + key + ">");
			if (null == todoInfo.get(key)) {
				sb.append(todoInfo.get(key));
			} else {
				//特殊字符需要转换
				sb.append(todoInfo.get(key).toString().replaceAll("&", "&amp;"));
			}
			sb.append("</" + key + ">");
			sb.append("\r\n");
		}
		sb.append("    </" + methodName + ">\n" +
				"  </soapenv:Body>\n" +
				"</soapenv:Envelope>");
		return sb.toString();
	}
}
