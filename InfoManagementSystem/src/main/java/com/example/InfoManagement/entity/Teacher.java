package com.example.InfoManagement.entity;

public class Teacher {
    private String no;
    private String name;
    private String department;
    private String className;
    private int teachingAge;

    public Teacher() {
    }

    public Teacher(String name, String department, String className, int teachingAge) {
        this.name = name;
        this.department = department;
        this.className = className;
        this.teachingAge = teachingAge;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTeachingAge() {
        return teachingAge;
    }

    public void setTeachingAge(int teachingAge) {
        this.teachingAge = teachingAge;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", className='" + className + '\'' +
                ", teachingAge=" + teachingAge +
                '}';
    }
}
