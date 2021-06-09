package com.example.InfoManagement.service.impl;



import com.example.InfoManagement.dao.StudentDao;
import com.example.InfoManagement.dao.impl.StudentDaoImpl;
import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.listener.ContextLoaderListener;
import com.example.InfoManagement.mapper.StudentMapper;
import com.example.InfoManagement.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    //    private StudentDao studentDao = new StudentDaoImpl();
    private StudentMapper studentMapper = ContextLoaderListener.getStudentMapper();

    /**
     * 根据学号查询学生信息
     * @param no
     * @return
     */
    @Override
    public Student selectStudentByNo(String no) {
        return studentMapper.selectStudentByNo(no);
    }

    /**
     * 查询所有学生信息
     * @return
     */
    @Override
    public List<Student> selectAll() {
//        return studentDao.selectAll();
        return studentMapper.selectAllStu();
    }

    /**
     * 查询网站中每页学生的信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Student> selectList(int pageNo, int pageSize){
//        return studentDao.selectList(pageNo,pageSize);
        return studentMapper.selectStuList(pageSize*pageNo,pageSize);
    }

    /**
     * 添加学生信息
     * @param stu
     * @param classNo
     * @return
     */
    @Override
    public boolean insertStu(Student stu, String classNo){
//        return studentDao.insertStu(stu,classNo);
        return studentMapper.insertStu(stu.getNo(),stu.getName(),stu.getDepartment(),stu.getMajor(),classNo,stu.getAdmissionDate())==1;
    }

    /**
     * 根据学号修改学生信息
     * @param stu
     * @param classNo
     * @return
     */
    @Override
    public boolean updateByNo(Student stu, String classNo) {
//        return studentDao.updateByNo(stu,classNo);
        return studentMapper.updateStuByNo(stu.getName(),stu.getDepartment(),stu.getMajor(),classNo,stu.getAdmissionDate(),stu.getNo())==1;
    }

    /**
     * 根据学号删除学生信息
     * @param no
     * @return
     */
    @Override
    public boolean deleteByNo(String no) {
//        return studentDao.deleteByNo(no);
        return studentMapper.deleteStuByNo(no)==1;
    }

    /**
     * 查询网站中学生页面的总页数
     * @param pageSize
     * @return
     */
    @Override
    public long queryPageNum(int pageSize) {
//        return studentDao.queryPageNum(pageSize);
        return studentMapper.queryStuNum()/pageSize;
    }

}
