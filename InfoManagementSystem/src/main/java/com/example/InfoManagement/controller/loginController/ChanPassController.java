package com.example.InfoManagement.controller.loginController;

import com.example.InfoManagement.service.AdminService;
import com.example.InfoManagement.service.impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/*@WebServlet("/chanPassController")
public class ChanPassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String newPass = req.getParameter("newPass");
        AdminService adminService = new AdminServiceImpl();

        boolean isSuccess = false;
        isSuccess = adminService.updatePassword("1", newPass);

        req.setAttribute("isSuccess",isSuccess);
        req.getRequestDispatcher("/jsp/chanPass.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
