package org.apache.ibatis;

import java.util.Map;

/**
 * 封装 Mapper接口内的方法/mapper.xml配置文件 sql语句配置的相关信息
 */
public class Function {
    private String sql;
    private String sqlType;
    private String functionName;
    private String paramType;
    private String returnType;
    private Map<String ,Object> paramMap;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    @Override
    public String toString() {
        return "Function{" +
                "sql='" + sql + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", functionName='" + functionName + '\'' +
                ", paramType='" + paramType + '\'' +
                ", returnType='" + returnType + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
