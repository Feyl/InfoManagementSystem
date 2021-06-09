package com.example.InfoManagement.utils;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 */
public class MD5Tool {
    /**
     * 根据 MD5算法获取加密后的数据
     * @param str
     * @return
     * @throws Exception
     */
    public static String getMD5(String str) throws Exception {
        String MD5 = "";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes();
        byte[] digest = md5.digest(bytes);

        for (int i = 0; i < digest.length; i++) {
            //摘要字节数组中各个字节的"十六进制"形式.
            int j = digest[i];
            j = j & 0x000000ff;
            String s1 = Integer.toHexString(j);

            if (s1.length() == 1) {
                s1 = "0" + s1;
            }
            MD5 += s1;
        }
        return MD5;
    }
}
