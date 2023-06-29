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
		display : grid;
		grid-template-rows: 1fr 40px;
		grid-template-areas: 
			"table"
			"pages";
	}
	
	#tbl {
		grid-area: table;
		border : 1px solid grey;
		box-shadow: 5px 5px 5px grey;
	}
	
	th, td {
		width:200px; height: 30px;
		border:1px solid black;
	}
	
	#pages {
		grid-area: pages;
		text-align: center;
		line-height: 60px;
	}
	
	.st_no {
		width: 20%;
	}
	
	.ma_name {
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
	List<StockDTO> stList = (List<StockDTO>)(Object)request.getAttribute("slist");
%>
	<div id="tbl">
		<table>
			<tr>
				<th>입출 번호</th><th>제품 이름</th><th>수량</th><th>입출 날짜</th><th>비고</th>
			</tr>
			<c:forEach var="stock" items="<%= stList %>">
				<tr>
					<jsp:useBean id="stock" class="dto.StockDTO" />
					<jsp:setProperty property="*" name="stock"/>
					<td class="st_no">${stock.st_no }</td>				
					<td class="ma_name">${stock.ma_code }</td>				
					<td class="st_ea">${stock.st_ea }</td>
					<td class="st_date">
						<fmt:parseDate value="${stock.st_recDate }" var="date" pattern="yyyy-MM-dd'T'HH:mm"/>
						<fmt:formatDate value="${date }" pattern="yyyy-MM-dd : HH시mm분" />
					</td>				
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
</body>
</html>