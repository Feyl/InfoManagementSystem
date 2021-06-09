package com.example.InfoManagement.controller.classController;

import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*@WebServlet("/delClassController")
public class DelClassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String no = req.getParameter("no");
        ClassService classService = new ClassServiceImpl();
        int stuNum = classService.queryStuNumByNo(no);
        boolean isSuccess = false;
        if (stuNum==0){
            isSuccess = classService.deleteByNo(no);
        }
        req.setAttribute("isSuccess", isSuccess);
        if(isSuccess) req.setAttribute("msg", "删除成功");
        else req.setAttribute("msg","删除失败（该班级学生人数目前不为0，不能删除）");
        req.getRequestDispatcher("/classInfoController?pageNo=0").forward(req,resp);//删除后默认跳到第一页
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
