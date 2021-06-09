package com.example.InfoManagement.dao.impl;

import com.example.InfoManagement.dao.ClassDao;
import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.utils.JDBCUtil;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*public class ClassDaoImpl implements ClassDao {
    *//**
     * 查询班级名称列表
     * @return
     * @throws SQLException
     *//*
    @Override
    public List<String> nameList(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> nameList = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select name from class";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            nameList = new CopyOnWriteArrayList<>();
            while(rs.next()){
                nameList.add(rs.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return nameList;
    }

    @Override
    public List<IClass> selectAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<IClass> classList = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from class";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            classList = new CopyOnWriteArrayList<>();
            while(rs.next()){
                String no = rs.getString("no");
                String name = rs.getString("name");
                int stuNum = rs.getInt("stuNum");
                classList.add(new IClass(no,name,stuNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return classList;
    }

    *//**
     * 查询所有班级信息
     * @return
     * @throws SQLException
     *//*
    @Override
    public List<IClass> selectList(int pageNo, int pageSize){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<IClass> classList = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from class limit "+pageNo*pageSize+","+pageSize;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            classList = new CopyOnWriteArrayList<>();
            while(rs.next()){
                String no = rs.getString("no");
                String name = rs.getString("name");
                int stuNum = rs.getInt("stuNum");
                classList.add(new IClass(no,name,stuNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return classList;
    }

    *//**
     * 通过班级名称查询班级号
     * @param className
     * @return
     * @throws SQLException
     *//*
    @Override
    public String selectNoByName(String className){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select no from class where name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, className);
            rs = stmt.executeQuery();
            rs.next();
            return rs.getString("no");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return null;
    }



    @Override
    public void updateStuNumByClassNo(String classNo,int num){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update class set  stuNum = stuNum + ? where no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,num);
            stmt.setString(2,classNo);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCUtil.close(stmt,conn);
        }
    }

    @Override
    public boolean insertClass(IClass cls){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean rs = false;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into class values(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,cls.getNo());
            stmt.setString(2,cls.getClassName());
            stmt.setInt(3,cls.getStuNum());
            rs = stmt.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCUtil.close(stmt,conn);
        }
        return rs;
    }

    @Override
    public boolean updateInfoByNo(IClass cls) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean rs = false;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update class set name=? where no=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,cls.getClassName());
            stmt.setString(2,cls.getNo());
            rs = stmt.executeUpdate()!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(stmt,conn);
        }
        return rs;
    }

    @Override
    public boolean deleteByNo(String no) {
        Connection conn = null;
        Statement stmt = null;
        boolean rs = false;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from class where no="+no;
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(sql)!=0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(stmt, conn);
        }
        return rs;
    }

    @Override
    public int queryStuNumByNo(String no) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int num = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select stuNum from class where no="+no;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            num = rs.getInt("stuNum");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return num;
    }

    @Override
    public int queryPageNum(int pageSize) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int classNum = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select count(no) classNum from class";
            rs = stmt.executeQuery(sql);
            rs.next();
            classNum = rs.getInt("classNum");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.close(rs,stmt,conn);
        }
        return (int)Math.ceil((double) classNum/(double) pageSize);
    }
}*/
