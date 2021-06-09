package com.example.InfoManagement.controller.classController;

import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/*@WebServlet("/classInfoController")
public class ClassInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
//        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int pageSize = 9;
        ClassService classService = new ClassServiceImpl();
        List<IClass> classList = null;
        classList = classService.selectClassList(pageNo,pageSize);
        long pageNum = classService.queryPageNum(pageSize);
        req.setAttribute("classList",classList);
        req.setAttribute("pageNo",pageNo);
        req.setAttribute("pageNum",pageNum);
        req.getRequestDispatcher("/jsp/classInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
