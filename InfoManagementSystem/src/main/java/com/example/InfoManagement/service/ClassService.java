package com.example.InfoManagement.service;

import com.example.InfoManagement.entity.IClass;

import java.util.List;

public interface ClassService {
    List<String> selectNameList();
    List<IClass> selectAllClass();
    List<IClass> selectClassList(int pageNo, int pageSize);

    String selectNoByName(String className);
    void updateStuNumByClassNo(String classNo,int num);
    boolean insertClass(IClass cls);
    boolean updateInfoByNo(IClass cls);
    boolean deleteByNo(String no);
    int queryStuNumByNo(String no);
    long queryPageNum(int pageSize);
}
