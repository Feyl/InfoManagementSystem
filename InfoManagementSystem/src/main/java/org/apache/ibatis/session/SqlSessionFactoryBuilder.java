package org.apache.ibatis.session;

/**
 * 创建 SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(String packageName){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactory.getInstance();
        sqlSessionFactory.build(packageName);
        return sqlSessionFactory;
    }
}
