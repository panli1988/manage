package com.lp.framework.manage.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroUtils {

    /**
     * shiro加密
     * @param credentials 密码
     * @param user 盐值
     * @return
     */
    public static String encrypt(String credentials,String user){
        String hashAlgorithmName = "MD5";//加密算法
        ByteSource salt = ByteSourceUtils.bytes(user);
        int hashIterations = 10;//加密次数
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }
}
