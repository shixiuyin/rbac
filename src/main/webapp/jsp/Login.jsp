<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2018/9/26
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
</head>
<body>

<div class="login-main">
    <header class="layui-elip">后台登录</header>
    <form class="layui-form" action="${pageContext.request.contextPath}/loginServlet" method="post">
        <div class="layui-input-inline">
          <label>${errorMsg}</label>
        </div>
        <div class="layui-input-inline">
            <input type="text" name="loginName" required lay-verify="required" placeholder="用户名" autocomplete="off"
                   class="layui-input" value="admin">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="loginPwd" required lay-verify="required" placeholder="密码" autocomplete="off"
                   class="layui-input" value="admin123456">
        </div>
        <div class="layui-input-inline login-btn">
            <button type="submit" class="layui-btn">登录</button>
        </div>
        <hr/>
        <!--<div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-primary">QQ登录</button>
        </div>
        <div class="layui-input-inline">
            <button type="button" class="layui-btn layui-btn-normal">微信登录</button>
        </div>-->
        <p><a href="Register.jsp" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p>
    </form>
</div>


<script src="../frame/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form'], function () {

        // 操作对象
        var form = layui.form
            , $ = layui.jquery;

        // you code ...


    });
</script>
</body>
</html>
