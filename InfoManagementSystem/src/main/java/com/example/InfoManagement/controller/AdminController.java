package com.example.InfoManagement.controller;

import com.example.InfoManagement.entity.Admin;
import com.example.InfoManagement.service.AdminService;
import com.example.InfoManagement.service.impl.AdminServiceImpl;
import com.example.InfoManagement.utils.MD5Tool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 用于接收与管理员相关的访问控制
 */
@WebServlet("/adminController")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");*/
        String method = req.getParameter("method");
        switch (method) {
/*            case "login":
                login(req,resp);break;*/
            case "chanPass":
                chanPass(req, resp);
                break;
            case "exit":
                exit(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

   /* private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AdminServiceImpl adminService = new AdminServiceImpl();
        Admin admin = null;

        admin = adminService.loginQuery(username, password);

        if(admin!=null){
            HttpSession session = req.getSession();
            session.setAttribute("loginFlag","loginFlag");
            Cookie jsessionidCookie = new Cookie("JSESSIONID",session.getId());//将Cookie保存到硬盘上30min(登录状态保持)
            jsessionidCookie.setMaxAge(60*1);
            resp.addCookie(jsessionidCookie);
            resp.sendRedirect("/studentController?method=stuInfo&pageNo=0");
        }else{
            req.setAttribute("message", "用户名或密码错误,登录失败!");
            req.getRequestDispatcher("/jsp/admin/login.jsp").forward(req, resp);
        }
    }*/


    /**
     * 修改密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void chanPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPass = null;
        try {
            newPass = MD5Tool.getMD5(req.getParameter("newPass"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdminService adminService = new AdminServiceImpl();
        boolean isSuccess = false;

        isSuccess = adminService.updatePassword("1", newPass);

        req.setAttribute("isSuccess", isSuccess);
        req.getRequestDispatcher("/jsp/admin/chanPass.jsp").forward(req, resp);
    }

    /**
     * 退出登录
     * @param req
     * @param resp
     * @throws IOException
     */
    private void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("loginFlag");
        resp.sendRedirect("/jsp/admin/login.jsp");
    }

}
