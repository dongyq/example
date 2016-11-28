package com.chinaventure.service;

import com.chinaventure.entity.Ouser;

/**
 * Created by huaxiujun on 2016/11/24.
 * 用户服务
 */
public interface OuserService {

    /**
     * 注册一个用户
     *
     * @param ouser 用户
     * @return 用户id
     */
    Integer create(Ouser ouser);

    /**
     * 登录
     *
     * @param phone    电话
     * @param password 密码
     * @return 成功返回jwt token, 失败为null
     */
    String login(String phone, String password);

    /**
     * 更改密码
     *
     * @param id   用户id
     * @param opwd 旧密码
     * @param npwd 新密码
     * @return 成功返回ok, 失败为错误信息
     */
    String changePwd(Integer id, String opwd, String npwd);
}
