<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2018/9/30
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%

    session.setAttribute("name","zhangsan");
    session.setAttribute("name","lisi");

%>


${name}

</body>



</html>
