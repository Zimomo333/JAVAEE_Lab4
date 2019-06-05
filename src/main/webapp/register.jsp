<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>用户注册</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/style_register.css" rel="stylesheet" type="text/css" media="all"/>
	<link type="text/css" rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<script type="text/javascript" src="jQuery/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div class="login">
	<div class="login-top">
		<h1>用户注册</h1>
		<form action="register_action.jsp" method="post">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
			用户名:　<input type="text" value="Username" name="username" onfocus="this.value = '';" >
			<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
			姓名:　　<input type="text" value="Name" name="name" onfocus="this.value = '';" >
			<span class="glyphicon glyphicon-tags" aria-hidden="true"></span>
			用户类型:<input type="text" value="Student/Teacher" name="grouptype" onfocus="this.value = '';" >
			<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
			邮箱:　　<input type="text" value="example@email.com" name="email" onfocus="this.value = '';" >
			<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
			密码:　　<input type="password" value="Password1" name="password1" onfocus="this.value = '';" >
			<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
			确认密码:<input type="password" value="Password2" name="password2" onfocus="this.value = '';" >
			<div class="login-button">
				<input type="submit" value="注册" >
			</div>
		</form>
	</div>
	<div class="login-bottom">
		<h3>已有账号，<a href="login.html">马上登陆</a> </h3>
	</div>
</div>
</body>
</html>