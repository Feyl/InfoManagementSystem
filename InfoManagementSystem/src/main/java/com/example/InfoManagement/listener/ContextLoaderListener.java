package com.example.InfoManagement.listener;

import com.example.InfoManagement.mapper.AdminMapper;
import com.example.InfoManagement.mapper.ClassMapper;
import com.example.InfoManagement.mapper.StudentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.ref.WeakReference;

/**
 * 用于 初始化 Mybatis相关配置及"容器" 的监听器
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    private static AdminMapper adminMapper;
    private static ClassMapper classMapper;
    private static StudentMapper studentMapper;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WeakReference<SqlSessionFactoryBuilder> builder = new WeakReference<>(new SqlSessionFactoryBuilder());
        String mapxmlPath = "mapper";
        SqlSessionFactory factory = builder.get().build(mapxmlPath);

        adminMapper = factory.getMapper(AdminMapper.class);
        classMapper = factory.getMapper(ClassMapper.class);
        studentMapper = factory.getMapper(StudentMapper.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //可以在 SqlSessionFactory中写一个方法关闭所有SqlSession中的连接
    }

    public static AdminMapper getAdminMapper() {
        return adminMapper;
    }

    public static ClassMapper getClassMapper() {
        return classMapper;
    }

    public static StudentMapper getStudentMapper() {
        return studentMapper;
    }
}
