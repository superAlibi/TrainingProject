<%@ page language="java" contentType="text/html; charset=utf-8;" import="java.util.*" 
    pageEncoding="utf-8" isELIgnored="false" %>

<% 
Cookie[] c=request.getCookies();
if(c!=null){
	for(int i=0;i<c.length;i++){
    if(c[i].getName().equals("remember")){
        //存着数据（用户名+密码）
		String name=c[i].getValue().split("-")[0];
		String pwd=c[i].getValue().split("-")[1];
        
        //再一次的存起来（备用）
        request.setAttribute("name",name);
        request.setAttribute("passwd", pwd);
    }
	}
}
%>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>登陆 | POS管理系统</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <style>
    .header { text-align: center; }
    .header h1 { font-size: 200%; color: #111; margin-top: 30px; }
    .header p { font-size: 14px; }
    body{
    	background-image:url(assets/i/fdbb37b5edead37d322afddb5f08dcb8.jpg);
    	background-repeat: no-repeat;
    	
    }
  </style>
</head>
<body>
<div class="header">
  <div class="am-g">
    <h1>欢迎使用智绘点途POS系统</h1>
    <p>Integrated Development Environment<br/></p>
  </div>
  <hr/>
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h3>登录</h3>
    <hr>
    <form method="post" action="${request.getContextPath()}login" class="am-form">
      <label for="email">账户:</label>
      <input type="text" name="uname" id="uname" value="${name}">
      <br>
      <label for="password">密码:</label>
      <input type="password" name="pwd" id="password" value="${passwd}">
      <br>
      <label for="remember-me">
        <input name ="remember" id="remember-me" type="checkbox" ${empty pwd ? ' selected="selected"' : ''}>记住密码</label>
      <br />
      <div class="am-cf">
        <input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
        <input type="submit" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
      </div>
    </form>
    <hr>
    <p>&copy;2016 ZHDT. All Rights Reserved.</p>
  </div>
</div>
</body>
</html>