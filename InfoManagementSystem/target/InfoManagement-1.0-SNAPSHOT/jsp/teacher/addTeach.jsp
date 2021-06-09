<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Teacher Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/table.css">
    <link rel="stylesheet" href="../../css/form.css">
</head>
<body>
<div class="ui vertical stackable nav menu" style="">
    <div class="header item">学生信息管理<i class="icon users"></i></div>
    <a href="/studentController?method=stuInfo&pageNo=0" class="item">查看学生信息</a>
    <a href="/jsp/student/addStu.jsp" class="item">添加学生信息</a>
    <a href="/jsp/student/stuQuery.jsp" class="item">查询学生信息</a>
    <div class="header item">教师信息管理<i class="icon user user"></i></div>
    <a href="/jsp/teacher/teachInfo.jsp" class="item">查看教师信息</a>
    <a href="/jsp/teacher/addTeach.jsp" class="item">添加教师信息</a>
    <a href="/jsp/teacher/teachQuery.jsp" class="item">查询教师信息</a>
    <div class="header item">班级信息管理<i class="icon building outline"></i></div>
    <a href="/classController?method=clsInfo&pageNo=0" class="item">查看班级列表</a>
    <a href="/jsp/class/addClass.jsp" class="item">添加班级信息</a>
    <a href="/jsp/class/classQuery.jsp" class="item">查询班级信息</a>
    <div class="header item">设置<i class="icon left cog"></i></div>
    <a href="/jsp/admin/chanPass.jsp" class="item">修改密码</a>
    <button class="item exitBt">退出登录<i class="icon power off"></i></button>
</div>
<div class="ui breadcrumb">
    <i class="ui icon align left" style="margin-right: 10px"></i>
    <a class="section">教师信息管理</a>
    <div class="divider"> / </div>
    <div class="active section">添加教师信息</div>
</div>

<form class="ui form" action="#">

    <div class="inline required field">
        <label>姓名</label>
        <input type="text" name="name" placeholder="姓名">
    </div>
    <div class="inline required field">
        <label>院系</label>
        <input type="text" name="department" placeholder="院系">
    </div>

    <div class="inline required field">
        <label>班级</label>
        <div class="ui selection dropdown">
            <input type="hidden" name="className">
            <i class="dropdown icon"></i>
            <div class="default text">班级</div>
            <div class="menu">
                <div class="item" data-value="软件工程一班">软件工程一班</div>
                <div class="item" data-value="软件工程二班">软件工程二班</div>
                <div class="item" data-value="软件工程三班">软件工程三班</div>
                <div class="item" data-value="软件工程四班">软件工程四班</div>
            </div>
        </div>
    </div>
    <div class="inline required field">
        <label>教龄</label>
        <input type="text" name="teachAge" placeholder="教龄">
    </div>
    <button class="ui blue button" type="submit">添加</button>
    <button class="ui button" type="button">取消</button>
</form>

<div class="ui basic modal exit">
    <div class="ui icon header">
        <i class="sign-out icon"></i>
        确认要退出登录吗？
    </div>
    <div class="actions">
        <div class="ui red basic cancel inverted button">
            <i class="remove icon"></i>
            否
        </div>
        <div class="ui green ok inverted button exit">
            <i class="checkmark icon"></i>
            是
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
<script>
    $('.selection.dropdown').dropdown();
</script>
</body>
</html>
