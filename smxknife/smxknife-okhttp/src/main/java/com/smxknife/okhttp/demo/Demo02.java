package com.smxknife.okhttp.demo;

import okhttp3.*;

import java.io.IOException;

/**
 * @author smxknife
 * 2019-06-14
 */
public class Demo02 {
	public static void main(String[] args) throws IOException {

//		MediaType JSON
//				= MediaType.get("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();

		RequestBody requestBody = new FormBody.Builder()
				.add("username", "admin")
				.add("password", "******")
				.build();

		Request request = new Request.Builder()
				.url("http://101.37.78.224:8081/login")
				.post(requestBody)
				.build();

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.code());
			System.out.println(response.body().string());
		}
	}
}
