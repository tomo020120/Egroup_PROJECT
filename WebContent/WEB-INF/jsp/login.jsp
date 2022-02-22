<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ログインページ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/loginStyle.css">

</head>
<body>

<div class="box" id="makeImg">
<h1>ログイン</h1>

<!-- 認証に飛ばす -->
<form id="loginForm" name="loginForm" method="post" action="loginCheck">

	<p class="p3">メールアドレス</p><input type="text" id="mailCheck" name="mailAddress">
	<div class="errorText" id="mailError"></div>
	<div class="toggle">
		<p class="p3">パスワード</p><input type="password"class="field js-password" id="passCheck" name="passWord" maxlength="20">
		<div class="errorText" id="passError"></div>

		<div class="btn">
  				<input class="btn-input js-password-toggle" id="eye" type="checkbox">
  				<label class="btn-label js-password-label" for="eye"><i class="fas fa-eye"></i></label>
  				パスワードを表示する
		</div>
	</div>
	<div class="btn_position"><input type="button" id="submitBtn" value="ログイン" class="btn btn--orange"></div>
</form>
<!-- 認証エラーメッセージ取得 -->
<p class="p2">${message}</p>
</div>
<div class="btn_position">
<p>--初めてIbanezをご利用ですか--</p>
<a href="registAccount" class="btn btn--white">新規登録画面へ</a>
</div>





<script type="text/javascript">
	var mailTextPattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/; // メアドの正規表現
	var passPattern=/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$/ // パスワードの正規表現(大文字、小文字、数字を少なくとも一回含める)
	var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現

	$(function(){
		$("#mailCheck").bind("blur", function() {
			var input_mail = $(this).val();
			check_mail(input_mail);
		});

		$("#passCheck").bind("blur", function() {
			var input_pass = $(this).val();
			check_pass(input_pass);
		});

		$("#submitBtn").on('click',function(){
			var mail = $("#mailCheck").val();
			var pass = $("#passCheck").val();

			var result1 = check_mail(mail);

			var result2 = check_pass(pass);

			if(result1 && result2){
				$("#loginForm").submit();
			}
		});
	});

	function check_mail(str){ // メアドチェック関数
		var _result = true;
		var _textbox = $.trim(str);

		console.log(_textbox);

		if(_textbox.match(emptyPattern)){
			$("#mailError").html("メールアドレスを入力してください");
			_result = false;
		}else if(!_textbox.match(mailTextPattern)){
			$("#mailError").html("正しいメールアドレスの書式ではありません");
			_result = false;
		}else{
			$("#mailError").html("");
		}
		return _result;
	}

	function check_pass(str){ // パスワード関数
		var _result = true;
		var _textbox = $.trim(str);

		console.log(_textbox);

		if(_textbox.match(emptyPattern)){
			$("#passError").html("パスワードを入力してください");
			_result = false;
		}else if(_textbox.length < 8){
			$("#passError").html("パスワードは8文字以上である必要があります");
			_result = false;
		}else if(!_textbox.match(passPattern)){
			$("#passError").html("正しいパスワードの書式ではありません");
			_result = false;
		}else{
			$("#passError").html("");
		}
		return _result;
	}

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