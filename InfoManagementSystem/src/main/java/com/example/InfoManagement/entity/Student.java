package com.example.InfoManagement.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String no;
    private String name;
    private String department;
    private String major;
    private String classNo;
    private String className;
    private Date admissionDate;

    public Student() {
    }

    public Student(String no, String name, String department, String major, String className, Date admissionDate) {
        this.no = no;
        this.name = name;
        this.department = department;
        this.major = major;
        this.className = className;
        this.admissionDate = admissionDate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * 用于根据学生条件查询班级信息
     * @return
     */
    public String attributeString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fmtDate= dateFormat.format(admissionDate);
        return no+name+department+major+className+fmtDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                ", admissionDate=" + admissionDate +
                '}';
    }
}
