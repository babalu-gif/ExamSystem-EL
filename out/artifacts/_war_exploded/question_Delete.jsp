<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/7/27
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String info = (String) request.getAttribute("key1");
    %>
    <font style="color:red;font-size:40px"><%=info%></font>
</body>
</html>
