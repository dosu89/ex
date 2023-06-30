<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member RegForm</title>
</head>
<body>
	<h1>Member RegForm</h1>
	<hr>
	<form action="memberRegist.jsp" method="POST">
		<input type="text" name="name" placeholder="Input name.."><br>
		<input type="text" name="id" placeholder="Input id...">
		<input type="button" value="id 중복확인" onclick="idCheck()"><br>
		<div id="result"></div>
		<input type="password" name="pw" placeholder="Input pw.."><br>
		<input type="password" name="repw" placeholder="Input repw..">
		<span id="checkpw"></span><br>
		<input type="text" name="tel" placeholder="Input tel.."><br>
		<input type="text" name="addr" placeholder="Input addr.."><br>
		<input type="submit" id="submit_btn" value="등록" onclick="return inputCheck()">&nbsp&nbsp
		<input type="reset" value="초기화"><br>
	</form>
	
<script>
	var txt_id = document.querySelector("input[name='id']");
	var submit_btn = document.getElementById("submit_btn");
	var txt_pw = document.querySelector("input[name='pw']");
	var txt_repw = document.querySelector("input[name='repw']");
	var span_pw = document.getElementById("checkpw");
	
	txt_id.addEventListener("change", ccheckId);
	txt_repw.addEventListener("keyup", checkPw);
	
	function idCheck() {
		// id 중복체크
		// id 텍스트 박스의 value값을 서버에 보내기.
		// 서버에서 온 응답 데이터(1 또는 0)를 통해 사용자에게 id 사용가능여부를 알려주기
		// 1: 이미 사용중입니다.  0: 사용가능합니다.
		var idv = txt_id.value;
		const ajax = new XMLHttpRequest();
		ajax.onload = function() {
			var check = this.responseText;
			if (check == 1) {
				document.getElementById("result").innerHTML = "이미 사용중입니다.";
			} else {
				document.getElementById("result").innerHTML = "사용가능합니다.";
			}
		}
		ajax.open("POST", "idCheck.jsp", true);
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax.send("id=" + idv);
	}
	
	function ccheckId() {
		const ajax = new XMLHttpRequest();
		ajax.onload = function() {
			document.getElementById("result").innerHTML = "중복검사를 하세요.";
		}
		ajax.open("GET", "memberRegForm.jsp",true);
		ajax.send();
	}
	
	function inputCheck() {
		var txt_tel = document.querySelector("input[name='tel']");
		var txt_addr = document.querySelector("input[name='addr']");
		var checkId = document.querySelector("#result");
		//form 데이터 입력 체크
		if ( txt_id.value.length == 0) {
			alert("ID를 입력하세요.");
			return false;
		}
		if ( txt_pw.value == "") {
			alert("PW를 입력하세요.");
			return false;
		}
		if ( txt_repw.value == "") {
			alert("PW를 입력하세요.");
			return false;
		}
		if ( txt_tel.value == "") {
			alert("전화번호를 입력하세요.");
			return false;
		}

		if ( checkId.innerHTML != "사용가능합니다.") {
			alert("아이디 중복 검사를 하세요");
			return false;
		}

		if ( span_pw.innerHTML != "일치합니다.") {
			alert("비밀번호를 확인하세요.");
			return false;
		}
		return true;
	}

	function checkPw() {
		const ajax2 = new XMLHttpRequest();
		ajax2.onload =  function () {
			if (txt_pw.value == txt_repw.value) {
				span_pw.innerHTML = "일치합니다.";
			} else {
				span_pw.innerHTML = "비밀번호를 확인하세요.";
			}
		}
		ajax2.open("GET", "memberRegForm.jsp", true);
		ajax2.send();
	}

</script>
</body>
</html>