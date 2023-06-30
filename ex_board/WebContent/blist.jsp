<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#page {
		width: 800px; height:700px;
		margin : 0px auto;
		display : grid;
		grid-template-rows : 10% 10% 1fr ;
		gap:10px;
		grid-template-columns : 1fr;
		grid-template-areas:
			"header"
			"nav"
			"section";
	}
	
	header {
		grid-area: header;
		border : 1px solid black;
		text-align: center;
	}
	
	nav {
		grid-area: nav;
		border : 1px solid black;
		text-align: center;
	}
	
	nav ul {
		list-style: none;
	}
	
	section {
		grid-area: section;
		border : 1px solid black;
	}
</style>
</head>
<body>
<%
%>
	<div id="page">
		<header>
			<h1>게시판</h1>
		</header>
		<nav>
			<ul>
				<li>게시판</li>
			</ul>
		</nav>
		<section>
			<table>
				<tr>
					<th>게시번호</th><th>제목</th><th>작성자</th><th>작성날짜</th>
				</tr>
				
			</table>
		</section>
	</div>
</body>
</html>