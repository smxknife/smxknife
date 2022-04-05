package com.smxknife.cloud.netflix.user.service;

import com.smxknife.cloud.netflix.user.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author smxknife
 * 2021/5/3
 */
@FeignClient(name = "user-provider", path = "/user", fallback = UserProviderFallback.class)
public interface FeignHystrixUserApi extends UserApi {
}
