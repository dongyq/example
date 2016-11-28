package com.chinaventure.support;

import com.chinaventure.entity.Ouser;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Created by huaxiujun on 2016/11/24.
 * 一些本地查询的综合接口
 */
public interface NativeQuery extends PageQuery {
    /**
     * 条件查询用户
     *
     * @param user 用户属性相关
     * @param page 页数
     * @param size 长度
     * @return 以上查询信息的组合
     */
    Page<Map> userSearch(Ouser user, int page, int size);
}
