<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="db.DBcon2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	System.out.print("id : " +id);
	if(id.length() == 0) {
		out.print(1);
		return;
	}
	DBcon2 conn = new DBcon2();
	Connection con = conn.getConnection();
	PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(id) FROM tbl_member WHERE id = ?");
	pstmt.setString(1, id);
	ResultSet rs = pstmt.executeQuery();
	int result = 0;
	if(rs.next())
		result = rs.getInt(1);
	
		out.print(result);
%>