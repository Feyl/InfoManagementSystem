package com.example.InfoManagement.controller;

import com.example.InfoManagement.entity.IClass;
import com.example.InfoManagement.service.ClassService;
import com.example.InfoManagement.service.impl.ClassServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 用于接收与班级相关的访问控制
 */
@WebServlet("/classController")
public class ClassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");*/
        String method = req.getParameter("method");
        switch(method){
            case "clsInfo": clsInfo(req,resp);break;
            case "addCls": addCls(req,resp); break;
            case "delCls": delCls(req,resp); break;
            case "editCls": editCls(req,resp); break;
            case "queryCls": queryCls(req,resp); break;
            case "queryClsName": queryClsName(req,resp); break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    /**
     * 查询班级信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void clsInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.getRequestDispatcher("/jsp/class/classInfo.jsp").forward(req, resp);
    }

    /**
     * 添加班级信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addCls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classNo = req.getParameter("classNo");
        String className = req.getParameter("className");
        ClassService classService = new ClassServiceImpl();
        boolean isSuccess = false;
        isSuccess = classService.insertClass(new IClass(classNo, className, 0));
        req.setAttribute("isSuccess",isSuccess);
        req.getRequestDispatcher("/jsp/class/addClass.jsp").forward(req,resp);
    }

    /**
     * 删除班级信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void delCls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.getRequestDispatcher("/classController?method=clsInfo&pageNo=0").forward(req,resp);//删除后默认跳到第一页
    }

    /**
     * 修改班级信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editCls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        System.out.println(name);
        ClassService classService = new ClassServiceImpl();
        boolean isSuccess = classService.updateInfoByNo(new IClass(no, name, 0));//这个参数0对更新不起作用
        req.setAttribute("isSuccess",isSuccess);
        if (isSuccess) req.setAttribute("msg","编辑成功");
        else req.setAttribute("msg","编辑失败");
        req.getRequestDispatcher("/classController?method=clsInfo&pageNo=0").forward(req,resp);
    }

    /**
     * 根据条件查询班级信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryCls(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryInfo = req.getParameter("queryInfo").trim();
        ClassService classService = new ClassServiceImpl();
        List<IClass> classes = classService.selectAllClass();
        List<IClass> classList = new CopyOnWriteArrayList<>();
        for (IClass cls : classes) {
            if (cls.attributeString().toLowerCase().contains(queryInfo.toLowerCase())){
                classList.add(cls);
            }
        }
        req.setAttribute("classList",classList);
        req.getRequestDispatcher("/jsp/class/classQuery.jsp").forward(req, resp);
    }

    /**
     * 查询所有班级名称
     * @param req
     * @param resp
     * @throws IOException
     */
    private void queryClsName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ClassService classService = new ClassServiceImpl();
        List<String> nameList = null;
        nameList = classService.selectNameList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),nameList);
    }
}
