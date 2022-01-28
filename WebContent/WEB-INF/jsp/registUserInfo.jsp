<%@ page pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>ユーザー登録</title>

<link rel="stylesheet" href="CSS/RegistStyle.css">

</head>
<body>
	<header><%@include file="header.jsp" %></header>
    <h1 id="title">ユーザー登録</h1>
    <form method='post' action="judge">
    ユーザー名<input type='text' id="userName" name='name' maxLength="25"><div class="circle" id="nameCircleID"></div><div class="circle2" id="nameCircle2ID"></div><br>
    メールアドレス<input id='mailAddress' type='text' name='mail' maxlength="256"><div class="circle" id="mailCircleID"></div><div class="circle2" id="mailCircle2ID"></div><br>
    パスワード<input type='text' id='inputpass' name='pass' maxlength="20"><div class="circle" id="circleID"></div><div class="circle2" id="circle2ID"></div><br>
    パスワード(再入力)<input type='text' id='inputagainpass' name='againpass'><div class="circle" id="reEnterCircleID"></div><div class="circle2" id="reEnterCircle2ID"></div><br>
    <input type="submit" id="submitBtn" value="登録">

<!-- ログインできなかったときのエラーメッセージ -->
    <p>${message}</p>

    </form>
<script type="text/javascript">
	var userName = document.getElementById("userName");
	var mail = document.getElementById("mailAddress");
	var pass = document.getElementById("inputpass");
	var passagain = document.getElementById("inputagainpass");

	var nameJudge=false;
	var mailJudge=false;
	var passJudge=false;
	var rePassJudge=false;
//正規表現
	const regis =/^[A-Z]([a-zA-Z0-9]){7,19}$/;
	const reg =/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
//CSSを取る
	const nameCircle=document.getElementById("nameCircleID");
	const nameCircle2=document.getElementById("nameCircle2ID");
	const circle=document.getElementById("circleID");
	const circle2=document.getElementById("circle2ID");
	const mailCircle=document.getElementById("mailCircleID");
	const mailCircle2=document.getElementById("mailCircle2ID");
	const reEnterCircle=document.getElementById("reEnterCircleID");
	const reEnterCircle2=document.getElementById("reEnterCircle2ID");
	const button=document.getElementById("submitBtn");
	mailCircle.style.display="none";
	mailCircle2.style.display="none";
	nameCircle.style.display="none";
	nameCircle2.style.display="none";
	circle.style.display="none";
	circle2.style.display="none";
	reEnterCircle.style.display="none";
	reEnterCircle2.style.display="none";
	button.disabled="true";

//入力チェック
	userName.addEventListener("input",function(){
	if(this.value !=null){
		console.log("ok");
		nameCircle2.style.display="none";
		nameCircle.style.display ="inline-block";
		nameJudge=true;
	}else{
		console.log("no");
		nameCircle.style.display="none";
		nameCircle2.style.display ="inline-block";
		nameJudge=false;
	}
	pushControll(nameJudge,rePassJudge,passJudge,mailJudge);
	});
//正規表現メール
	mail.addEventListener("input",function(){
	if(reg.test(mail.value)){
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
	pushControll(nameJudge,rePassJudge,passJudge,mailJudge);
	});
//正規表現をパスワードに設定
	pass.addEventListener("input",function(){
	if(regis.test(pass.value)){
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
	pushControll(nameJudge,rePassJudge,passJudge,mailJudge);
	});
//passwordのチェック
	passagain.addEventListener("input",function(){
		if(passagain.value === pass.value){
			console.log("ok");
			reEnterCircle.style.display="inline-block";
			reEnterCircle2.style.display="none";
			rePassJudge=true;
		}else{
			console.log("no");
			reEnterCircle2.style.display="inline-block";
			reEnterCircle.style.display="none";
			repassJudge=false;
		}
		pushControll(nameJudge,rePassJudge,passJudge,mailJudge);
	});

	function pushControll(nameJudge,rePassJudge,passJudge,mailJudge){
		if(passJudge && mailJudge && nameJudge && rePassJudge){
			button.disabled = false;
		}else{
			button.disabled = true;
		}
	}
</script>
</body>
</html>