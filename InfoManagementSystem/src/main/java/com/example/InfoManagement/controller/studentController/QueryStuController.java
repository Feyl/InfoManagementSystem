package com.example.InfoManagement.controller.studentController;

import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.service.StudentService;
import com.example.InfoManagement.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*@WebServlet("/queryStuController")
public class QueryStuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String queryInfo = req.getParameter("queryInfo");
        StudentService studentService = new StudentServiceImpl();
        List<Student> students = studentService.selectAll();
        List<Student> stuList = new CopyOnWriteArrayList<>();
        for (Student student : students) {
            if (student.attributeString().toLowerCase().contains(queryInfo.toLowerCase())){
                stuList.add(student);
            }
        }
        req.setAttribute("stuList",stuList);
        req.getRequestDispatcher("/jsp/stuQuery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
