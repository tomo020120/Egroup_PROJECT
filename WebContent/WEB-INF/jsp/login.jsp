<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ログインページ</title>

<%@include file="/CSS/loginStyle.css"%>

</head>
<body>
<h1 id="title">ログイン</h1>
<a href="registAccount">新規登録画面へ</a>

<!-- 認証に飛ばす -->
<form name="sampleform" method="post" action="LoginCheckCommand">

メールアドレス<input type="text" id="mailCheck" name="mailAddress"><div class="circle" id="mailCircleID"></div><div class="circle2" id="mailCircle2ID"></div><br>
パスワード<input type="text" id="passCheck" name="passWord" maxlength="20"><div class="circle" id="circleID"></div><div class="circle2" id="circle2ID"></div><br>
				<input type="submit" value="ログイン">
</form>


<script type="text/javascript">
	var input_pass=document.getElementById("passCheck");
	var input_mail=document.getElementById("mailCheck");

	const regex=/^[A-Z]([a-zA-Z0-9]){7,19}$/;
	const reg =/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;

	const circle=document.getElementById("circleID");
	const circle2=document.getElementById("circle2ID");
	const mailCircle=document.getElementById("mailCircleID");
	const mailCircle2=document.getElementById("mailCircle2ID");

	circle.style.display="none";
	circle2.style.display="none";

	//パスワード書式チェック
	input_pass.addEventListener("input",function(){
		if(regex.test(this.value)){
			console.log("ok");
			circle2.style.display="none";
			circle.style.display ="inline-block";
		}else{
			console.log("no");
			circle.style.display="none";
			circle2.style.display ="inline-block";
		}

		  });


	mailCircle.style.display="none";
	mailCircle2.style.display="none";

	input_mail.addEventListener("input",function(){
		var result=reg.test(this.value);

		if(result==true){
			console.log("ok");
			mailCircle2.style.display="none";
			mailCircle.style.display ="inline-block";
		}else{
			console.log("no");
			mailCircle.style.display="none";
			mailCircle2.style.display ="inline-block";
		}

		  });

	/*
	//パスワード一致チェック
	input_rePass.addEventListener("input",function(){
		console.log(this.value);
		var passWord=document.getElementById("passCheck").value;
		console.log(passWord);
		var result=passWord==this.value;
		console.log(result);
	}); */
</script>


</body>
</html>