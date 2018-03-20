package com.smxknfie.softmarket.accesstoken.service;


import com.smxknfie.softmarket.accesstoken.domain.AccessToken;

public interface AccessTokenService {

    String accessToken();

    String flushAccessToken(String accessToken);

    String flushAccessToken(AccessToken accessToken);
}
