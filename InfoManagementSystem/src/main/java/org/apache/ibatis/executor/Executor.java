package org.apache.ibatis.executor;


import org.apache.ibatis.Function;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.util.JdbcUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sql执行对象（底层调用 JdbcUtil的方法）
 */
public class Executor {
    private SqlSession sqlSessionProxy;
    private Map<String, Function> functionMap;
    private static String regex = "\\#\\{(.+?)\\}"; //xml中的sql语句中放置参数（实体的属性）对应的正则表达式
    private static Pattern pattern = Pattern.compile(regex);

    public Executor(Map<String, Function> fnctMap, SqlSession sqlSession) {
        this.sqlSessionProxy = sqlSession;
        this.functionMap = fnctMap;
    }

    public <T> T run(Method method, Object[] args, Connection conn) {
        if (method.getAnnotations().length != 0 && method.getAnnotations().length != 1) {
            throw new RuntimeException("该方法只能有一个注解");
        }

        if (method.getAnnotations().length == 1) {
            Function fnct = getFunctionByAnnotation(method);
            functionMap.put(fnct.getFunctionName(), fnct);
        }
        Function fnct = functionMap.get(method.getName());
        if (fnct == null) {
            throw new RuntimeException("注解或Xml配置文件有误");
        }
        fnct.setParamMap(getParamsMap(method, args));
        List<Object> arglist = argsList(fnct);
        String sql = fnct.getSql().replaceAll(regex, "?");

        sqlSessionProxy.setUse(true);
        if (!isQuery(fnct.getSqlType())) {
            return (T) JdbcUtil.execute(sql, arglist, conn);
        }
        ResultSet rs = JdbcUtil.query(sql, arglist, conn);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fnct.getReturnType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) handleResult(rs, clazz);
    }


    /**
     * 将 Mapper接口的方法封装成 Function对象并返回
     *
     * @param method
     * @return
     */
    private Function getFunctionByAnnotation(Method method) {
        Function fnct = new Function();
        if (method.isAnnotationPresent(Insert.class)) {
            Insert insert = method.getAnnotation(Insert.class);
            fnct.setSql(insert.value());
            fnct.setSqlType("insert");
        } else if (method.isAnnotationPresent(Delete.class)) {
            Delete del = method.getAnnotation(Delete.class);
            fnct.setSql(del.value());
            fnct.setSqlType("delete");
        } else if (method.isAnnotationPresent(Update.class)) {
            Update update = method.getAnnotation(Update.class);
            fnct.setSql(update.value());
            fnct.setSqlType("update");
        } else if (method.isAnnotationPresent(Select.class)) {
            Select select = method.getAnnotation(Select.class);
            fnct.setSql(select.value());
            fnct.setSqlType("select");
        }
        fnct.setFunctionName(method.getName());
        fnct.setParamType("");
        fnct.setReturnType(method.getReturnType().getName());
        return fnct;
    }

    /**
     * 将方法的形参名或形参位置(下标)，与传入的实参一一映射并返回
     *
     * @param method
     * @param args
     * @return
     */
    private Map<String, Object> getParamsMap(Method method, Object[] args) {
        // 记录参数索引与参数名称的对应关系
        final SortedMap<Integer, String> map = new ConcurrentSkipListMap<>();
        Annotation[][] annos = method.getParameterAnnotations();
        int paramIndex = 0;
        for (Annotation[] anno : annos) {
            String name = null;
            for (Annotation a : anno) {
                if (a instanceof Param) {
                    name = ((Param) a).value();
                }
            }
            //没有@param注解 (0,1,2)
            if (name == null) {
                name = String.valueOf(map.size());
            }
            map.put(paramIndex, name);/*如果@Param注解存在,那值为@param的值,键为@param注解的下标
                                        如果@param注解不存在,那值为对应参数下标数值的字符串
                                         map中的键值+1递增*/
            paramIndex++;
        }
        int paramCount = map.size();
        Map<String, Object> params = new ConcurrentHashMap<>();
        if (args == null || paramCount == 0) {
        } else {
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                params.put(entry.getValue(), args[entry.getKey()]);
            }
        }
        return params;
    }

    /**
     * 将 Function对象中存储的Map<String, Object>类型的参数，
     * 按照sql语句中参数对应顺序储存在List<Object>中并返回
     *
     * @param fnct
     * @return
     */
    private List<Object> argsList(Function fnct) {
        List<Object> args = new CopyOnWriteArrayList<>();
        Matcher matcher = pattern.matcher(fnct.getSql());
        //sql语句中参数的个数
        int sqlParamCount = 0;
        while (matcher.find()) {
            sqlParamCount++;
        }
        Map<String, Object> paramMap = fnct.getParamMap();
        int i = 0;
        matcher = pattern.matcher(fnct.getSql());
        while (matcher.find()) {
            String key = matcher.group(1);
            if (paramMap.size() != sqlParamCount && paramMap.size() == 1) {// 传入了参数类型为对象类型(一个参数)
                Object objParam = null;
                for (Object objectParam : paramMap.values()) {
                    objParam = objectParam;
                }
                try {
                    Field field = objParam.getClass().getDeclaredField(key);
                    field.setAccessible(true);
                    args.add(field.get(objParam));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    args.add(null);
                    continue;
                }
            } else if (paramMap.get(key) != null) {//传入的普通类型参数类型带了@Param注解
                // （按顺序传值,否则及存在带@Param注解的形参又存在不带@Param注解的形参可能出现异常）
                args.add(paramMap.get(key));
            } else {//传入的普通类型参数类型不带@Param注解
                args.add(paramMap.get(i + ""));
            }
            i++;
        }
        return args;
    }

    /**
     * 判断sql语句是否为查询语句
     *
     * @param sqlType
     * @return
     */
    private boolean isQuery(String sqlType) {
        switch (sqlType.toLowerCase()) {
            case "select":
                return true;
            /*case "create":
            case "alter":
            case "drop":
            case "truncate":
            case "insert":
            case "update":
            case "delete":*/
            default:
                return false;
        }
    }

    /**
     * 处理查询结果集,将查询结果及封装到 Java对象/集合中并返回
     *
     * @param rs
     * @param clazz
     * @return
     */
    private Object handleResult(ResultSet rs, Class<?> clazz) {
        try {
            rs.last();
            int num = rs.getRow();
            rs.beforeFirst();
            List<Object> list = new CopyOnWriteArrayList<>();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int count = rsMeta.getColumnCount();
            if (count > 1) {
                while (rs.next()) {
                    Object obj = clazz.newInstance();
                    wrapperObj(rs, rsMeta, obj);
                    list.add(obj);
                }
            } else {
                while (rs.next()) {
                    String columnName = rsMeta.getColumnName(1);
                    list.add(rs.getObject(columnName));
                }
            }
            if (num == 1)
                return list.get(0);
            else if (num == 0)
                return null;
            else
                return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数据库字段映射为对象属性并存储到对象中
     *
     * @param rs
     * @param rsMeta
     * @param obj
     * @throws SQLException
     */
    private void wrapperObj(ResultSet rs, ResultSetMetaData rsMeta, Object obj) throws SQLException {
        int count = rsMeta.getColumnCount();
        for (int i = 0; i < count; i++) {
            try {
                String columnName = rsMeta.getColumnName(i + 1);
                String javaPropName = lineToHump(columnName);
                Field field = obj.getClass().getDeclaredField(javaPropName);
                field.setAccessible(true);
                field.set(obj, typeof(rs.getObject(columnName)));
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }


    /**
     * 下划线转驼峰
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将数据库对应类型的数据转换为JDK可以接受的数据类型
     *
     * @param obj
     * @return
     */
    private Object typeof(Object obj) {
        if (obj instanceof java.time.LocalDateTime) {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = ((java.time.LocalDateTime) obj).atZone(zoneId);
            obj = Date.from(zdt.toInstant());
        }
        return obj;
    }

}
