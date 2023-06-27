<%@page import="vo.CommentVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardVO"%>
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
	section {
		top : 50px; left: 30%;
		position: absolute;
	}
	
	td {
		border: 1px solid grey;
	}
	
	#wir {
		width: 50px;
	}
		
	#c_con {
		width:400px; height:50px;
		display : inline-block;
		position: relative;
	}
	
	#cont {
		padding: 0px;
		width: 300px; height : 300px;
		vertical-align: top;
	}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"/>
	<section>
		<table>
			<jsp:useBean id="board" class="vo.BoardVO" scope="request"/>
			<jsp:setProperty property="*" name="board"/>
			<tr>
				<th>제목</th><td>${board.b_title }</td>
			</tr>
			<tr>
				<th>작성자</th><td>${board.b_writer }</td>
			</tr>
			<tr>
				<th>내용</th><td id = "cont">${board.b_content }</td>
			</tr>
		</table>
		<hr>
		<div id = com>댓글</div>
			<textarea rows="3" cols="50" name = "con12"></textarea>
			<input type="text" name = "wri12" id = "wri">
			
			<input type="button" value="작성" id="btn1">
<%
	BoardVO b = (BoardVO)request.getAttribute("board");
	CommentDAO cdao = new CommentDAO();
	ArrayList<CommentVO> clist = cdao.selectAll(b.getB_no());
%>
		<hr>
		<table id = "tbl">
			<c:if test="<%= clist != null %>">
				<c:forEach var="comment"  items= "<%= clist %>">
				<jsp:useBean id="comment" class = "vo.CommentVO" />
				<jsp:setProperty property="*" name="comment"/>
				<tr>
					<td><span id="c_con">${comment.c_content }</span></td>
					<td>${comment.c_writer }</td>
					<td id ="btns"><button id="${comment.c_no }" class = "updts">수정</button><button id="del">삭제</button></td>
				</tr>
				</c:forEach>
			</c:if>
		</table>
	</section>
<script>
	var submit = document.getElementById('btn1');
	var reset = document.getElementById('btn123');
	var updateb = document.getElementsByClassName('updts');
	var b_no1 = <%= b.getB_no() %>;
	
	submit.addEventListener("click", insert);
	
	for(var i=0; i<updateb.length; i++) {
		updateb[i].addEventListener("click", updateC);
	}
	
	
	function insert() {
		var content1 = document.querySelector("textarea[name='con12']").value;
		var writer1 = document.querySelector("input[name='wri12']").value;
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
			list();
		}
		xhttp.open("POST", "comreg", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("content="+content1+"&writer="+writer1+"&b_no="+b_no1);
	}
	
	function updateF() {
		var content1 = document.querySelector("textarea[name='con12']").value;
		var writer1 = document.querySelector("input[name='wri12']").value;
		var c_no = pageContext.getAttribute("c_num");
		alert(c_no);
		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
			list();
		}
		xhttp.open("POST", "update1", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("content="+content1+"&writer="+writer1+"&c_no="+c_no);
	}
	
	function updateC(e) {
		if (e.target.id == 'upda') {
			var content1 = document.querySelector("textarea[name='con12']").value;
			var writer1 = document.querySelector("input[name='wri12']").value;
			var c_no = pageContext.getAttribute("c_num");
			alert(c_no);
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				list();
			}
			xhttp.open("POST", "update1", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("content="+content1+"&writer="+writer1+"&c_no="+c_no);
		} else {
			var c_no = e.target.id;
			alert(c_no);
			var tr = e.target.parentElement.parentElement;
			tr.innerHTML = "";
			
			const xhttp = new XMLHttpRequest();
			
			xhttp.onload = function() {
				let j = this.responseText;
				let com = JSON.parse(j);
				tr.innerHTML += "<td><textarea rows='3' cols='50' name = 'con12'>"+com.c_content+"</textarea></td> "
							+ "<td><input type='text' name = 'wri12' id = 'wri' value='"+com.c_writer+"'></td> "
							+ "<td id='btns'><button id ='upda'>수정</button></td>";

				for(var i=0; i<updateb.length; i++) {
					updateb[i].addEventListener("click", updateC);
				}
			}
			xhttp.open("POST", "upCheck", true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("c_no="+c_no);
		}
	}
	
	function list() {
		
		var tbl = document.getElementById("tbl");
		tbl.innerHTML = "";
		const ajax = new XMLHttpRequest();
		ajax.onload = function() {
			let str = this.responseText;
			let obj = JSON.parse(str);
			for(var i =0; i<obj.length; i++){
			tbl.innerHTML += "<tr>"
								+ "<td><span id = 'c_con'>" + obj[i].c_content + "</span></td>"
								+ "<td>" + obj[i].c_writer + "</td>"
								+ "<td id='btns'><button id='"+obj[i].c_no+"'>수정</button><button id='del'>삭제</button></td>"
								+ "</tr>";
			}
		}
		ajax.open("GET", "clist2?b_no="+b_no1, true);
		ajax.send();
	}
</script>
</body>
</html>