package com.chinaventure.jwt.dto;

import java.util.Date;

/**
 * Created by huaxiujun on 2016/11/24.
 * JWT 用户对象
 */
public class JwtUser {
    // id
    private Long id;

    // 名字
    private String name;

    // 角色 逗号分隔
    private String roles;

    // 失效时间
    private Date expiration;

    //===============================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
