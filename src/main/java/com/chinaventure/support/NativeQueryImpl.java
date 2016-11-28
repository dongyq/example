package com.chinaventure.support;

import com.chinaventure.entity.Ouser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

import static com.chinaventure.tools.DbHelper.ifNotEmptyLink;
import static com.chinaventure.tools.DbHelper.ifNotNullLink;

/**
 * Created by huaxiujun on 2016/11/24.
 * 一些本地查询的综合接口
 */
@Service
public class NativeQueryImpl implements NativeQuery {

    private final EntityManager entityMgr;
    private final String[] memberKeys = {"picture", "email", "phone", "name"};

    public NativeQueryImpl(EntityManager entityMgr) {
        this.entityMgr = entityMgr;
    }

    /**
     * 条件查询报名用户
     *
     * @param user 用户属性相关
     * @param page 页数
     * @param size 长度
     * @return 以上查询信息的组合
     */
    @Override
    public Page<Map> userSearch(Ouser user, int page, int size) {
        // sql
        String sql = "select u.picture,u.email,u.phone,u.name from ouser u where";

        // 分页请求
        PageRequest pr = new PageRequest(page, size);
        // 参数
        Map<String, Object> params = new HashMap<>();
        // 判断空相关参数
        sql = ifNotEmptyLink(user.getName(), sql, params, "and", "u.name like :name");
        sql = ifNotEmptyLink(user.getPhone(), sql, params, "and", "u.phone like :phone");

        sql = ifNotNullLink(user.getSex(), sql, params, "and", "u.sex = :sex");

        // 这里的原生查询也是可以直接映射到 user 对象上的, 这里之所以用map
        // 是为了展示比如联合查询等需要展示的类VO 的map 情况
        return pageQuery(entityMgr, sql, memberKeys, params, pr);
    }
}
