<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'regist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<script type="text/javascript">
	function change(){
		var img=document.getElementById("verifyCodeImg");
		img.src="<c:url value='/VerifyCodeServlet?time='/>"+new Date().getTime();
	}
</script>
</head>

<body align="center">
	<h1>注册界面</h1>
	<%--${pageContext.request.contextPath}/RegistServlet --%>
	<p><font color="red">${msg}</font></p>
	<form action="<c:url value="/RegistServlet"/>" method="post">
		账    号:<input type="text" name="username" value="${user.username}"/><font color="red">${errors.username}</font><br/><br/>
		密    码:<input type="password" name="password" value="${user.password }"/><font color="red">${errors.password}</font><br/>
		<div align="center">
		&nbsp验证码:<input type="text" name="verifyCode" value="${user.verifyCode }" size="3"/>&nbsp<img align="center" border="1px" id="verifyCodeImg" src="<c:url value="/VerifyCodeServlet"/>">
		           <a href="javascript:change()">换一张</a><font color="red">${errors.verifyCode}</font><br/><br/>
		</div>
		<input type="submit" value="注册" /><br/><br/>
	</form>
</body>
</html>
