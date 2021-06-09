package org.apache.ibatis.session;

import org.apache.ibatis.Function;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.util.JdbcUtil;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.*;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SqlSessionFactory {
    private static volatile SqlSessionFactory instance;
    private ClassLoader classLoader;
    private String mapperPackage;
    private List<String> mapperxmls;
    private List<SqlSession> sqlSessions;
    private Map<String, Function> functionMap;
    private int maxConnect = 100; //最大链接数量
    private int incrementCount = 10; //数量递增梯度值

    private SqlSessionFactory() {
    }

    public static SqlSessionFactory getInstance() {//单例模式：懒汉式/双重校验锁
        if (instance == null) {
            synchronized (SqlSessionFactory.class) {
                if (instance == null)
                    instance = new SqlSessionFactory();
            }
        }
        return instance;
    }

    /**
     * 初始化属性 mapperxmls、sqlSessions、functionMap
     */
    private void init() {
        this.mapperxmls = new CopyOnWriteArrayList<>();
        this.sqlSessions = new CopyOnWriteArrayList<>();
        this.functionMap = new ConcurrentHashMap<>();

        doScanClass(mapperPackage);
        initMapper();
    }

    /**
     * 初始化属性 mapperPackage（用于存储所有 mapper.xml的根目录）
     * 初始化属性 classLoader为系统类加载器
     *
     * @param packageName
     */
    public void build(String packageName) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();//获取系统类加载器
        }
        mapperPackage = packageName;
    }

    /**
     * 获取SqlSession对象
     *
     * @return
     */
    public SqlSession getSqlSession() {
        if (functionMap == null) {
            init();
        }
        while (true) {
            for (SqlSession sqlSession : sqlSessions) {
                if (!sqlSession.isUse()) {
                    Connection conn = sqlSession.getConnection();
                    try {
                        if (!conn.isValid(3)) {
                            sqlSession.setConnection(JdbcUtil.getConnection());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    sqlSession.setUse(true);
                    return sqlSession;
                }
            }
            if (sqlSessions.size() <= maxConnect - incrementCount) {
                createSqlSession(incrementCount);
            } else if (sqlSessions.size() > maxConnect - incrementCount && sqlSessions.size() < maxConnect) {
                createSqlSession(maxConnect - sqlSessions.size());
            }
        }
    }

    /**
     * 创建参数值数量的 SqlSession对象
     *
     * @param count
     */
    private void createSqlSession(int count) {
        if (maxConnect > 0 && maxConnect < sqlSessions.size()) {
            throw new RuntimeException("超出最大连接数!");
        }
        for (int i = 0; i < count; i++) {
            sqlSessions.add(new SqlSession(this, JdbcUtil.getConnection(), false));
        }
    }


    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MapperProxy(this.getSqlSession()));
    }

    /**
     * 获取一个 Executor执行对象
     *
     * @param sqlSession
     * @return
     */
    public Executor getExecutor(SqlSession sqlSession) {
        return new Executor(functionMap, sqlSession);
    }

    /**
     * 扫描mapper接口，并将mapper接口的包路径转换为mapper接口相应的xml文件的路径存储到mapperxmls中
     *
     * @param mapperPackage
     */
    private void doScanClass(String mapperPackage) {
        URL url = this.getClass().getResource("/" + mapperPackage.replace(".", "/"));

        File file = new File(url.getPath());
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {//深度优先遍历
                doScanClass(mapperPackage + "." + f.getName());
            } else {
                this.mapperxmls.add(f.getPath());
            }
        }
    }


    /**
     * 判断是否存在 mapper.xml配置文件，
     * 如果存在 mapper.xml配置文件，则调用getMapper(String xmlPath)
     * 将 mapper.xml配置文件的信息封装到Function对象中并进行存储
     */
    private void initMapper() {
        if (mapperxmls.isEmpty())
            return;
        for (String mapperxmlPath : mapperxmls) {
            if (mapperxmlPath.endsWith(".xml"))
                getMapper(mapperxmlPath);
        }
    }

    /**
     * 将 mapper.xml文件中的sql相关的信息抽取
     * 并封装到 Function对象中
     *
     * @param mapperxmlPath
     */
    private void getMapper(String mapperxmlPath) {
        SAXReader reader = new SAXReader();
        reader.setValidation(false);//不对.xml文件进行联网校验
        reader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
            }
        });

        try {
            Element root = reader.read(new FileInputStream(mapperxmlPath)).getRootElement();
            for (Iterator iter = root.elementIterator(); iter.hasNext(); ) {
                Element elem = (Element) iter.next();
                Function fnct = new Function();
                fnct.setSql(elem.getText().trim());
                fnct.setSqlType(elem.getName().trim());
                fnct.setFunctionName(elem.attributeValue("id"));
                fnct.setParamType(elem.attributeValue("parameterType"));
                fnct.setReturnType(elem.attributeValue("resultType"));
                addMapper(fnct);
            }
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 Function对象添加到 functionMap
     *
     * @param fnct
     */
    private void addMapper(Function fnct) {
        functionMap.put(fnct.getFunctionName(), fnct);
    }
}
