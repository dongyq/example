package com.chinaventure.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by huaxiujun on 2016/11/24.
 * <p>
 * jwt token 无效
 */
public class JwtTokenMalformedException extends AuthenticationException {
    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
