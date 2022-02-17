<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ログインページ</title>


<link rel="stylesheet" href="CSS/loginStyle.css">

</head>
<body>

<div class="box" id="makeImg">
<h1>ログイン</h1>

<!-- 認証に飛ばす -->
<form name="sampleform" method="post" action="loginCheck">

	<p class="p3">メールアドレス</p><input type="text" id="mailCheck" name="mailAddress">
		<div class="circle" id="mailCircleID">
		</div>
		<div class="circle2" id="mailCircle2ID">
		</div><br>
		<div class="toggle">
			<p class="p3">パスワード</p><input type="password"class="field js-password" id="passCheck" name="passWord" maxlength="20">
			<div class="circle" id="circleID"></div><div class="circle2" id="circle2ID"></div><br>

				<div class="btn">
    				<input class="btn-input js-password-toggle" id="eye" type="checkbox">
    				<label class="btn-label js-password-label" for="eye"><i class="fas fa-eye"></i></label>
    				パスワードを表示する
 				</div>
			</div>
			<div class="btn_position"><input type="submit" id="submitBtn" value="ログイン" class="btn btn--orange"></div>
</form>
<!-- 認証エラーメッセージ取得 -->
<p class="p2">${message}</p>
</div>
<div class="btn_position">
<p>--初めてIbanezをご利用ですか--</p>
<a href="registAccount" class="btn btn--white">新規登録画面へ</a>
</div>





<script type="text/javascript">
	var input_pass=document.getElementById("passCheck");
	var input_mail=document.getElementById("mailCheck");
	var mailJudge=false;
	var passJudge=false;

	const regex=/^[A-Z]([a-zA-Z0-9]){7,19}$/;
	const reg =/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;

	const circle=document.getElementById("circleID");
	const circle2=document.getElementById("circle2ID");
	const mailCircle=document.getElementById("mailCircleID");
	const mailCircle2=document.getElementById("mailCircle2ID");
	const button=document.getElementById("submitBtn");

	circle.style.display="none";
	circle2.style.display="none";
	button.disabled="true";

	//パスワード書式チェック
	input_pass.addEventListener("input",function(){
		if(regex.test(this.value)){
			console.log("ok");
			circle2.style.display="none";
			circle.style.display ="inline-block";
			passJudge=true;
		}else{
			console.log("no");
			circle.style.display="none";
			circle2.style.display ="inline-block";
			passJudge=false;
		}
		pushControll(passJudge,mailJudge);

		  });


	mailCircle.style.display="none";
	mailCircle2.style.display="none";

	input_mail.addEventListener("input",function(){
		var result=reg.test(this.value);

		if(result==true){
			console.log("ok");
			mailCircle2.style.display="none";
			mailCircle.style.display ="inline-block";
			mailJudge=true;
		}else{
			console.log("no");
			mailCircle.style.display="none";
			mailCircle2.style.display ="inline-block";
			mailJudge=false;
		}
		pushControll(passJudge,mailJudge);

		  });

	function pushControll(passJudge,mailJudge){
		if(passJudge && mailJudge){
			button.disabled = false;
		}else{
			button.disabled = true;
		}
	}





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
	<script>
	//パスワード表示js
	  const passwordToggle = document.querySelector('.js-password-toggle');
	  passwordToggle.addEventListener('change', function () {
	    const password = document.querySelector('.js-password'),
	          passwordLabel = document.querySelector('.js-password-label');
	    if (password.type === 'password') {
	      password.type = 'text';
	      passwordLabel.innerHTML = '<i class="fas fa-eye-slash"></i>';
	    } else {
	      password.type = 'password';
	      passwordLabel.innerHTML = '<i class="fas fa-eye"></i>';
	    }
	    password.focus();
	  });
	</script>



</body>
</html>