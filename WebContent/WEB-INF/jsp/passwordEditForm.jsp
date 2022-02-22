<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード編集画面</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/userNameEditFormStyle.css">
</head>
<body>

	<h1>パスワード編集画面</h1>
	<form id="updatePasswordForm" method="post" action="updatePassword">
		現在のパスワード:<input id="currentPass" type="password" name="currentPassword" maxLength="20" class="field js-password2"><br>
		<div id="currentPassError" class="errorText"></div>

		新規パスワード:<input type="password" id="inputpass" name="newPassword" maxLength="20" class="field js-password" ><br>
		<div id="newPassError" class="errorText"></div>

		新規パスワード(再入力):<input type="password" id="inputagainpass" name="againNewPassword" maxLength="20" class="field js-password1"><br>
		<div id="againPassError" class="errorText"></div>

		<div class="btn">
    				<input class="btn-input js-password-toggle" id="eye" type="checkbox">
    				<label class="btn-label js-password-label" for="eye"><i class="fas fa-eye"></i></label>
    				パスワードを表示する
 		</div>

		<input id="updateButton" type="button" value="変更を保存">

		<input id="correctPassword" type="hidden" value="${sessionInfo.userPassword}">
	</form>

	<a href="accountInfoEdit">戻る</a>

	<script type="text/javascript">
	var mailTextPattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/; // メアドの正規表現
	var passPattern=/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$/ // パスワードの正規表現(大文字、小文字、数字を少なくとも一回含める)
	var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現

	$(function(){
		$("#currentPass").bind("blur", function() {
			var current_pass = $(this).val();
			check_currentPass(current_pass);
		});

		$("#inputpass").bind("blur", function() {
			var input_pass = $(this).val();
			check_pass(input_pass);
		});

		$("#inputagainpass").bind("blur", function() {
			var input_again_pass = $(this).val();
			check_againPass(input_again_pass);
		});

		$("#updateButton").on('click',function(){
			var currentPass = $("#currentPass").val();
			var pass = $("#inputpass").val();
			var againPass = $("#inputagainpass").val();

			var result1 = check_currentPass(currentPass);

			var result2 = check_pass(pass);

			var result3 = check_againPass(againPass);

			if(result1 && result2 && result3){
				$("#updatePasswordForm").submit();
			}
		});
	});

	function check_currentPass(str){ // パスワード関数
		var _result = true;
		var _textbox = $.trim(str);
		var correctPass = $("#correctPassword").val();

		console.log(_textbox);

		if(_textbox.match(emptyPattern)){
			$("#currentPassError").html("現在のパスワードを入力してください");
			_result = false;
		}else if(_textbox != correctPass){
			$("#currentPassError").html("パスワードが正しくありません");
			_result = false;
		}else{
			$("#currentPassError").html("");
		}
		return _result;
	}


	function check_pass(str){ // パスワード関数
		var _result = true;
		var _textbox = $.trim(str);

		console.log(_textbox);

		if(_textbox.match(emptyPattern)){
			$("#newPassError").html("パスワードを入力してください");
			_result = false;
		}else if(_textbox.length < 8){
			$("#newPassError").html("パスワードは8文字以上である必要があります");
			_result = false;
		}else if(!_textbox.match(passPattern)){
			$("#newPassError").html("正しいパスワードの書式ではありません");
			_result = false;
		}else{
			$("#newPassError").html("");
		}
		return _result;
	}

	function check_againPass(str){ // パスワード再入力関数
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
	    const password2 = document.querySelector('.js-password2');
	    if (password.type === 'password') {
	      password.type = 'text';
	      password1.type = 'text';
	      password2.type = 'text';
	      passwordLabel.innerHTML = '<i class="fas fa-eye-slash"></i>';
	    } else {
	      password.type = 'password';
	      password1.type = 'password';
	      password2.type = 'password';
	      passwordLabel.innerHTML = '<i class="fas fa-eye"></i>';
	    }
	    password.focus();
	  });
	</script>
</body>
</html>