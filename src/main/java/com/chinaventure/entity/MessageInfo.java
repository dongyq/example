package com.chinaventure.entity;

import org.hibernate.annotations.Target;
import sun.security.util.Length;

import javax.persistence.*;

/**
 * Created by Alex on 2016/11/29.
 * 消息
 */
@Entity
@Table(name = "message_info")
public class MessageInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 60)
    private String title;

    @Column(length = 200)
    private String content;

    @Column
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
