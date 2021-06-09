package org.apache.ibatis.util;

import org.apache.ibatis.config.BatisConfig;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 负责执行sql语句操作数据库
 */
public class JdbcUtil {
    private static BatisConfig batisConfig;
    private static boolean autoCommit = false;
    private static Connection connection;
    private static ResourceBundle bundle;

    static {
        initConfig();
    }

    /**
     * 初始化数据库配置类的属性
     *
     * 也可以使用 java.util.Properties 处理配置文件
     */
    private static void initConfig() {
        bundle = ResourceBundle.getBundle("application");
        batisConfig.jdbcDriver = bundle.getString("config.jdbcDriver");
        batisConfig.jdbcUrl = bundle.getString("config.jdbcUrl");
        batisConfig.username = bundle.getString("config.username");
        batisConfig.password = bundle.getString("config.password");
        batisConfig.initConnectCount = Integer.valueOf(bundle.getString("config.initConnectCount"));
        batisConfig.maxConnect = Integer.valueOf(bundle.getString("config.maxConnect"));
        batisConfig.incrementCount = Integer.valueOf(bundle.getString("config.incrementCount"));
    }

    /**
     * 注册驱动
     */
    private static void loadDriver() {
        try {
            Class.forName(batisConfig.jdbcDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * 若当前连接对象无效，重新获取连接对象
     * @return
     */
    public static Connection getConnection() {
        if (isInValid(connection)) {
            loadDriver();
            try {
                connection = DriverManager.getConnection(batisConfig.jdbcUrl, batisConfig.username, batisConfig.password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 判断数据库连接对象是否为无效连接
     * @param conn
     * @return
     */
    private static boolean isInValid(Connection conn) {
        try {
            if (conn == null || conn.isClosed() || !conn.isValid(3))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取数据库执行对象（执行sql语句不带参数）
     * @return
     */
    public static Statement statement() {
        Statement stmt = null;
        Connection conn = getConnection();
        transaction();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    /**
     * 获取数据库执行对象（执行sql语句带参数）
     * @return
     */
    public static PreparedStatement preparedStatement(String sql) {
        PreparedStatement ps = null;
        Connection conn = getConnection();
        transaction();
        try {
            ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    /**
     * 设置事务（是否自动提交）
     */
    public static void transaction() {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理数据库查询语句
     * @param sql
     * @param params 传入的参数列表
     * @param c
     * @return
     */
    public static ResultSet query(String sql, List<Object> params, Connection c) {
        if (c != null) {
            connection = c;
        }
        if (sql == null || sql.trim().isEmpty() || !sql.trim().toLowerCase().startsWith("select")) {
            throw new RuntimeException("输入的语句为空，或者不是查询语句!");
        }
        ResultSet rs = null;
        try {
            if (params.size() > 0) {
                PreparedStatement ps = preparedStatement(sql);
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
                rs = ps.executeQuery();
            } else {
                Statement stmt = statement();
                rs = stmt.executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 处理数据库更新、删除、添加语句
     * @param sql
     * @param params
     * @param c
     * @return
     */
    public static Integer execute(String sql, List<Object> params, Connection c) {
        if (c != null) {
            connection = c;
        }
        if (sql == null || sql.trim().isEmpty() || sql.trim().toLowerCase().startsWith("select")) {
            throw new RuntimeException("输入的语句为空，或者输入的语句有误!");
        }
        int rs = 0;
        try {
            if (params.size() > 0) {
                PreparedStatement ps = preparedStatement(sql);
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
                rs = ps.executeUpdate();
                commit(connection);
            }else{
                Statement stmt = statement();
                rs = stmt.executeUpdate(sql);
                commit(connection);
            }
        } catch (SQLException e) {
            System.err.println(" 执行失败!"+e.getMessage());
            rollback(connection);
        }
        return rs;
    }

    /**
     * 提交事务，更新数据库
     * @param conn
     */
    private static void commit(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     * @param conn
     */
    private static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     * @param c 可关闭对象
     */
    public static void release(Closeable c) {//是否需要释放资源
        if (c != null) {
            if (c instanceof Connection || c instanceof Statement || c instanceof ResultSet) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *将JDK中的数据类型转换为对应的数据库可以处理的数据类型
     * @param o
     * @return
     */
    public static Object typeof(Object o) {
        //java.sql.Timestamp
        if (o instanceof java.util.Date) {
            return new Date(((java.util.Date) o).getTime());
        }
        if (o instanceof Character || o.getClass() == char.class) {
            return String.valueOf(o);
        }
        return o;
    }

}
