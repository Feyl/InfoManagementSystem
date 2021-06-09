<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Class Information Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/table.css">
    <style>
        .ui.table{
            width:60%;
        }
        #btn-group{
            position: absolute;
            margin-top: 10px;
            right:290px;
        }
        .ui.message{
            margin-top: 20px;
            width: 800px;
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
    <div class="active section">查看班级信息</div>
</div>
<c:if test="${isSuccess}">
    <div class="ui success message">
        <i class="close icon"></i>
        <div class="header">
                ${msg}
        </div>
    </div>
</c:if>
<c:if test="${isSuccess!=null&&!isSuccess}">
    <div class="ui error message">
        <i class="close icon"></i>
        <div class="header">
                ${msg}
        </div>
    </div>
</c:if>
<table class="ui striped selectable celled table">
    <thead>
    <tr>
        <th>序号</th>
        <th>班级号</th>
        <th>班级名称</th>
        <th>班级人数</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.classList}" var="cls" varStatus="s">
        <tr>
            <td>${pageNo*9+s.count}</td>
            <td>${cls.no}</td>
            <td>${cls.className}</td>
            <td>${cls.stuNum}</td>
            <td>
                <button class="ui blue basic mini button editBt">编辑</button>
                <button class="ui red basic mini button delBt">删除</button>
            </td>
        </tr>
    </c:forEach>
    <%--<tr>
        <th>1</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>2</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>3</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>4</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>5</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>6</th>
        <th>119230202</th>
        <th>软件工程2班</th>
        <th>30</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>--%>
    </tbody>
</table>
<div id="btn-group">
    <a class="ui left attached facebook button" href="/classController?method=clsInfo&pageNo=${pageNo-1>0?pageNo-1:0}">上一页</a>
    <a class="ui right attached facebook button" href="/classController?method=clsInfo&pageNo=${pageNo+1<=pageNum?pageNo+1:pageNo}">下一页</a>
</div>
<div class="ui modal editModal">
    <div class="header">编辑信息</div>
    <div class="content">
        <form class="ui form" action="/classController">
            <input type="hidden" name="method" value="editCls">
            <div class="inline required field">
                <label>班级号</label>
                <input type="text" readonly name="no" placeholder="班级号">
            </div>
            <div class="inline required field">
                <label>班级名称</label>
                <input type="text" name="name" placeholder="班级名称">
            </div>
            <div class="inline required field">
                <label>班级数量</label>
                <input type="text" readonly name="stuNum" placeholder="学生数量">
            </div>
            <button class="ui blue button" type="submit">确认</button>
            <button class="ui button cancelBt" type="button">取消</button>
        </form>
    </div>
</div>

<div class="ui basic modal del">
    <div class="ui icon header">
        <i class="trash alternate icon"></i>
        确认要删除该班级的信息吗？
    </div>
    <div class="actions">
        <div class="ui red basic cancel inverted button del">
            <i class="remove icon"></i>
            否
        </div>
        <div class="ui green ok inverted button del">
            <i class="checkmark icon"></i>
            是
        </div>
    </div>
</div>

<div class="ui basic modal exit">
    <div class="ui icon header">
        <i class="sign-out icon"></i>
        确认要退出登录吗？
    </div>
    <div class="actions">
        <div class="ui red basic cancel inverted button exit">
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
    $('.editBt').on('click', function(){
        let tdInForm = $(this).parent().siblings();
        let inputInModal = $('.ui.form div').find('input');
        for (let i = 1; i < tdInForm.length; i++) {
            $(inputInModal[i-1]).val($(tdInForm[i]).text());
        }
        $('.ui.editModal').modal('show');
    })

    $('.cancelBt').on('click', function(){
        $('.ui.modal').modal('hide');
    })

    $(document).ready(
        function (){
            $('.ui.message').fadeOut(2000);
        }
    )

    let delClassNo;
    $('.delBt').on('click',function () {
        delClassNo = $(this).parent().siblings('td').eq(1).text();
        $('.ui.basic.modal.del').modal('show');
    })

    $('.ui.ok.button.del').on('click',function () {
        window.location.href ='/classController?method=delCls&no='+delClassNo;
    })

    $('.exitBt').on('click',function (){
        $('.ui.basic.modal.exit').modal('show');
    })
    $('.ui.ok.button.exit').on('click',function (){
        window.location.href = '/adminController?method=exit'
    })
</script>
</body>
</html>

