package com.example.InfoManagement.dao;

import com.example.InfoManagement.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectAll();
    List<Student> selectList(int pageNo, int pageSize);
    boolean insertStu(Student stu,String classNo);
    boolean updateByNo(Student stu,String classNo);
    boolean deleteByNo(String no);
    int queryPageNum(int pageSize);
}
