<%@ page pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>アカウントを作成</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/loginStyle.css">

</head>
<body>

	<div class="box2">
    <h1>アカウントを作成</h1>
    <form id="registForm" method='post' action="judge">
    <p class="p3">ユーザー名(50字以内)</p>
    <input type='text' id="userName" name='name' maxLength="50">
	<div class="errorText" id="nameError"></div>

    <p class="p3">メールアドレス</p>
    <input id='mailAddress' type='text' name='mail' maxlength="256">
	<div class="errorText" id="mailError"></div>

    <p class="p3">パスワード<br>(英大・小と数字を含む8～20字)</p>
    <input type='password' id='inputpass' name='pass' maxlength="20"class="field js-password">
    <div class="errorText" id="passError"></div>



    <p class="p3">もう一度パスワードを入力してください</p>
    <input type='password' id='inputagainpass' name='againpass'class="field js-password1">
    <div class="errorText" id="againPassError"></div>

    <div class="btn">
    				<input class="btn-input js-password-toggle" id="eye" type="checkbox">
    				<label class="btn-label js-password-label" for="eye"><i class="fas fa-eye"></i></label>
    				パスワードを表示する
 	</div>

    <br><input type="button" id="submitBtn" value="Ibanezのアカウントを作成する" class="btn btn--orange2">



<!-- ログインできなかったときのエラーメッセージ -->
    <p class="p4">${message}</p>

    </form>
    </div>

<script type="text/javascript">
	var mailTextPattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/; // メアドの正規表現
	var passPattern=/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$/ // パスワードの正規表現(大文字、小文字、数字を少なくとも一回含める)
	var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現

	$(function(){
		$("#userName").bind("blur", function() {
			var input_name  = $(this).val();
			check_name(input_name);
		});

		$("#mailAddress").bind("blur", function() {
			var input_mail = $(this).val();
			check_mail(input_mail);
		});

		$("#inputpass").bind("blur", function() {
			var input_pass = $(this).val();
			check_pass(input_pass);
		});

		$("#inputagainpass").bind("blur", function() {
			var input_again_pass = $(this).val();
			check_againPass(input_again_pass);
		});

		$("#submitBtn").on('click',function(){
			var result;
			var name = $("#userName").val();
			var mail = $("#mailAddress").val();
			var pass = $("#inputpass").val();
			var againPass = $("#inputagainpass").val();

			result = check_name(name);

			result = check_mail(mail);

			result = check_pass(pass);

			result = check_againPass(againPass);

			if(result){
				$("#registForm").submit();
			}
		});
	});

	function check_name(str){
		var _result = true;
		var _textbox = $.trim(str);

		if(_textbox.match(emptyPattern)){
			$("#nameError").html("ユーザー名を入力してください");
			_result = false;
		}else if(_textbox.match(/管理者/)){
			$("#nameError").html("そのユーザー名は使用できません");
			_result = false;
		}else if(_textbox.match(/admin/)){
			$("#nameError").html("そのユーザー名は使用できません");
			_result = false;
		}else{
			$("#nameError").html("");
		}
		return _result;
	}

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

	function check_againPass(str){ // パスワード関数
		var _result = true;
		var _textbox = $.trim(str);
		var pass = $("#inputpass").val();

		console.log(_textbox);

		if(_textbox.match(emptyPattern)){
			$("#againPassError").html("再度パスワードを入力してください");
			_result = false;
		}else if(_textbox != pass){
			$("#againPassError").html("入力されたパスワードと一致しません");
			_result = false;
		}else{
			$("#againPassError").html("");
		}
		return _result;
	}

	//パスワード表示js
	  const passwordToggle = document.querySelector('.js-password-toggle');
	  passwordToggle.addEventListener('change', function () {
	    const password = document.querySelector('.js-password'),
	    passwordLabel = document.querySelector('.js-password-label');
	    const password1 = document.querySelector('.js-password1');
	    if (password.type === 'password') {
	      password.type = 'text';
	      password1.type = 'text';
	      passwordLabel.innerHTML = '<i class="fas fa-eye-slash"></i>';
	    } else {
	      password.type = 'password';
	      password1.type = 'password';
	      passwordLabel.innerHTML = '<i class="fas fa-eye"></i>';
	    }
	    password.focus();
	  });
</script>
</body>
</html>