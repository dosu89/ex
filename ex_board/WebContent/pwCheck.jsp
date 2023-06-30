<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pw = request.getParameter("pw");
	String repw = request.getParameter("repw");
	if (pw.equals(repw)) {
		out.print("일치합니다.");
	} else {
		out.print("비밀번호를 확인하세요.");
	}
%>