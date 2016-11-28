package com.chinaventure.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by huaxiujun on 2016/11/24.
 * 鉴权不通过时的覆盖处理
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -3816299929230252502L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // REST 资源不进行跳转 直接返回401
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
