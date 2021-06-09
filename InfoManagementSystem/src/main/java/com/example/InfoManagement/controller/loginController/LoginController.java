package com.example.InfoManagement.controller.loginController;

import com.example.InfoManagement.entity.Admin;
import com.example.InfoManagement.service.impl.AdminServiceImpl;
import com.example.InfoManagement.utils.MD5Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");*/
        String username = req.getParameter("username");
        String password = null;
        try {
            password = MD5Tool.getMD5(req.getParameter("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdminServiceImpl adminService = new AdminServiceImpl();
        Admin admin = null;

        admin = adminService.loginQuery(username, password);

        if(admin!=null){
            HttpSession session = req.getSession();
            session.setAttribute("loginFlag","loginFlag");
            Cookie jsessionidCookie = new Cookie("JSESSIONID",session.getId());//将Cookie保存到硬盘上30min(登录状态保持)
            jsessionidCookie.setMaxAge(60*30);
            resp.addCookie(jsessionidCookie);
            resp.sendRedirect("/studentController?method=stuInfo&pageNo=0");
        }else{
            req.setAttribute("msg", "用户名或密码错误，登录失败!");
            req.getRequestDispatcher("/jsp/admin/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
