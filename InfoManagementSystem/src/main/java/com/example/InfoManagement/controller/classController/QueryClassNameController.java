package com.example.InfoManagement.controller.classController;

import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*@WebServlet("/queryClassNameController")
public class QueryClassNameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        ClassService classService = new ClassServiceImpl();
        List<String> nameList = null;
        nameList = classService.selectNameList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),nameList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
