package com.example.InfoManagement.service;

import com.example.InfoManagement.entity.Admin;

import java.sql.SQLException;

public interface AdminService {
    Admin loginQuery(String username, String password);
    boolean updatePassword(String username,String newPass);
}
