<%--
  Created by IntelliJ IDEA.
  User: Zimomo
  Date: 2019/5/19
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type='text/javascript'>
    function submitForm() {
        var transport=document.getElementById('transport');
        transport.submit();
    }
</script>
<body onload="submitForm();">
    <form id="transport" action="login_action.jsp" method="post">
        <input type="text" value="<%=session.getAttribute("username") %>" name="username" >
        <input type="password" value="<%=session.getAttribute("password") %>" name="password1" >
    </form>
</body>