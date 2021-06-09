package com.example.InfoManagement.controller.studentController;

import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.StudentService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;
import com.example.InfoManagement.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*@WebServlet("/delStuController")
public class DelStuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String no = req.getParameter("no");
        String className = req.getParameter("className");
        StudentService studentService = new StudentServiceImpl();
        ClassService classService = new ClassServiceImpl();
        boolean isSuccess = studentService.deleteByNo(no);
        req.setAttribute("isSuccess", isSuccess);
        if(isSuccess) {
            String classNo = classService.selectNoByName(className);
            classService.updateStuNumByClassNo(classNo,-1);//更新数据库中class表存储的班级人数
            req.setAttribute("msg", "删除成功");
        }
        else req.setAttribute("msg","删除失败");
        req.getRequestDispatcher("/stuInfoController?pageNo=0").forward(req,resp);//删除后默认跳到第一页
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
