package com.chinaventure.tools;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huaxiujun on 2016/11/24.
 * 部分常用工具
 */
public class Utils {
    public static String STR_UTF8 = "utf-8";
    public static Charset CHAR_SET_UTF8 = Charset.forName(STR_UTF8);

    /**
     * 获取MD5的Hash码(32位)。
     *
     * @param data 需要转化hash码的数据。
     * @return MD5的hash码, 异常时, 返回原数据
     */
    public static String getMD5Hash(String... data) {
        return getHash("MD5", data);
    }

    /**
     * 获取SHA1
     *
     * @param data 需要转化的数据。
     * @return SHA1的hash码, 异常时, 返回原数据
     */
    public static String getSHA1(String... data) {
        return getHash("SHA-1", data);
    }

    /**
     * 获取加密信息
     *
     * @param data 需要转化的数据。
     * @return hash码, 异常时, 返回原数据
     */
    public static String getHash(String algorithm, String... data) {
        try {
            // 签名生成
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //update each data
            for (String elem : data) {
                md.update(elem.getBytes());
            }

            byte[] digest = md.digest();
            //变换成16进制表示
            StringBuilder buffer = new StringBuilder();
            for (byte aDigest : digest) {
                String tmp = Integer.toHexString(aDigest & 0xff);
                if (tmp.length() == 1) {
                    buffer.append('0');
                }
                buffer.append(tmp);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return Arrays.toString(data);
        }
    }

    /**
     * 获取系统环境变量
     *
     * @param name key
     * @param def  获取不到时的默认值
     * @return 对应的环境变量
     */
    public static String getSysEnv(String name, String def) {
        String value = System.getenv(name);
        if (value == null) {
            value = def;
        }
        return value;
    }

    /**
     * 根据给定的key 将object[] 放入map
     *
     * @param objs object数组
     * @param keys key数组
     * @return map
     */
    @SuppressWarnings("unchecked")
    public static Map array2Map(Object[] objs, String... keys) {
        Map map = new HashMap();
        for (int ix = 0; ix < keys.length; ix++) {
            map.put(keys[ix], objs[ix]);
        }
        return map;
    }
}
