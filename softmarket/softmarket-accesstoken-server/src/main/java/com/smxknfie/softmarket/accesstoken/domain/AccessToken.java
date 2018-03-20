package com.smxknfie.softmarket.accesstoken.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccessToken {

    @Id
    private Long id = 1L;

    private String accessToken;

    private String expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
