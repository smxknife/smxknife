package com.smxknife.softmarket.service;

import com.smxknife.softmarket.domain.AccessToken;

public interface AccessTokenService {

    AccessToken accessToken();

    String flushAccessToken(String accessToken);
}
