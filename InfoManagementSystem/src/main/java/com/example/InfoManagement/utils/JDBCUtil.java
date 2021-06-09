package com.example.InfoManagement.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 数据库工具类
 */
public class JDBCUtil {

    private JDBCUtil() {
    }

//Druid数据库连接池方式:

    //1.定义成员变量 DataSource
    private static DataSource dataSource;

    static {
        try {
            //加载配置文件
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取DataSource
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     *
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    /**
     * 释放资源
     *
     * @param rs 待关闭的数据查询结果集
     * @param stmt 待关闭的数据库执行对象
     * @param conn 待归还的数据库连接对象
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt, Connection conn) {
        close(null,stmt,conn);
    }


    /**
     * 获取数据库连接池对象
     * @return 数据库连接池对象
     */
    public static DataSource getDataSource(){
        return dataSource;
    }


//普通方式:
    /*private static ResourceBundle bundle = ResourceBundle.getBundle("druid");

    static{
        try {
            Class.forName(bundle.getString("driverClassName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = bundle.getString("url");
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt,Connection conn){
        close(null,stmt,conn);
    }*/
}
