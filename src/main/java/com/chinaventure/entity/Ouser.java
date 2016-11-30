package com.chinaventure.entity;

import javax.persistence.*;

/**
 * Created by huaxiujun on 2016/11/24.
 * 用户
 */
@Entity
public class Ouser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //设置id为自增
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


    public Ouser() {
    }

    public Ouser(String picture, String email, String phone, String password, String name, Integer sex) {
        this.picture = picture;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Ouser{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
