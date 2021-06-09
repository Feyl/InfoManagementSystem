package com.example.InfoManagement.entity;

public class IClass {
    private String no;
    private String className;
    private int stuNum;

    public IClass() {
    }

    public IClass(String no, String className, int stuNum) {
        this.no = no;
        this.className = className;
        this.stuNum = stuNum;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    /**
     * 用于根据班级条件查询班级信息
     * @return
     */
    public String attributeString(){
        return no+className+stuNum;
    }

    @Override
    public String toString() {
        return "IClass{" +
                "no='" + no + '\'' +
                ", name='" + className + '\'' +
                ", stuNum=" + stuNum +
                '}';
    }
}
