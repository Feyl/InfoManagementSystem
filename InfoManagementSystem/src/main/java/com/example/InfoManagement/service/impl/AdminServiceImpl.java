package com.example.InfoManagement.service.impl;

import com.example.InfoManagement.dao.AdminDao;
import com.example.InfoManagement.dao.impl.AdminDaoImpl;
import com.example.InfoManagement.entity.Admin;
import com.example.InfoManagement.listener.ContextLoaderListener;
import com.example.InfoManagement.mapper.AdminMapper;
import com.example.InfoManagement.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
//    private AdminDao adminDao = new AdminDaoImpl();
    private AdminMapper adminMapper = ContextLoaderListener.getAdminMapper();

    /**
     * 登录信息校验
     * @param username
     * @param password
     * @return
     */
    public Admin loginQuery(String username, String password){
//        return adminDao.loginQuery(username,password);
        return adminMapper.loginQuery(username,password);
    }

    /**
     * 修改密码
     * @param username
     * @param newPass
     * @return
     */
    @Override
    public boolean updatePassword(String username, String newPass){
//        return adminDao.updatePassword(username,newPass);
        return adminMapper.updatePassword(username,newPass)==1;
    }
}
