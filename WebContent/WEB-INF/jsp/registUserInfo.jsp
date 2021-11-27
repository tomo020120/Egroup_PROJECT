<%@ page pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>ユーザー登録</title>
<%@include file="/CSS/RegistStyle.css"%>
</head>
<body>
    <h1 id="title">ユーザー登録</h1>
    <form method='post' action="judge">
    ユーザー名<input type='text' name='name'><br>
    メールアドレス<input id='mailAddress' type='text' name='mail'><div class="circle" id="mailCircleID"></div><div class="circle2" id="mailCircle2ID"></div><br>
    パスワード<input type='text' id='inputpass' name='pass' maxlength="20"><div class="circle" id="circleID"></div><div class="circle2" id="circle2ID"></div><br>
    パスワード(再入力)<input type='text' id='inputagainpass' name='againpass'><div class="circle" id="reEnterCircleID"></div><div class="circle2" id="reEnterCircle2ID"></div><br>
    <input type="submit" value="登録">

<!-- ログインできなかったときのエラーメッセージ -->
    <p>${message}</p>

    </form>
<script type="text/javascript">
	var mail = document.getElementById("mailAddress");
	var pass = document.getElementById("inputpass");
	var passagain = document.getElementById("inputagainpass");
//正規表現
	const regis =/^[A-Z]([a-zA-Z0-9]){7,19}$/;
	const reg =/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/;
//CSSを取る
	const circle=document.getElementById("circleID");
	const circle2=document.getElementById("circle2ID");
	const mailCircle=document.getElementById("mailCircleID");
	const mailCircle2=document.getElementById("mailCircle2ID");
	const reEnterCircle=document.getElementById("reEnterCircleID");
	const reEnterCircle2=document.getElementById("reEnterCircle2ID");
	mailCircle.style.display="none";
	mailCircle2.style.display="none";
	circle.style.display="none";
	circle2.style.display="none";
	reEnterCircle.style.display="none";
	reEnterCircle2.style.display="none";
//正規表現をメールアドレスに設定
	mail.addEventListener("input",function(){
	if(reg.test(mail.value)){
		console.log("ok");
		mailCircle2.style.display="none";
		mailCircle.style.display ="inline-block";
	}else{
		console.log("no");
		mailCircle.style.display="none";
		mailCircle2.style.display ="inline-block";
	}
	});
//正規表現をパスワードに設定
	pass.addEventListener("input",function(){
	if(regis.test(pass.value)){
		console.log("ok");
		circle2.style.display="none";
		circle.style.display ="inline-block";
	}else{
		console.log("no");
		circle.style.display="none";
		circle2.style.display ="inline-block";
	}
	});
//passwordのチーク
	passagain.addEventListener("input",function(){
		if(passagain.value === pass.value){
			console.log("ok");
			reEnterCircle.style.display="inline-block";
			reEnterCircle2.style.display="none";
		}else{
			console.log("no");
			reEnterCircle2.style.display="inline-block";
			reEnterCircle.style.display="none";
		}
	});
</script>
</body>
</html>