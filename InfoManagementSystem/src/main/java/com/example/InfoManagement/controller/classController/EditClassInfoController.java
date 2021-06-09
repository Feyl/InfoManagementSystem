package com.example.InfoManagement.controller.classController;

import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/*@WebServlet("/editClassInfoController")
public class EditClassInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        ClassService classService = new ClassServiceImpl();
        boolean isSuccess = classService.updateInfoByNo(new IClass(no, name, 0));//这个参数0对更新不起作用
        req.setAttribute("isSuccess",isSuccess);
        if (isSuccess) req.setAttribute("msg","编辑成功");
        else req.setAttribute("msg","编辑失败");
        req.getRequestDispatcher("/classInfoController?pageNo=0").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}*/
