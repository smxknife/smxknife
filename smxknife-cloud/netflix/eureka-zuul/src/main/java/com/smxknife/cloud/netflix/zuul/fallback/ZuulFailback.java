package com.smxknife.cloud.netflix.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author smxknife
 * 2021/5/28
 */
@Component
public class ZuulFailback implements FallbackProvider {
	@Override
	public String getRoute() {
		// * 代表对所有的服务生效
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 0;
			}

			@Override
			public String getStatusText() throws IOException {
				return "xxxxxx";
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("failback".getBytes(StandardCharsets.UTF_8));
			}

			@Override
			public HttpHeaders getHeaders() {
				return HttpHeaders.EMPTY;
			}
		};
	}
}
