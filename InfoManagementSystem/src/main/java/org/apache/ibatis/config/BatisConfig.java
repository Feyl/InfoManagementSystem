package org.apache.ibatis.config;

/**
 * 用于存储数据库配置信息
 */
public class BatisConfig {
    public static String jdbcDriver;
    public static String jdbcUrl;
    public static String username;
    public static String password;
    public static Integer initConnectCount;
    public static Integer maxConnect;
    public static Integer incrementCount;
}
