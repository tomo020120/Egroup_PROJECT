<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メールアドレス変更</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/userNameEditFormStyle.css">
</head>
<body>
	<h1>メールアドレス変更</h1>
	<form id="upadateMailForm" method="post" action="judgeCorrectUserMailAddress">
		新しいメールアドレス:<input id="newMailAddress" type="text" name="newUserMailAddress" maxLength="256">
		<div class="errorText" id="mailError"></div>
		<input id="submitBtn" type="button" value="変更を保存">
	</form>
	<a href="accountInfoEdit">戻る</a>
	<p>${message}</p>
<script type="text/javascript">
	var mailTextPattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/; // メアドの正規表現
	var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現

	$(function(){
		$("#newMailAddress").bind("blur", function() {
			var input_mail = $(this).val();
			check_mail(input_mail);
		});

		$("#submitBtn").on('click',function(){
			var result;
			var mail = $("#newMailAddress").val();

			result = check_mail(mail);

			if(result){
				$("#upadateMailForm").submit();
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
</script>
</body>
</html>