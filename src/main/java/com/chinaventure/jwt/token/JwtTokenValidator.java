package com.chinaventure.jwt.token;

import com.chinaventure.jwt.dto.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import org.springframework.stereotype.Component;

/**
 * Created by huaxiujun on 2016/11/24.
 * jwt token 验证
 */
@Component
public class JwtTokenValidator {

    private final JwtParser jwtParser;

    public JwtTokenValidator(JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }

    /**
     * 验证token 成功返回 jwtuser
     *
     * @param token the JWT token to parse
     * @return jwt user 或者 null
     */
    public JwtUser parseToken(String token) {
        JwtUser user = null;

        try {
            Claims body = jwtParser.parseClaimsJws(token).getBody();

            user = new JwtUser();
            user.setName(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setRoles((String) body.get("role"));

        } catch (JwtException ex) {
            // 也许是过期,或者其他一些异常 总之我们认为获取不到 token代表的user
        }
        return user;
    }
}
