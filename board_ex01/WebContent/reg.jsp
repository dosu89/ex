<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	section {
		width : 100%; height : calc(100% - 50px);
		top : 50%; left: 50%;
		trandform : translate(50%, 50%);
		position: absolute;
		}
</style>
</head>
<body>
	<jsp:include page = "nav.jsp"/>
	<section>
		<form action = "regi" method= "post" name = "frm">
			제목 <input type = "text" name = "b_title"><br>
			작성자 <input type = "text" name = "b_writer"><br>
			내용 <textarea rows="5" cols="30" name = "b_content"></textarea><br>
			<input type="button" value = "등록" id = "smit1">
			<input type="button" value = "수정" id = "smit2">
			<input type="button" value = "삭제" id = "smit3">
		</form>
	</section>
<script>
	var title = frm.b_title;
	var writer = frm.b_writer;
	var content = frm.b_content;
	var submit = document.getElementById('smit1');
	var update = document.getElementById("smit2");
	
	submit.addEventListener("click", loadDoc)
		
		function loadDoc() {
			const xhttp = new XMLHttpRequest();
			xhttp.onload = function() {
				alert("등록 완료");
			}
			xhttp.open("GET", "regi?b_title="+title.value+"&b_writer="+writer.value+"&b_content="+content.value, true);
			xhttp.send();
		}
	
</script>
</body>
</html>