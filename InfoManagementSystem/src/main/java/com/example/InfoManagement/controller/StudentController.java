package com.example.InfoManagement.controller;

import com.example.InfoManagement.entity.Student;
import com.example.InfoManagement.service.ClassService;
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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/**
 * 用于接收与学生相关的访问控制
 */
@WebServlet("/studentController")
public class StudentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");*/
        String method = req.getParameter("method");
        switch (method){
            case "stuInfo": stuInfo(req,resp);break;
            case "addStu": addStu(req,resp);break;
            case "delStu": delStu(req,resp);break;
            case "editStu": editStu(req,resp);break;
            case "queryStu": queryStu(req,resp);break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    /**
     * 查询学生信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void stuInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = 9;
        StudentServiceImpl stuService = new StudentServiceImpl();
        List<Student> stuList = null;
        stuList = stuService.selectList(pageNo,pageSize);
        long pageNum = stuService.queryPageNum(pageSize);
        req.setAttribute("stuList",stuList);
        req.setAttribute("pageNo",pageNo);
        req.setAttribute("pageNum",pageNum);
        req.getRequestDispatcher("/jsp/student/stuInfo.jsp").forward(req,resp);
    }

    /**
     * 添加学生信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");*/
        Student stu = wrapperStu(req, resp);
        String classNo = queryClassNoByClassName(stu.getClassName());
        ClassServiceImpl classService = new ClassServiceImpl();
        StudentService stuService = new StudentServiceImpl();
        boolean isInsertSuccess = stuService.insertStu(stu, classNo);//将学生信息插入student表
        classService.updateStuNumByClassNo(classNo,1);//更新数据库中class表存储的班级人数
        req.setAttribute("isSuccess",isInsertSuccess);
        req.getRequestDispatcher("/jsp/student/addStu.jsp").forward(req, resp);
    }

    /**
     * 删除学生信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void delStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String no = req.getParameter("no");
        String className = req.getParameter("className");
        StudentService studentService = new StudentServiceImpl();
        ClassService classService = new ClassServiceImpl();
        boolean isSuccess = studentService.deleteByNo(no);
        req.setAttribute("isSuccess", isSuccess);
        if(isSuccess) {
            String classNo = classService.selectNoByName(className);
            classService.updateStuNumByClassNo(classNo,-1);//更新数据库中class表存储的班级人数
            req.setAttribute("msg", "删除成功");
        }
        else req.setAttribute("msg","删除失败");
        req.getRequestDispatcher("/studentController?method=stuInfo&pageNo=0").forward(req,resp);//删除后默认跳到第一页

    }

    /**
     * 编辑学生信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void editStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");*/
        String no = req.getParameter("no");
        Student stu = wrapperStu(req, resp);
        String classNo = queryClassNoByClassName(stu.getClassName());//新的班级号
        StudentService studentService = new StudentServiceImpl();
        String oldClassNo = studentService.selectStudentByNo(no).getClassNo();//原来的班级号
        boolean isSuccess = studentService.updateByNo(stu, classNo);//跟新学生信息
        req.setAttribute("isSuccess",isSuccess);
        updateClassStuNum(oldClassNo,classNo);//更新学生信息时，如果学生修改了班级需要修改相关班级的人数
        if (isSuccess) req.setAttribute("msg","编辑成功");
        else req.setAttribute("msg","编辑失败");
        req.getRequestDispatcher("/studentController?method=stuInfo&pageNo=0").forward(req,resp);
    }

    /**
     * 根据条件查询班级信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void queryStu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryInfo = req.getParameter("queryInfo").trim();
        StudentService studentService = new StudentServiceImpl();
        List<Student> students = studentService.selectAll();
        List<Student> stuList = new CopyOnWriteArrayList<>();
        for (Student student : students) {
            if (student.attributeString().toLowerCase().contains(queryInfo.toLowerCase())){
                stuList.add(student);
            }
        }
        req.setAttribute("stuList",stuList);
        req.getRequestDispatcher("/jsp/student/stuQuery.jsp").forward(req, resp);
    }

    /**
     * 将前台传入与学生相关的数据包装成学生对象并返回
     * @param req
     * @param resp
     * @return
     */
    private Student wrapperStu(HttpServletRequest req, HttpServletResponse resp){
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String department = req.getParameter("department");
        String major = req.getParameter("major");
        String className = req.getParameter("className");
        String admissionDateStr = req.getParameter("admissionDate");
        ClassServiceImpl classService = new ClassServiceImpl();
        String classNo = null;
        Date admissionDate = null;
        if(Pattern.matches("^20[12][0-9]-[01][0-9]-[0-3][0-9]$",admissionDateStr)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                admissionDate = sdf.parse(admissionDateStr);//将日期字符串格式转换为Date格式
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            admissionDate = new Date();
        }
        return new Student(no,name,department,major,className,admissionDate);
    }

    /**
     * 通过班级名查询班级号
     * @param name
     * @return
     */
    private String queryClassNoByClassName(String name){
        ClassServiceImpl classService = new ClassServiceImpl();
        return classService.selectNoByName(name);
    }

    /**
     * 修改相应班级的人数
     * @param oldClassNo
     * @param newClassNo
     */
    private void updateClassStuNum(String oldClassNo,String newClassNo){
        ClassService classService = new ClassServiceImpl();
        classService.updateStuNumByClassNo(oldClassNo,-1);//原来的班级人数减一
        classService.updateStuNumByClassNo(newClassNo,1);//对应的新的班级人数加一
    }
}
