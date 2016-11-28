package com.chinaventure.tools;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by huaxiujun on 2016/11/24.
 * 数据库sql 简单链接器
 */
public class DbHelper {

    /**
     * 链接sql
     *
     * @param sql     原sql
     * @param keyword 关键字
     * @param append  需附加的sql
     * @return 链接后的sql
     */
    public static String linkSql(String sql, String keyword, String append) {
        if (sql.equals("")) {
            return append;
        } else if (append.equals("")) {
            return sql;
        } else {
            if (!append.startsWith(" ")) {
                append = " " + append;
            }
            // where没有附加首条件的情况
            if (sql.trim().endsWith(" where")) {
                return sql + append;
            } else {
                if (!keyword.startsWith(" ")) {
                    keyword = " " + keyword;
                }
                return sql + keyword + append;
            }
        }
    }

    /**
     * 如果不为空值 则进行连接sql
     *
     * @param value   待检验的值
     * @param sql     原sql
     * @param params  参数map
     * @param keyword 关键字
     * @param append  需附加的sql
     * @return 链接后的sql
     */
    public static String ifNotEmptyLink(String value, String sql, Map<String, Object> params,
                                        String keyword, String append) {
        if (StringUtils.isNotEmpty(value)) {
            sql = linkSql(sql, keyword, append);
            String key = StringUtils.substringAfterLast(append, ":");
            if (append.contains(" like ")) {
                value = "%" + value + "%";
            }
            params.put(key, value);
        }
        return sql;
    }

    /**
     * 如果不为空值 则进行连接sql
     *
     * @param value   待检验的值
     * @param sql     原sql
     * @param params  参数map
     * @param keyword 关键字
     * @param append  需附加的sql
     * @return 链接后的sql
     */
    public static String ifNotNullLink(Object value, String sql, Map<String, Object> params,
                                       String keyword, String append) {
        if (value != null) {
            sql = linkSql(sql, keyword, append);
            String key = StringUtils.substringAfterLast(append, ":");
            params.put(key, value);
        }
        return sql;
    }
}
