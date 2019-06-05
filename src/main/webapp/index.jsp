<%@ page import="Bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Zimomo
  Date: 2019/4/16
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="jQuery/jquery.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <title>用户中心</title>

</head>

<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouptypeed for better mobile display -->
        <div style="width:300px;margin:0 auto;">
        <div class="navbar-header" >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="font-size:25px;"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>　用户中心</a>
        </div>
    </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <% if(session.getAttribute("username")!=null){ %>
                <li><a href="https://cas.dgut.edu.cn/logout?callback=http://localhost:8080/login/delete_session.jsp" style="font-size: 20px;"> <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>　登出</a></li>
                <% }else{ %>
                <li><a href="https://cas.dgut.edu.cn?appid=javaee&state=STATE" style="font-size: 20px;"> <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>　莞工登陆</a></li>
                <% } %>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

    <div class="row" style="padding-left: 130px;padding-right: 130px;">
            <div class="page-header">
                <h1><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>　用户信息</h1>
            </div>
    </div>
    <div class="row" style="padding-left: 145px;padding-right: 145px;width: 245px;">
        <div style="float: left;">
            <img class="img-thumbnail" src="images/head_photos/<%=session.getAttribute("head")%>" alt="头像" style="width:245px;height: 316px;"/>
            <div style="text-align: center;width: 240px;font-size: 23px;padding-top: 5px;">
                <span class="glyphicon glyphicon-camera" aria-hidden="true">　用户头像</span>
            </div>

            <div style="text-align: center;width: 240px;font-size: 13px;padding-top: 5px;padding-left:35px;">
                <form action="Upload_Servlet" method="post" enctype="multipart/form-data">
                    <input type="file" name="file">
                    <input type="submit" value="上传头像">
                </form>
            </div>
        </div>
        <div style="font-size: 23px;width: 1300px;padding-left: 265px;">
            <div class="alert alert-success" role="alert">用户名（username）：<%=session.getAttribute("username") %></div>
            <div class="alert alert-info" role="alert">姓名（name）：<%=session.getAttribute("name")%></div>
            <div class="alert alert-warning" role="alert">用户组（grouptype）：<%=session.getAttribute("grouptype")%></div>
            <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>　邮箱（email）：<%=session.getAttribute("email")%></div>
            <% if(session.getAttribute("login")=="local"){ %>
            <div class="alert alert-success" role="alert">
                <form action="Relate_Servlet" method="post">
                    关联莞工账号（relate）：<%=session.getAttribute("relate") %>　　　　　　　　　　　　　　　
                    <input type="text" value="学号" name="relate">
                    <input type="submit" value="绑定">
                </form>
            </div>
            <% }else{ %>
            <div class="alert alert-success" role="alert">关联本地账号（relate）：<%=session.getAttribute("relate") %></div>
            <% } %>
        </div>
    </div>

    <nav class="navbar navbar-inverse navbar-fixed-bottom">
        <div class="container-fluid">
            <div style="width: 350px;margin:0 auto;">
                <a class="navbar-brand" style="font-size:20px;">@17级软卓1一班 梁梓豪</a>
            </div>
        </div>
    </nav>
</body>
</html>


<script>
    var errori ='<%=request.getParameter("error")%>'
    if(errori=='yes'){
        alert("该学号未注册，请先用该学号莞工登陆！");
    }
</script>
