<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード編集画面</title>

<link rel="stylesheet" href="CSS/passwordEditFormStyle.css">
</head>
<body>
<%@include file="header.jsp"%>
	<h1>パスワード編集画面</h1>
	<form id="updatePasswordForm" method="post" action="updatePassword">
		現在のパスワード:<input id="currentPassword" type="password" name="currentPassword" maxLength="20"><br>
		新規パスワード:<input type="text" id="newpassword" name="newPassword" maxLength="20" ><div class="passcheck" id="newpasscheck"></div><div class="passcheck2" id="newpasscheck2"></div><br>
		新規パスワード(再入力):<input type="text" id="againpassword" name="againNewPassword" maxLength="20"><div class="passcheck" id="againpasscheck"></div><div class="passcheck2" id="againpasscheck2"></div><br>
		<input id="updateButton" type="button" value="変更を保存">

		<div id="correctPassword">${sessionInfo.userPassword}</div>

	</form>
	<script type="text/javascript">
		var currentPass = document.getElementById("currentPassword");
		var correctPass = document.getElementById("correctPassword");
		var newpass = document.getElementById("newpassword");
		var againpass = document.getElementById("againpassword");

		const regex=/^[A-Z]([a-zA-Z0-9]){7,19}$/;
		const passcheck=document.getElementById("newpasscheck");
		const passcheck2=document.getElementById("newpasscheck2");
		const againpasscheck=document.getElementById("againpasscheck");
		const againpasscheck2=document.getElementById("againpasscheck2");

		correctPass.style.display = "none";

		var currentPassFlag = false;
		var inputPassFlag = false;


		currentPass.addEventListener("input",function(){
			if(this.value == correctPass.innerHTML){
				console.log("正しい");
				currentPassFlag = true;

			}
			else{
				console.log(result);
				currentPassFlag = false;
			}
			submitJudge(currentPassFlag,inputPassFlag);
		});

		newpass.addEventListener("input",function(){
			if(regex.test(this.value)){
				console.log("ok");
				passcheck2.style.display="none";
				passcheck.style.display ="inline-block";
			}else{
				console.log("no");
				passcheck.style.display="none";
				passcheck2.style.display ="inline-block";
			}
			submitJudge(currentPassFlag,inputPassFlag);
		});

		againpass.addEventListener("input",function(){
		if(newpass.value == againpass.value)
		{
			console.log("ok");
			againpasscheck2.style.display="none";
			againpasscheck.style.display ="inline-block";
			inputPassFlag = true;

		}else{
			console.log("no");
			againpasscheck.style.display="none";
			againpasscheck2.style.display ="inline-block";
			inputPassFlag = false;
		}
		submitJudge(currentPassFlag,inputPassFlag);
	});

		function submitJudge(flag1,flag2){
			var updateButton = document.getElementById("updateButton");
			updateButton.onclick = function(){
				var form = document.getElementById("updatePasswordForm");
			if(flag1 && flag2){
				form.submit();
			}else{

			}
		}
	}



	</script>
</body>
</html>