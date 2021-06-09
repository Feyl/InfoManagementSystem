package com.example.InfoManagement.controller.studentController;

import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.service.StudentService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;
import com.example.InfoManagement.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
@WebServlet("/addStuController")
public class AddStuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String major = req.getParameter("major");
        String className = req.getParameter("className");
        String admissionDateStr = req.getParameter("admissionDate");
        ClassServiceImpl classService = new ClassServiceImpl();
        String classNo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date admissionDate = null;
        StudentService stuService = new StudentServiceImpl();
        boolean isInsertSuccess = false;
        try {
            classNo = classService.selectNoByName(className);//根据班级名称从数据库中查询班级号
            admissionDate = sdf.parse(admissionDateStr);//将日期字符串格式转换为Date格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isInsertSuccess = stuService.insertStu(new Student(no, name, department, major, classNo, admissionDate), classNo);//将学生信息插入student表
        classService.updateStuNumByClassNo(classNo,1);//更新数据库中class表存储的班级人数
        req.setAttribute("isSuccess",isInsertSuccess);
        req.getRequestDispatcher("/jsp/addStu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
*/
