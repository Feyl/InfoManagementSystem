package com.example.InfoManagement.dao;

import com.example.InfoManagement.entity.Admin;

import java.sql.SQLException;

public interface AdminDao {
    Admin loginQuery(String username, String password);
    boolean updatePassword(String username,String newPass);
}
