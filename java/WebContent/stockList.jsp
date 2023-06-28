<%@page import="java.util.List"%>
<%@page import="stock.Service_st"%>
<%@page import="dto.StockDTO"%>
<%@page import="java.util.ArrayList"%>
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
		grid-area: section;
		padding : 20px;
		border : 1px solid black;
		box-shadow: 5px 5px 5px grey;
		display : grid;
		grid-template-rows: 1fr 40px;
		grid-template-areas: 
			"table"
			"pages";
	}
	
	#tbl {
		grid-area: table;
		border : 1px solid red;
	}
	
	th, td {
		width:200px; height: 30px;
		border:1px solid black;
	}
	
	#pages {
		grid-area: pages;
		border: 1px solid blue;
		text-align: center;
		line-height: 40px;
	}
	
	.st_no {
		width: 20%;
	}
	
	.st_name {
		width: 20%;
	}
	
	.st_ea {
		width: 10%;
	}
	
	.st_date {
		width: 20%;
	}
</style>
</head>
<body>
<%
	Service_st s_serv = new Service_st();
	List<StockDTO> slist = s_serv.selectAllStock();
%>
	<section>
		<div id="tbl">
		<table>
			<tr>
				<th>입출 번호</th><th>제품 이름</th><th>수량</th><th>입출 날짜</th><th>비고</th>
			</tr>
			<c:forEach var="stock" items="<%= slist %>">
				<tr>
					<jsp:useBean id="stock" class="dto.StockDTO" />
					<jsp:setProperty property="*" name="stock"/>
					<td>${stock.st_no }</td>				
					<td>${stock.ma_code }</td>				
					<td>${stock.st_amount }</td>
					<td>${stock.st_recDate }</td>				
					<td>${stock.st_note }</td>				
				</tr>
			</c:forEach>

		</table>
		</div>
		<div id="pages">
			<button>&lt&lt</button>
			<button>1</button>
			<button>2</button>
			<button>3</button>
			<button>4</button>
			<button>5</button>
			<button>&gt&gt</button>
		</div>
	</section>
</body>
</html>