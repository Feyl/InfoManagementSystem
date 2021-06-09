package com.example.InfoManagement.dao;

import com.example.InfoManagement.entity.IClass;

import java.util.List;

public interface ClassDao {
    List<String> nameList();
    List<IClass> selectAll();
    List<IClass> selectList(int pageNo, int pageSize);
    String selectNoByName(String className);
    void updateStuNumByClassNo(String classNo,int num);
    boolean insertClass(IClass cls);
    boolean updateInfoByNo(IClass cls);
    boolean deleteByNo(String no);
    int queryStuNumByNo(String no);//查看每个班级的人数
    int queryPageNum(int pageSize);//查看数据库中的班级数可以分几页
}
