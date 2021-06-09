<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Class Query Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/table.css">
    <style>
        .ui.table{
            width:56%;
        }
        .ui.input{
            width: 220px;
            position: absolute;
            top:60px;
            right: 355px;
        }
        .ui.message{
            margin-top: 20px;
            width: 600px;
            margin-left: 320px;
        }
        .ui.loading.segment{
            width:56%;
            height: 500px;
            margin-left: 320px;
        }
    </style>
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
    <div class="header item">班级信息管理<i class="icon home"></i></div>
    <a href="/classController?method=clsInfo&pageNo=0" class="item">查看班级列表</a>
    <a href="/jsp/class/addClass.jsp" class="item">添加班级信息</a>
    <a href="/jsp/class/classQuery.jsp" class="item">查询班级信息</a>
    <div class="header item">设置<i class="icon left cog"></i></div>
    <a href="/jsp/admin/chanPass.jsp" class="item">修改密码</a>
    <button class="item exitBt">退出登录<i class="icon power off"></i></button>
</div>
<div class="ui breadcrumb">
    <i class="ui icon align left" style="margin-right: 10px"></i>
    <a class="section">班级信息管理</a>
    <div class="divider"> / </div>
    <div class="active section">查询班级信息</div>
</div>
<form action="/classController" method="get">
    <input type="hidden" name="method" value="queryCls">
    <div class="ui small icon input">
        <input type="text" name="queryInfo" placeholder="搜索">
        <i class="search icon"></i>
    </div>
</form>
<c:if test="${classList!=null&&empty classList}">
    <div class="ui error message">
        <i class="close icon"></i>
        <div class="header">
            没有查找到任何信息!
        </div>
    </div>
</c:if>
<table class="ui selectable celled table">
    <thead>
    <tr>
        <th>序号</th>
        <th>班级号</th>
        <th>班级名称</th>
        <th>班级人数</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.classList}" var="cls" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td>${cls.no}</td>
            <td>${cls.className}</td>
            <td>${cls.stuNum}</td>
        </tr>
    </c:forEach>
    <%--<tr>
        <th>1</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>

    </tr>
    <tr>
        <th>2</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>

    </tr>
    <tr>
        <th>3</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>

    </tr>
    <tr>
        <th>4</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
    </tr>
    <tr>
        <th>5</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
    </tr>
    <tr>
        <th>6</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
    </tr>--%>
    </tbody>
</table>
<c:if test="${classList==null}">
    <div class="ui bottom attached loading tab segment">
    </div>
</c:if>

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
    $('.message .close')
        .on('click', function() {
            $(this).closest('.message').transition('fade');
        });

    $('.exitBt').on('click',function (){
        $('.ui.basic.modal.exit').modal('show');
    })
    $('.ui.ok.button.exit').on('click',function (){
        window.location.href = '/adminController?method=exit'
    })
</script>
</body>
</html>
