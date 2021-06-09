package com.example.InfoManagement.controller.classController;

import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.StudentService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;
import com.example.InfoManagement.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*@WebServlet("/queryClassController")
public class QueryClassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String queryInfo = req.getParameter("queryInfo");
        ClassService classService = new ClassServiceImpl();
        List<IClass> classes = classService.selectAllClass();
        List<IClass> classList = new CopyOnWriteArrayList<>();
        for (IClass cls : classes) {
            if (cls.attributeString().toLowerCase().contains(queryInfo.toLowerCase())){
                classList.add(cls);
            }
        }
        req.setAttribute("classList",classList);
        req.getRequestDispatcher("/jsp/classQuery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
