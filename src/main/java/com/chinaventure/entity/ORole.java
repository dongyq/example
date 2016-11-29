package com.chinaventure.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lianlei on 2016/11/29.
 */
@Entity
@Table(name = "o_role")//指定生成表名
public class ORole {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "name" , length = 50)//指定生成后的表字段名称以及长度
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinTable(name = "o_user_role" , joinColumns = {@JoinColumn(name = "rid")} , inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<Ouser> ousers;
    public ORole() {
    }

    @Override
    public String toString() {
        return "ORole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ousers=" + ousers +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ouser> getOusers() {
        return ousers;
    }

    public void setOusers(List<Ouser> ousers) {
        this.ousers = ousers;
    }

    public ORole(Integer id, String name, List<Ouser> ousers) {

        this.id = id;
        this.name = name;
        this.ousers = ousers;
    }
}