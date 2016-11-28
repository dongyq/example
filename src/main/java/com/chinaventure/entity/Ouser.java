package com.chinaventure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by huaxiujun on 2016/11/24.
 * 用户
 */
@Entity
public class Ouser {
    @Id
    @GeneratedValue
    private Integer id;

    // 图片
    @Column(length = 100)
    private String picture;

    // 邮箱
    @Column(length = 30)
    private String email;

    // 电话
    @Column(length = 20)
    private String phone;

    // 密码
    @Column(length = 32)
    private String password;

    // 名字
    @Column(length = 20)
    private String name;

    // 性别 0男 1女
    @Column(columnDefinition = "TINYINT(1)")
    private Integer sex = 0;

    //===========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
