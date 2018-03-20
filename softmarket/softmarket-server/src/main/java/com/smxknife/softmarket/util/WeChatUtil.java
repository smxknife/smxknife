package com.smxknife.softmarket.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class WeChatUtil {

	public static String signature(String token, String timestamp, String notice) {
		String[] arrays = new String[] {token, timestamp, notice};
		Arrays.sort(arrays);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arrays.length; i++) {
			builder.append(arrays[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行 sha1 加密
			byte[] digest = md.digest(builder.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		builder = null;
		return tmpStr;
	}

	public static boolean checkSignature(String signature, String timestamp, String notice, String token) {
		String tmpStr = signature(token, timestamp, notice);
		// 将 sha1 加密后的字符串可与 signature 对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			builder.append(byteToHexStr(byteArray[i]));
		}
		return builder.toString();
	}

	/**
	 * 将字节转换为十六进制字符串
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	public static Map<String, String> parseXml(HttpServletRequest request) throws IOException, DocumentException {
		// 将解析结果存储在HashMap中
		Map<String,String> map = new HashMap<>();

		// 从request中获取输入流
		try (InputStream inputStream = request.getInputStream()) {

			// 读取输入流
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);

			// 得到根元素
			Element root = document.getRootElement();
			// 得到根元素的所有子节点
			List<Element> elements = root.elements();
			// 遍历所有子节点
			for (Element element : elements) {
				map.put(element.getName(), element.getText());
			}
		}
		return map;
	}

//	public static String sendTextMsg(Map<String, String> requestMap, String content) {
//		Map<String, Object> responseMap = new HashMap<>();
//		responseMap.put(WeChatConstant.FROM_USER_NAME, requestMap.get(WeChatConstant.TO_USER_NAME));
//		responseMap.put(WeChatConstant.TO_USER_NAME, requestMap.get(WeChatConstant.FROM_USER_NAME));
//		responseMap.put(WeChatConstant.MSG_TYPE, MsgType.text.name());
//		responseMap.put(WeChatConstant.CREATE_TIME, new Date().getTime());
//		responseMap.put(WeChatConstant.CONTENT, content);
//
//		return mapToXml(responseMap);
//	}

	public static String mapToXml(Map<String, Object> map) {
		StringBuilder builder = new StringBuilder();
		builder.append("<xml>");
		mapToXml2(map, builder);
		builder.append("</xml>");
		return builder.toString();
	}

	private static void mapToXml2(Map<String, Object> map, StringBuilder builder) {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = map.get(key);
			if (value == null) value = "";
			if (ArrayList.class.isAssignableFrom(value.getClass())) {
				ArrayList list = (ArrayList) value;
				builder.append("<" + key + ">");
				for (int i = 0; i < list.size(); i++) {
					HashMap hashMap = (HashMap) list.get(i);
					mapToXml2(hashMap, builder);
				}
				builder.append("</" + key + ">");
			} else if (Map.class.isAssignableFrom(value.getClass())){
				builder.append("<" + key + ">");
				mapToXml2((Map) value, builder);
				builder.append("</" + key + ">");
			} else {
				builder.append("<" + key + "><![CDATA[" + value + "]]></" + key + ">");
			}
		}
	}
}
