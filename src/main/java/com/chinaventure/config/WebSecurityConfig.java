package com.chinaventure.config;

import com.chinaventure.jwt.JwtEntryPoint;
import com.chinaventure.jwt.JwtProvider;
import com.chinaventure.jwt.JwtSuccessHandler;
import com.chinaventure.jwt.JwtTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * Created by huaxiujun on 2016/11/24.
 * web 鉴权配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtEntryPoint unauthorizedHandler;

    private final JwtProvider jwtProvider;

    public WebSecurityConfig(JwtEntryPoint unauthorizedHandler, JwtProvider authenticationProvider) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtProvider = authenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(jwtProvider));
    }

    @Bean
    public JwtTokenFilter jwtTokenFilterBean() throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter();
        jwtTokenFilter.setAuthenticationManager(authenticationManager());
        jwtTokenFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return jwtTokenFilter;
    }

    /**
     * 关于自定义filter相关覆写方法执行2次的问题,查到如下解释,所以做了相关处理
     * If you are using Spring Boot, any GenericFilterBean (OncePerRequestFilter is one) in the context
     * will be automatically added to the filter chain.
     * Meaning the configuration you have above will include the same filter twice.
     * The easiest workaround for this is to define a FilterRegistrationBean in the context,
     * and have it disabled:
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(final JwtTokenFilter filter) {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 关闭CSRF, jwt token的情况不需要
                .csrf().disable()
                // /auth/路径下的访问都需要鉴权
                .authorizeRequests()
                .antMatchers("/auth/**").authenticated()
                .and()
                // 鉴权失败的处理
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                // no session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // JWT filter
        httpSecurity
                .addFilterBefore(jwtTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 关闭页面缓存
        httpSecurity.headers().cacheControl();
    }
}
