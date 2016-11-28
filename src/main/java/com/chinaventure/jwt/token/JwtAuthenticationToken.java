package com.chinaventure.jwt.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by huaxiujun on 2016/11/24.
 * token 的携带者
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        // 目前没有用到 覆写为null
        return null;
    }

    @Override
    public Object getPrincipal() {
        // 目前没有用到 覆写为null
        return null;
    }
}
