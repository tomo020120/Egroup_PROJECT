<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ログインページ</title>


</head>
<body>
<h1 id="title">ログイン</h1>
<a href="registAccount">新規登録画面へ</a>

<!-- 認証に飛ばす -->
<form name="sampleform" method="post" action="LoginCheckCommand">
<p>まだ作り途中、基本的な動きだけ</p>
メールアドレス<input type="text" name="mailAddress"><br>
パスワード<input type="text" id="passCheck" name="passWord"><br>
パスワード再確認<input type="text" id="rePassCheck" name="rePassWord"><br>
				<input type="submit" value="ログイン">
</form>
<p>パスワードチェックは行ってるけど、まだ画面上には出してない、コンソールだけ</p>

<script type="text/javascript">
	var input_pass=document.getElementById("passCheck");
	var input_rePass=document.getElementById("rePassCheck");
	const regex=/^([a-zA-Z0-9]{8,20})$/;

	//パスワード書式チェック
	input_pass.addEventListener("input",function(){
		var result=regex.test(this.value);
		console.log(this.value);
		console.log(result);
		  });
	//パスワード一致チェック
	input_rePass.addEventListener("input",function(){
		console.log(this.value);
		var passWord=document.getElementById("passCheck").value;
		console.log(passWord);
		var result=passWord==this.value;
		console.log(result);
	});
</script>


</body>
</html>