package com.chinaventure.rest;

import com.chinaventure.base.StandardResult;
import com.chinaventure.dao.OuserRepository;
import com.chinaventure.entity.Ouser;
import com.chinaventure.service.OuserService;
import com.chinaventure.support.NativeQuery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by huaxiujun on 2016/11/24.
 * 用户路由
 */
@RestController
public class OuserRouter {
    private final OuserRepository ouserRepo;
    private final OuserService ouserService;
    private final NativeQuery nativeQuery;

    public OuserRouter(OuserRepository ouserRepo, OuserService ouserService, NativeQuery nativeQuery) {
        this.ouserRepo = ouserRepo;
        this.ouserService = ouserService;
        this.nativeQuery = nativeQuery;
    }

    /**
     * 创建一个用户
     *
     * @param ouser {picture”: 图片, “email”: 邮箱, “phone”: 电话,
     *              ”name”: 名字,  ”sex”: 性别 0男 1女, "password": 密码}
     * @return 标准返回值, 插入的ouser id
     */
    @RequestMapping(path = "/ouser", method = RequestMethod.POST)
    public StandardResult createOuser(@RequestBody Ouser ouser) {
        return new StandardResult(ouserService.create(ouser));
    }

    /**
     * 获取一个用户
     *
     * @param id ouser id
     * @return 用户信息
     */
    @RequestMapping(path = "/auth/ouser/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('OUSER')")
    public StandardResult getOuser(@PathVariable Integer id) {
        Ouser ouser = ouserRepo.findOne(id);
        ouser.setPassword(null);
        return new StandardResult(ouser);
    }

    /**
     * 更新一个用户信息
     *
     * @param ouser 完整的用户信息
     * @return 标准返回值
     */
    @RequestMapping(path = "/auth/ouser", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('OUSER') AND hasRole('ADMIN')")
    public StandardResult updateOuser(@RequestBody Ouser ouser) {
        Ouser old = ouserRepo.findOne(ouser.getId());
        ouser.setPassword(old.getPassword());
        ouserRepo.saveAndFlush(ouser);
        return new StandardResult(null);
    }

    /**
     * 登录
     *
     * @param phone    电话
     * @param password 密码
     * @return 标准返回值
     */
    @RequestMapping(path = "/ouser/login", method = RequestMethod.POST)
    public StandardResult login(@RequestParam String phone, @RequestParam String password) {
        String token = ouserService.login(phone, password);
        StandardResult result;
        if (token != null) {
            result = new StandardResult(token);
        } else {
            result = new StandardResult(1001, "login failed.");
        }
        return result;
    }

    /**
     * 修改密码
     *
     * @param id   用户id
     * @param opwd 旧密码
     * @param npwd 新密码
     * @return 标准返回值
     */
    @RequestMapping(path = "/auth/ouser/cpw", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('OUSER')")
    public StandardResult changePwd(@RequestParam Integer id, @RequestParam String opwd, @RequestParam String npwd) {
        String res = ouserService.changePwd(id, opwd, npwd);
        StandardResult result;
        if (res.equals("ok")) {
            result = new StandardResult(res);
        } else {
            result = new StandardResult(1002, res);
        }
        return result;
    }

    /**
     * 条件查询报名用户
     *
     * @param user 查询条件
     * @param page 页数
     * @param size 长度
     * @return 分页报名用户
     */
    @RequestMapping(path = "/auth/ouser/search", method = RequestMethod.GET)
    public StandardResult getMembers(@ModelAttribute Ouser user, int page, int size) {
        return new StandardResult(nativeQuery.userSearch(user, page, size));
    }

    //获取所用用户信息
    @RequestMapping(path = "/auth/users",method = RequestMethod.GET)
    public StandardResult getUsers(){
        List<Ouser> all = ouserRepo.findAll();
        return new StandardResult(all);
    }
    
}