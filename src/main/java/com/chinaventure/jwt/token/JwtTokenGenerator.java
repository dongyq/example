package com.chinaventure.jwt.token;

import com.chinaventure.jwt.dto.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by huaxiujun on 2016/11/24.
 * jwt token 创建类
 */
@Component
public class JwtTokenGenerator {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * 创建jwt token
     *
     * @param user 创建token 的用户
     * @return jwt token
     */
    public String generateToken(JwtUser user) {
        Claims claims = Jwts.claims().setSubject(user.getName());
        claims.put("userId", user.getId() + "");
        claims.put("role", user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(user.getExpiration())
                .compact();
    }
}
