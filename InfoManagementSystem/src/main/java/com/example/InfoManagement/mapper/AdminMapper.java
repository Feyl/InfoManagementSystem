package com.example.InfoManagement.mapper;

import com.example.InfoManagement.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin loginQuery(String username, String password);

    @Update("update admin set password = #{newPass} where username = #{username}")
    Integer updatePassword(@Param("username")String username, @Param("newPass") String newPass);
}
