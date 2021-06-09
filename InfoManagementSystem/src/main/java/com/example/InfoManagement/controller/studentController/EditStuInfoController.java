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

/*@WebServlet("/editStuInfoController")
public class EditStuInfoController extends HttpServlet {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ClassServiceImpl classService = new ClassServiceImpl();
        Date admissionDate = null;
        boolean isSuccess = false;
        try {
            admissionDate = sdf.parse(admissionDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String classNo = classService.selectNoByName(className);
        StudentService studentService = new StudentServiceImpl();
        isSuccess = studentService.updateByNo(new Student(no, name, department, major, className, admissionDate), classNo);
        req.setAttribute("isSuccess",isSuccess);
        if (isSuccess) req.setAttribute("msg","编辑成功");
        else req.setAttribute("msg","编辑失败");
        req.getRequestDispatcher("/stuInfoController?pageNo=0").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
