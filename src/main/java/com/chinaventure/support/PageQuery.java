package com.chinaventure.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.chinaventure.tools.Utils.array2Map;

/**
 * Created by huaxiujun on 2016/11/24.
 * 分页查询的默认接口
 */
interface PageQuery {
    /**
     * 进行page分页查询
     *
     * @param entityMgr EntityManager
     * @param sql       sql查询语句
     * @param keys      输出key项
     * @param params    参数
     * @param pr        分页参数
     * @return Map类型的Page
     */
    @SuppressWarnings("unchecked")
    default Page<Map> pageQuery(EntityManager entityMgr, String sql, String[] keys,
                                Map<String, Object> params, PageRequest pr) {
        // 生成count sql
        String csql = StringUtils.substringAfter(sql, " from ");
        csql = "select count(*) from " + StringUtils.substringBefore(csql, " order by ");

        Query query = entityMgr.createNativeQuery(sql);
        Query cquery = entityMgr.createNativeQuery(csql);
        // 设置参数
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
            cquery.setParameter(key, params.get(key));
        }
        // 设置分页
        query.setFirstResult(pr.getOffset());
        query.setMaxResults(pr.getPageSize());

        // 返回值转换为map
        List<Object[]> list = query.getResultList();
        List<Map> res = list.stream().map(os -> array2Map(os, keys)).collect(Collectors.toList());
        // 返回分页参数
        long total = Long.parseLong(cquery.getSingleResult().toString());
        return new PageImpl<>(res, pr, total);
    }
}
