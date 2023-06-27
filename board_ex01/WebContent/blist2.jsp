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
	
	h1 {
		text-align: center;
		color :orange;
	}
	
	table, th, td {
		border:1px solid black;
	}
	
	table {
		width: 700px; min-height: 300px;
		position: relative;
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
</style>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<section>
		<h1>게시글 목록</h1>
		<hr>
		<button id="load">목록 불러오기</button>
		<table id = "tbl">
			
		</table>
		<hr>
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name ="title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name ="writer"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols= "70" rows="5" name ="contents"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" ><button id = "btn">등록</button></td>
			</tr>
		</table>
	</section>
<script>
	var load = document.getElementById("load");
	var tbl = document.getElementById("tbl");
	var btn = document.getElementById("btn");
	
	btn.addEventListener("click", regist);
	
	load.addEventListener('click', loadf);
	
	function loadf() {
		const ajax = new XMLHttpRequest();
		ajax.onload = function() {
			let jstr = this.responseText;
			let jobj = JSON.parse(jstr);
			tbl.innerHTML = "<tr><th>게시번호</th><th>제목</th><th>작성자</th></tr>";
			for (var i = 0; i < jobj.length; i++) {
				tbl.innerHTML += "<tr>"
									+"<td>"+jobj[i].b_no + "</td>"
									+"<td>"+jobj[i].b_title + "</td>"
									+"<td>"+jobj[i].b_writer + "</td>"
									+"</tr>";
			}
		}
		ajax.open("GET", "ajax", true);
		ajax.send();
	}
	
	function regist() {
		var title = document.querySelector("input[name='title']").value;
		var writer = document.querySelector("input[name='writer']").value;
		var contents = document.querySelector("textarea[name='contents']").value;
		const ajax2 = new XMLHttpRequest();
		ajax2.onload = function() {
			alert("등록 완료");
			alert(title);
		}
		ajax2.open("POST", "regi", true);
		ajax2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax2.send("b_title="+title + "&b_writer="+writer+"&b_content="+contents);
	}
</script>
</body>
</html>