<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="btn" data-aaa="0" onclick="f()">실행</button>
	
<script>
	function f() {
		const btn = document.querySelector("#btn");
		btn.setAttribute("data-aaa", 1);
		//alert(btn.getAttribute("data-clicked");
		alert(btn-dataset.aaa);
	}
</script>
</body>
</html>