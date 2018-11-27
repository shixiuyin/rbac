<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2018/9/30
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>表格</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
</head>
<body class="body">

<a class="layui-btn" href="javascript:location.reload()"></a>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>行边框表格</legend>
</fieldset>

<table class="layui-table" lay-skin="line">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>sessionId</th>
        <th>登录名</th>
        <th>用户名</th>
        <th>ip</th>
        <th>最后登录时间</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${applicationScope.onlineList}" var="userInfo" varStatus="vs">
    <tr>
        <td>${vs.index+1}</td>
        <td>${userInfo.sessionId}</td>
        <td>${userInfo.loginName}</td>
        <td>${userInfo.userName}</td>
        <td>${userInfo.ip}</td>
        <td>${userInfo.loginTime}</td>
    </tr>
    </c:forEach>


    </tbody>
</table>



<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript">
    // you code ...


</script>
</body>
</html>
