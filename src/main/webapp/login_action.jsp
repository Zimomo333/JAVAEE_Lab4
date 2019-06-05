<%--
  Created by IntelliJ IDEA.
  User: Zimomo
  Date: 2019/5/19
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" scope="request" class="Bean.UserBean" >
    <jsp:setProperty name="userBean" property="*" />
</jsp:useBean>

<%
    if(userBean.isNull_log()){
        out.print("<script type='text/javascript'>alert('用户名或密码不能为空！请重新输入');window.location='login.html';</script>");
        return;
    }
    if(userBean.check_login()){
        session.setAttribute("username", userBean.getusername());
        session.setAttribute("name", userBean.getname());
        session.setAttribute("email", userBean.getemail());
        session.setAttribute("grouptype", userBean.getgrouptype());
        session.setAttribute("password", userBean.getpassword1());
        session.setAttribute("head", userBean.gethead());
        session.setAttribute("relate",userBean.getrelate());
        session.setAttribute("login","local");
        out.print("<script type='text/javascript'>window.location='index.jsp';</script>");
        return;
    }else{
        out.print("<script type='text/javascript'>alert('用户名或密码错误，登录失败！请重新输入');window.location='login.html';</script>");
    }
%>