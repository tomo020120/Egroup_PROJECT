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
<div id="div1">div1</div>
<!-- 認証に飛ばす -->
<form name="sampleform" method="post" action="LoginCheckCommand">
<p>まだ作り途中、基本的な動きだけ</p>
メールアドレス<input type="text" name="mailAddress"><div class="circle"></div><br>
パスワード<input type="text" id="passCheck" name="passWord" maxlength="20"><div class="circle" id="circleID"></div><div class="circle2" id="circle2ID"></div><br>
				<input type="submit" value="ログイン">
</form>
<p>パスワードチェックは行ってるけど、まだ画面上には出してない、コンソールだけ</p>

<script type="text/javascript">
	var input_pass=document.getElementById("passCheck");
	const regex=/^[A-Z]([a-zA-Z0-9]){7,19}$/;
	const circle=document.getElementById("circleID");
	const circle2=document.getElementById("circle2ID");

	circle.style.display="none";
	circle2.style.display="none";

	//パスワード書式チェック
	input_pass.addEventListener("input",function(){
		var result=regex.test(this.value);
		if(result==true){
			console.log("ok");
			circle2.style.display="none";
			circle.style.display ="inline-block";
		}else{
			console.log("no");
			circle.style.display="none";
			circle2.style.display ="inline-block";
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