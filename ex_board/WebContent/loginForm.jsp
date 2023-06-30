<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
	<h1>Login Form</h1>
	<hr>
	<form action="Login.jsp" method="POST">
		<input type="text" name="id" placeholder="Input id.."><br>
		<input type="password" name="pw" placeholder="Input pw.."><br>
		<input type="submit" value="로그인">&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="moveMemberForm()">
	</form>
	
<script>
	function moveMemberForm() {
		location.href="memberRegForm.jsp";
	}
</script>
</body>
</html>