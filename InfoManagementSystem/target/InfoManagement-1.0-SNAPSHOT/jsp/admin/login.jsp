<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <style type="text/css">
        body {
            background-color: #314157;
        }
        body > .grid {
            height: 100%;
        }
        .image {
            margin-top: -100px;
        }
        .column {
            max-width: 450px;
        }
    </style>
</head>
<body>
<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2 class="ui blue image header">
            <div class="content">
                信息管理系统
            </div>
        </h2>
        <form class="ui large form" action="/loginController" method="post"><%--/adminController?method=login--%>
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" required name="username" placeholder="Username">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" required name="password" placeholder="Password">
                    </div>
                </div>
                <button class="ui fluid large blue submit button">Login</button>
            </div>
        </form>
        <c:if test="${msg!=null}">
            <div class="ui error message" style="display: block">
                <div class="header">
                        ${msg}
                </div>
            </div>
        </c:if>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
<script>

    $(document).ready(
        function (){
            $('.ui.message').fadeOut(2000);
        }
    )

</script>
</body>
</html>
