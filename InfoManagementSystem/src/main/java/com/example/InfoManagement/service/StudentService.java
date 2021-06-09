package com.example.InfoManagement.service;

import com.example.InfoManagement.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    Student selectStudentByNo(String no);
    List<Student> selectAll();
    List<Student> selectList(int pageNo, int pageSize);
    boolean insertStu(Student stu,String classNo);
    boolean updateByNo(Student stu,String classNo);
    boolean deleteByNo(String no);
    long queryPageNum(int pageSize);
}
