<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Student Information Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <link rel="stylesheet" href="../../css/common.css">
    <link rel="stylesheet" href="../../css/table.css">
    <style>
        #btn-group{
            position: absolute;
            margin-top: 10px;
            right:170px;
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
    <a class="section">学生信息管理</a>
    <div class="divider"> / </div>
    <div class="active section">查看学生信息</div>
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
        <th>学号</th>
        <th>姓名</th>
        <th>院系</th>
        <th>专业</th>
        <th>班级</th>
        <th>入学日期</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.stuList}" var="stu" varStatus="s">
        <tr>
            <td>${pageNo*9+s.count}</td>
            <td>${stu.no}</td>
            <td>${stu.name}</td>
            <td>${stu.department}</td>
            <td>${stu.major}</td>
            <td>${stu.className}</td>
            <td><fmt:formatDate value="${stu.admissionDate}" pattern="yyyy-MM-dd"/></td>
            <td>
                <button class="ui blue basic mini button editBt">编辑</button>
                <button class="ui red basic mini button delBt" >删除</button>
            </td>
        </tr>
    </c:forEach>
<%--    <tr>
        <th>1</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>2</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>3</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>4</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>5</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>6</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>7</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>8</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>
    <tr>
        <th>9</th>
        <th>11923020201</th>
        <th>Jack</th>
        <th>人工智能学院</th>
        <th>SE</th>
        <th>119230202</th>
        <th>2019-06-01</th>
        <th>
            <button class="ui blue basic mini button editBt">编辑</button>
            <button class="ui red basic mini button">删除</button>
        </th>
    </tr>--%>
    </tbody>
</table>

<div id="btn-group">
    <a class="ui left attached facebook button" href="/studentController?method=stuInfo&pageNo=${pageNo-1>0?pageNo-1:0}">上一页</a>
    <a class="ui right attached facebook button" href="/studentController?method=stuInfo&pageNo=${pageNo+1<=pageNum?pageNo+1:pageNo}">下一页</a>
</div>

<div class="ui modal editModal">
    <div class="header">编辑信息</div>
    <div class="content">
        <form class="ui form" action="/studentController">
            <input type="hidden" name="method" value="editStu">
            <div class="inline required field">
                <label>学号</label>
                <input type="text" name="no" placeholder="学号" readonly>
            </div>
            <div class="inline required field">
                <label>姓名</label>
                <input type="text" name="name" placeholder="姓名">
            </div>
            <div class="inline required field">
                <label>院系</label>
                <input type="text" name="department" placeholder="院系">
            </div>
            <div class="inline required field">
                <label>专业</label>
                <input type="text" name="major" placeholder="专业">
            </div>
            <div class="inline required field">
                <label>班级</label>
                <div class="ui selection dropdown">
                    <input type="hidden" name="className">
                    <i class="dropdown icon"></i>
                    <div class="default text">班级</div>
                    <div class="menu" id="classMenu">
                        <%--<div class="item" data-value="软件工程一班">软件工程一班</div>
                        <div class="item" data-value="软件工程二班">软件工程二班</div>
                        <div class="item" data-value="软件工程三班">软件工程三班</div>
                        <div class="item" data-value="软件工程四班">软件工程四班</div>--%>
                    </div>
                </div>
            </div>
            <div class="inline required field">
                <label>入学日期</label>
                <input type="text" name="admissionDate" placeholder="入学日期">
            </div>
            <button class="ui blue button" type="submit">确认</button>
            <button class="ui button cancelBt" type="button">取消</button>
        </form>
    </div>
</div>

<div class="ui basic modal del">
    <div class="ui icon header">
        <i class="trash alternate icon"></i>
        确认要删除该学生的信息吗？
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
        let inputInModal = $('.ui.form').find('div input');
        for (let i = 1; i < tdInForm.length; i++) {
            $(inputInModal[i-1]).val($(tdInForm[i]).text());
            if (i==tdInForm.length-2){
                $(inputInModal[i-1]).siblings(".default.text").text($(tdInForm[i]).text());
                $(inputInModal[i-1]).siblings(".default.text").removeClass("default");
                let item = $(inputInModal[i-1]).siblings(".menu").children('div').first();
                $(item).addClass("active selected");
                $(item).attr("data-value",$(tdInForm[i]).text())
                $(item).text($(tdInForm[i]).text());
            }
        }
        $('.ui.editModal').modal('show');
    })

    $('.cancelBt').on('click', function(){
        $('.ui.editModal').modal('hide');
    })

    $('.selection.dropdown').dropdown();

    $(document).ready(
        function (){
            $('.ui.message').fadeOut(2000);
        }
    )

    $('.selection.dropdown').dropdown();
    $('.ui.selection.dropdown').click(function () {
        $('#classMenu').empty();
        $.get("/classController?method=queryClsName",{},function (date) {
            for(let i = 0;i<date.length;i++){
                let className = $("<div class=\"item\" data-value=\""+date[i]+"\">"+date[i]+"</div>");
                $('#classMenu').append(className);
            }
        },"json");
    });

    let delStuNo,delStuClassName;
    $('.delBt').on('click',function () {
        delStuNo = $(this).parent().siblings('td').eq(1).text();
        delStuClassName = $(this).parent().siblings('td').eq(5).text();
        $('.ui.basic.modal.del').modal('show');
    })

    $('.ui.ok.button.del').on('click',function () {
        window.location.href ='/studentController?method=delStu&no='+delStuNo+'&className='+delStuClassName;
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
