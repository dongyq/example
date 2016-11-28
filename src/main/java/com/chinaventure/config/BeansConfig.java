package com.chinaventure.config;

import com.chinaventure.tools.SysSeq;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by huaxiujun on 2016/11/24.
 * bean 相关初始化管理
 */
@Configuration
public class BeansConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtParser jwtParser() {
        return Jwts.parser().setSigningKey(secret);
    }

    @Bean
    public SysSeq sysSeq() {
        return new SysSeq();
    }
}
