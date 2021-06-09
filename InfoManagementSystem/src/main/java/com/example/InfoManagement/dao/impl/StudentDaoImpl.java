package com.example.InfoManagement.dao.impl;


import com.example.InfoManagement.dao.StudentDao;
import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.utils.JDBCUtil;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> stuList = null;
        stuList = new CopyOnWriteArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select s.*,c.name className from student s join class c on s.classNo=c.no";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String no = rs.getString("no");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String major = rs.getString("major");
                String className = rs.getString("className");
                Date admissionDate = rs.getDate("admissionDate");
                stuList.add(new Student(no,name,department,major,className,admissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,stmt,conn);
        }
        return stuList;
    }

    /**
     * 按页查找学生列表
     * @param pageNo 页数
     * @param pageSize 每页含有的学生人数
     * @return 学生列表
     * @throws SQLException
     */
    @Override
    public List<Student> selectList(int pageNo, int pageSize){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Student> stuList = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select s.*,c.name className from student s join class c on s.classNo=c.no limit "+pageNo*pageSize+","+pageSize;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            stuList = new CopyOnWriteArrayList<>();
            while (rs.next()){
                String no = rs.getString("no");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String major = rs.getString("major");
                String className = rs.getString("className");
                Date admissionDate = rs.getDate("admissionDate");
                stuList.add(new Student(no,name,department,major,className,admissionDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs, stmt,conn);
        }
        return stuList;
    }

    /**
     * 插入学生信息
     * @param stu
     */
    @Override
    public boolean insertStu(Student stu,String classNo){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into student values(?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,stu.getNo());
            stmt.setString(2,stu.getName());
            stmt.setString(3,stu.getDepartment());
            stmt.setString(4,stu.getMajor());
            stmt.setString(5,classNo);
            stmt.setDate(6, new Date(stu.getAdmissionDate().getTime()));
            if (stmt.executeUpdate()!=0) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCUtil.close(stmt, conn);
        }
        return false;
    }

    @Override
    public boolean updateByNo(Student stu,String classNo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rs = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update student set name=?,department=?,major=?,classNo=?,admissionDate=? where no=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,stu.getName());
            stmt.setString(2,stu.getDepartment());
            stmt.setString(3,stu.getMajor());
            stmt.setString(4,classNo);
            stmt.setDate(5, new Date(stu.getAdmissionDate().getTime()));
            stmt.setString(6,stu.getNo());
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs!=0;
    }

    @Override
    public boolean deleteByNo(String no) {
        Connection conn = null;
        Statement stmt = null;
        int rs = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "delete from student where no = "+no;
            rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(stmt, conn);
        }
        return rs!=0;
    }

    @Override
    public int queryPageNum(int pageSize) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int stuNum = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select count(no) stuNum from student";
            rs = stmt.executeQuery(sql);
            rs.next();
            stuNum = rs.getInt("stuNum");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return (int)Math.ceil((double) stuNum/(double) pageSize);
    }

}
