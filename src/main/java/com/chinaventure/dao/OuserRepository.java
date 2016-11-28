package com.chinaventure.dao;

import com.chinaventure.entity.Ouser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huaxiujun on 2016/11/24.
 * 用户dao
 */
public interface OuserRepository extends JpaRepository<Ouser, Integer> {
    // 通过phone查找ouser 只需要按规则写出方法名即可(spring-data-jpa的方便之处)
    Ouser findByPhone(String phone);
}
