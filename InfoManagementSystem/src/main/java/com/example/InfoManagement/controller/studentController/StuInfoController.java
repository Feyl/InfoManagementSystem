package com.example.InfoManagement.controller.studentController;



import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*@WebServlet("/stuInfoController")
public class StuInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = 9;
        StudentServiceImpl stuService = new StudentServiceImpl();
        List<Student> stuList = null;
        stuList = stuService.selectList(pageNo,pageSize);
        long pageNum = stuService.queryPageNum(pageSize);
        req.setAttribute("stuList",stuList);
        req.setAttribute("pageNo",pageNo);
        req.setAttribute("pageNum",pageNum);
        req.getRequestDispatcher("/jsp/stuInfo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
