package com.example.InfoManagement;


import com.example.InfoManagement.dao.StudentDao;
import com.example.InfoManagement.service.impl.ClassServiceImpl;
import com.example.InfoManagement.service.impl.StudentServiceImpl;
import com.example.InfoManagement.utils.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class normalTest {
    @Test
    public void JDBCUtilTest() throws SQLException {
        System.out.println(JDBCUtil.getConnection());
    }

    @Test
    public void listTest() throws SQLException {
//        System.out.println(new StudentServiceImpl().selectList(0, 9));
//        System.out.println(new ClassServiceImpl().selectList(0,9));
        System.out.println(new StudentServiceImpl().queryPageNum(9));
    }
}
