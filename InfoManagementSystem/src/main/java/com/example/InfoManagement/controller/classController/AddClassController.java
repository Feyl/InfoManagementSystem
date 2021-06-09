package com.example.InfoManagement.controller.classController;

import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/*@WebServlet("/addClassController")
public class AddClassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String classNo = req.getParameter("classNo");
        String className = req.getParameter("className");
        ClassService classService = new ClassServiceImpl();
        boolean isSuccess = false;
        isSuccess = classService.insertClass(new IClass(classNo, className, 0));
        req.setAttribute("isSuccess",isSuccess);
        req.getRequestDispatcher("/jsp/addClass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
