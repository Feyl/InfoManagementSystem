package com.example.InfoManagement.service.impl;


import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.listener.ContextLoaderListener;
import com.example.InfoManagement.mapper.ClassMapper;
import com.example.InfoManagement.service.ClassService;

import java.util.List;

public class ClassServiceImpl implements ClassService {
//    private ClassDao classDao = new ClassDaoImpl();
    private ClassMapper classMapper = ContextLoaderListener.getClassMapper();

    /**
     * 查询所有班级的名称
     * @return
     */
    @Override
    public List<String> selectNameList(){
//        return classDao.nameList();
        return classMapper.selectNameList();
    }

    /**
     * 查询所有班级
     * @return
     */
    @Override
    public List<IClass> selectAllClass() {
//        return classDao.selectAll();
        return classMapper.selectAllClass();
    }

    /**
     * 查询网站中每页班级的信息
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<IClass> selectClassList(int pageNo, int pageSize){
//        return classDao.selectList(pageNo,pageSize);
        return classMapper.selectClassList(pageSize*pageNo,pageSize);
    }

    /**
     * 根据班级名查询班级号
     * @param className
     * @return
     */
    @Override
    public String selectNoByName(String className){
//        return classDao.selectNoByName(className);
        return classMapper.selectNoByName(className);
    }

    /**
     * 根据班级号更新班级人数
     * 当增加或删除学生时都会调用该方法（只是设置的参数num不同 -1/1）
     * @param classNo
     * @param num
     */
    @Override
    public void updateStuNumByClassNo(String classNo,int num){
//        classDao.updateStuNumByClassNo(classNo,num);
        classMapper.updateStuNumByClassNo(classNo,num);
    }

    /**
     * 添加班级信息
     * @param cls
     * @return
     */
    @Override
    public boolean insertClass(IClass cls){
//        return classDao.insertClass(cls);
        return classMapper.insertClass(cls)==1;
    }

    /**
     * 根据班级号修改班级信息
     * @param cls
     * @return
     */
    @Override
    public boolean updateInfoByNo(IClass cls) {
//        return classDao.updateInfoByNo(cls);
        return classMapper.updateInfoByNo(cls)==1;
    }

    /**
     * 根据班级号删除班级信息
     * @param no
     * @return
     */
    @Override
    public boolean deleteByNo(String no) {
//        return classDao.deleteByNo(no);
        return classMapper.deleteClassByNo(no)==1;
    }

    /**
     * 根据班级号查询相应班级人数
     * @param no
     * @return
     */
    @Override
    public int queryStuNumByNo(String no) {
//        return classDao.queryStuNumByNo(no);
        return classMapper.queryStuNumByNo(no);
    }

    /**
     * 查询网站中班级页面的总页数
     * @param pageSize
     * @return
     */
    @Override
    public long queryPageNum(int pageSize) {
//        return classDao.queryPageNum(pageSize);
        return classMapper.queryClassNum()/pageSize;
    }
}
