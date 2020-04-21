package com.yuyahong.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 19:24
 */
public class MyStringUtils {
    /**
     * uuid的生成
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 通过明文获取加密密码
     *
     * @param password
     * @return
     */
    public static String getPassword(String password) {
        return getMD5(password);
    }

    /**
     * 密码加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getPassword("123456"));
    }

}
