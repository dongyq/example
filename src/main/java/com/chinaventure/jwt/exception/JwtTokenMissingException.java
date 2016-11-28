package com.chinaventure.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by huaxiujun on 2016/11/24.
 * <p>
 * jwt token 缺失
 */
public class JwtTokenMissingException extends AuthenticationException {

    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
