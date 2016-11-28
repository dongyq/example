package com.chinaventure.jwt;

import com.chinaventure.jwt.dto.JwtUser;
import com.chinaventure.jwt.exception.JwtTokenMalformedException;
import com.chinaventure.jwt.token.AuthenticatedUser;
import com.chinaventure.jwt.token.JwtAuthenticationToken;
import com.chinaventure.jwt.token.JwtTokenValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by huaxiujun on 2016/11/24.
 * jwt 鉴权提供者
 */
@Component
public class JwtProvider extends AbstractUserDetailsAuthenticationProvider {

    // jwt 验证器
    private final JwtTokenValidator jwtTokenValidator;

    public JwtProvider(JwtTokenValidator jwtTokenValidator) {
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtUser user = jwtTokenValidator.parseToken(token);

        if (user == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());

        return new AuthenticatedUser(user.getId(), user.getName(), token, authorityList);
    }
}
