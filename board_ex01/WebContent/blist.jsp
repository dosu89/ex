<%@page import="test.Page"%>
<%@page import="vo.CommentVO"%>
<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dao.CommentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		margin: 0;
		width: 100vw; height: 100vh;
		position: relative;
	}
	
	section {
		top : 50px;
		width:100%;
		position: absolute;
	}
	
	#title11 {
		margin : 0px auto;
	}
	h1 {
		text-align: center;
		color :orange;
	}
	
	
	table, th, td {
		border:1px solid black;
	}
	
	#tblcon {
		width: 100%; 
		height : 500px;
		display : flex;
		justify-content : center;
	}
	
	table {
		margin : 100px ;
		width: 700px; height: 300px;
		position: absolute;
	}
	
	#atag {
		height:30px;
		position: relative;
	}
	
	span {
		display: inline-block;
		position: relative;
	}
	
	.com {
		left: 30px;
		width: 400px;
	}
	
	#pageNation {
		text-align : center;
		position: relative;
	}
</style>
</head>
<body>
<%
	BoardDAO dao = new BoardDAO();
	ArrayList<BoardVO> blist = dao.selectAll();
	CommentDAO cdao = new CommentDAO();
	
	int pageCnt = 0;
	if (blist.size()%3 == 0) {
		pageCnt = blist.size()/3;
	} else {
		pageCnt = blist.size()/3 + 1;
	}
	pageContext.setAttribute("cnt", pageCnt);
%>
	<jsp:include page="nav.jsp" />
	<section>
		<div id="title11">
			<h1>게시글 목록</h1>
			<hr>
		</div>
		<div id="tblcon">
			<table id ="tbl">
				<tr>
					<th>게시번호</th><th>제목</th><th>작성자</th>
				</tr>
				<c:forEach var="board" begin="0" end="2" items = "<%= blist %>">
					<tr>
					<jsp:useBean id="board" class="vo.BoardVO" />
					<jsp:setProperty property="*" name="board"/>
						<td>${board.b_no }</td>
						<td>${board.b_title }</td>
						<td>${board.b_writer }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="pageNation">
			<button id="bef">&lt&lt</button>
			<c:forEach var="pageNum" begin="1" end="<%= pageCnt %>">
				<button id="${pageNum }">${pageNum }</button>
			</c:forEach>
			<button id="af">&gt&gt</button>
		</div>
	</section>
<script>
	var page12 = document.getElementById("pageNation");
	var pageCnt = ${cnt};
	
	page12.addEventListener("click", load);
		
	function load(e) {
		var page1 = e.target.id;
		if (page1 == "bef") {
			page1 = 1;
		} else if (page1 =="af" ) {
			page1 = pageCnt;
		} else if (page1 == "pageNation") {
			return;
		}
		const ajax = new XMLHttpRequest();
		ajax.onload = function() {
			document.getElementById("tbl").innerHTML = 
				"<tr><th>게시번호</th><th>제목</th><th>작성자</th></tr>"
				+ this.response;;
		}
		ajax.open("GET", "PageServlet?page="+page1, true);
		ajax.send();
	}
</script>
</body>
</html>