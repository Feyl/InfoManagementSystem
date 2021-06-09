package com.example.InfoManagement.mapper;

import com.example.InfoManagement.entity.IClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClassMapper {
//    @Select("select class_name from class")
    List<String> selectNameList();

//    @Select("select * from class")
    List<IClass> selectAllClass();

//    @Select("select * from class limit #{pageNo}*#{pageSize},#{pageSize}")
    List<IClass> selectClassList(@Param("startNo") int startNo, @Param("pageSize") int pageSize);

    @Select("select no from class where class_name = #{className}")
    String selectNoByName(String className);

    @Update("update class set  stu_num = stu_num + #{num} where no = #{classNo}")
    Integer updateStuNumByClassNo(@Param("classNo") String classNo,@Param("num")int num);

    @Insert("insert into class values(#{no},#{className},#{stuNum})")
    Integer insertClass(IClass cls);

    @Update("update class set class_name = #{className} where no= #{no}")
    Integer updateInfoByNo(IClass cls);

    @Delete("delete from class where no= #{no}")
    Integer deleteClassByNo(String no);

    @Select("select stu_num from class where no = #{no}")
    Integer queryStuNumByNo(String no);//查看每个班级的人数

    @Select("select count(no) classNum from class")
    Long queryClassNum();//查看数据库中的班级数可以分几页
}