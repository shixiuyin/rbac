<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2018/9/26
  Time: 14:55
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
    <title>注册页</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
</head>

<body>
<div class="login-main" id="reg_main">
    <header class="layui-elip">用户注册</header>
    <label>${errorMsg}</label>
    <form class="layui-form" action="${pageContext.request.contextPath}/regServlet" method="post">
        <div class="layui-input-inline">
            <input type="text" name="loginName" required lay-verify="required" placeholder="登录名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password" name="loginPwd" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="password"  required lay-verify="required" placeholder="确认密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" name="userName" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="email" name="email" required lay-verify="required" placeholder="邮箱" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="text" name="address" required lay-verify="required" placeholder="地址" autocomplete="off" class="layui-input">
        </div>

        <div class="layui-input-inline">
            <select name="status" lay-verify="required">
                <option value="0">激活</option>
                <option value="1" selected>未激活</option>
            </select>
        </div>

        <div class="layui-input-inline">
            <textarea name="remark" placeholder="备注" class="layui-textarea"></textarea>
        </div>

        <div class="layui-input-inline login-btn">
            <button type="submit" class="layui-btn">注册</button>
        </div>
        <hr/>
        <p>
            <a href="Login.jsp" class="fl">已有账号？立即登录</a>
            <a href="javascript:;" class="fr">忘记密码？</a>
        </p>
    </form>
</div>

<script src="../frame/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form'], function() {
        var form = layui.form,
            $ = layui.jquery;

        // you code ...

    });
</script>
</body>

</html>