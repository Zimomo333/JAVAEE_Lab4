<%--
  Created by IntelliJ IDEA.
  User: Zimomo
  Date: 2019/4/20
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>delete_session</title>
</head>
<body>
    <% session.invalidate();

    out.print("<script type='text/javascript'>window.location='login.html';</script>");
    %>
</body>
</html>
