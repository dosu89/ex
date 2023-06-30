<%@page import="db.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>목록 페이지(paging)</h1>
	<hr>
	총 게시글 수 : 88개<br>
	페이지번호의 최대 값 : 9개(1~9)<br><br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>bno</th><th>title</th><th>writer</th><th>regDate</th>
		</thead>
		<tbody>
			<c:forEach var="board" items="${list }" varStatus="status">
			<tr>
				<td>${board.bno }</td>
				<td>${board.title }</td>
				<td>${board.writer }</td>
				<td>${board.regdate }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<c:set var="endNum" value="${startNum + 4 }"/>
		<c:if test="${startNum != 1 }">
			<a href="paging?p=${startNum - 5 }"><button onclick=f(event)>&lt</button></a>&nbsp;
		</c:if>
		
		<c:if test="${endNum }>${lastPage }">
			<c:set var="endNum" value="${lastPage}"/>
		</c:if>
		
		<c:forEach var="i" begin="${startNum }" end="${endNum }">
			<a href="paging?p=${i }"><button>${i }</button></a>&nbsp;
		</c:forEach>
		
		<c:if test="${endNum%5 == 0 }">
			<a href="paging?p=${endNum + 1 }"><button onclick=f(event)>&gt</button></a>
		</c:if>
	<br>
<script>
	
</script>	
</body>
</html>