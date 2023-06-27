<%@page import="dao.TestDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="doneLoop" value="false" />
	<c:set var="dao" value="<%=new TestDAO()%>" />
	<c:set var="tlist" value="${dao.get()}" />
	<table border="1">
		<tr>
			<th>게시판 번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>

			<!-- page 번호에 맞는 게시물의 시작 index 찾기  -->
		<c:choose>
			<c:when test="${not empty sessionScope.page}">
				<c:set var="i" value="${sessionScope.page - 1}" />
			</c:when>
			<c:otherwise>
				<c:set var="i" value="0" />
			</c:otherwise>
		</c:choose>

		<c:set var="pageUnit" value="2" />
		<c:set var="index" value="${pageUnit*i}" />
		
		<!-- 시작 index 부터  pageUnit 만큼 게시물 출력하기 -->
		<c:forEach var="row" items="${tlist}" varStatus="vs">
			<c:if test="${vs.count >= index + 1 && not doneLoop}">
				<tr>
					<td>${row.num}</td>
					<td>${row.title}</td>
					<td>${row.writer}</td>
				</tr>
			</c:if>
			<c:if test="${vs.count - index == pageUnit}">
				<c:set var="doneLoop" value="true" />
			</c:if>
		</c:forEach>
	</table>
	
	<!-- page 번호 출력하기-->
	<c:forEach begin="0" end="${tlist.size()}" step="${pageUnit}" varStatus="vs">
		<div class="page" style="display: inline-block;">
			<c:out value="${vs.count}" />
		</div>
	</c:forEach>

	<script type="text/javascript">
		var page = document.querySelectorAll(".page");

		for (var i = 0; i < page.length; i++) {
			page[i].addEventListener("click", paging);
		}

		function paging(e) {
			var target = e.target;
			var value = target.innerText;

			var x = new XMLHttpRequest();

			x.open("POST", "page", true);
			x.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			x.send("page=" + value);
			location.reload();
		}
	</script>

</body>
</html>