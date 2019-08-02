package com.smxknife.okhttp.demo;

import okhttp3.*;

import java.io.IOException;

/**
 * @author smxknife
 * 2019-06-14
 */
public class RequestDemo {
	public int syncRequest(String url) {
		OkHttpClient client = new OkHttpClient();

		Request  request = new Request.Builder()
				.url(url)
				.build();

		try (Response response = client.newCall(request).execute()) {
			System.out.println(response.body().string());
			return response.code();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void asyncRequest(String url) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(url)
				.build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				System.out.println(response.body().string());
			}
		});
	}
}
