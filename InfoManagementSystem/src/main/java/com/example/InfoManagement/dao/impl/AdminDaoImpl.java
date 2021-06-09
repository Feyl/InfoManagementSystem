package com.example.InfoManagement.dao.impl;


import com.example.InfoManagement.dao.AdminDao;
import com.example.InfoManagement.entity.Admin;
import com.example.InfoManagement.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin loginQuery(String username, String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin admin = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from admin where username = ? and password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs = stmt.executeQuery();
            rs.next();
            String usernameQuery = rs.getString("username");
            String passwordQuery = rs.getString("password");
            admin = new Admin(usernameQuery, passwordQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean updatePassword(String username,String newPass){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean rs = false;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update admin set password = ? where username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,newPass);
            stmt.setString(2,username);
            rs = stmt.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            JDBCUtil.close(stmt, conn);
        }
        return rs;
    }
}
