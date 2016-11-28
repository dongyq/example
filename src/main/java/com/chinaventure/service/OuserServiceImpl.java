package com.chinaventure.service;

import com.chinaventure.dao.OuserRepository;
import com.chinaventure.entity.Ouser;
import com.chinaventure.jwt.dto.JwtUser;
import com.chinaventure.jwt.token.JwtTokenGenerator;
import com.chinaventure.tools.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by huaxiujun on 2016/11/24.
 * 用户服务
 */
@Service
public class OuserServiceImpl implements OuserService {

    @Value("${ouser.pw.key}")
    private String pwKey;

    private final OuserRepository ouserRepo;
    private final JwtTokenGenerator jwtTokenGenerator;
    
    public OuserServiceImpl(OuserRepository ouserRepo, JwtTokenGenerator jwtTokenGenerator) {
        this.ouserRepo = ouserRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    /**
     * 注册一个用户
     *
     * @param ouser 用户
     * @return 用户id
     */
    @Override
    @Transactional
    public Integer create(Ouser ouser) {
        String password = ouser.getPassword();
        ouser.setPassword(null);
        // insert
        Ouser res = ouserRepo.save(ouser);
        // 根据id加盐
        Integer id = res.getId();
        ouser.setPassword(saltPassword(id, password));
        // 更新密码
        ouserRepo.save(ouser);
        return id;
    }

    /**
     * 登录
     *
     * @param phone    电话
     * @param password 密码
     * @return 成功返回jwt token, 失败为null
     */
    @Override
    public String login(String phone, String password) {
        String res = null;
        Ouser ouser = ouserRepo.findByPhone(phone);
        if (ouser != null) {
            password = saltPassword(ouser.getId(), password);
            if (ouser.getPassword().equals(password)) {
                JwtUser user = new JwtUser();
                user.setId(ouser.getId().longValue());
                user.setName(ouser.getName());
                user.setRoles("ROLE_OUSER");
                // 过期时间1天
                user.setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000));
                res = jwtTokenGenerator.generateToken(user);
            }
        }
        return res;
    }

    /**
     * 更改密码
     *
     * @param id   用户id
     * @param opwd 旧密码
     * @param npwd 新密码
     * @return 成功返回ok, 失败为错误信息
     */
    @Override
    public String changePwd(Integer id, String opwd, String npwd) {
        String res = "ok";
        Ouser ouser = ouserRepo.findOne(id);
        if (ouser != null) {
            String password = saltPassword(ouser.getId(), opwd);
            if (ouser.getPassword().equals(password)) {
                password = saltPassword(ouser.getId(), npwd);
                ouser.setPassword(password);
                ouserRepo.saveAndFlush(ouser);
            } else {
                res = "Wrong old password";
            }
        }
        return res;
    }

    /**
     * 给用户密码加盐
     *
     * @param id       用户id
     * @param password 密码
     * @return 加盐后的密码
     */
    private String saltPassword(Integer id, String password) {
        return Utils.getMD5Hash(password, id.toString(), pwKey);
    }
}
