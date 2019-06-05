<%--
  Created by IntelliJ IDEA.
  User: Zimomo
  Date: 2019/5/19
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" scope="request" class="Bean.UserBean" >
    <jsp:setProperty name="userBean" property="*" />
</jsp:useBean>

<%
    if(userBean.isNull_reg()){
        out.print("<script type='text/javascript'>alert('用户名、姓名、用户类型、邮箱、密码不能为空！');window.location='register.jsp';</script>");
        return;
    }
    else if(!userBean.isEqual()){
        out.print("<script type='text/javascript'>alert('两次输入的密码不一致，请重新输入');window.location='register.jsp';</script>");
        return;
    }
    if(!userBean.check_register()){
        userBean.add();
        out.print("<script type='text/javascript'>alert('注册成功，请登录！');window.location='login.html';</script>");
        return;
    }else{
        out.print("<script type='text/javascript'>alert('用户名已存在！请重新输入');window.location='register.jsp';</script>");
    }
%>
