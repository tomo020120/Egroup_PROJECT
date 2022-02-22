<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー名変更</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/userNameEditFormStyle.css">
</head>
<body>

	<h1>ユーザー名変更</h1>
	<form id="updateUserNameForm" method="post" action="updateUserName" class="formBox">
		新しい名前<br><input id="userName" type="text" name="newUserName" maxLength="50">
		<div class="errorText" id="newUserNameError"></div>
		<input id="submitBtn" type="button" value="変更を保存">
	</form>
	<div class="button"><a href="accountInfoEdit">戻る</a></div>
<script type="text/javascript">
	var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現

	$(function(){
		$("#userName").bind("blur", function() {
			var input_name  = $(this).val();
			check_name(input_name);
		});

		$("#submitBtn").on('click',function(){
			var result;
			var name = $("#userName").val();

			result = check_name(name);

			if(result){
				$("#updateUserNameForm").submit();
			}
		});
	});

	function check_name(str){
		var _result = true;
		var _textbox = $.trim(str);

		if(_textbox.match(emptyPattern)){
			$("#newUserNameError").html("ユーザー名を入力してください");
			_result = false;
		}else if(_textbox.match(/管理者/)){
			$("#newUserNameError").html("そのユーザー名は使用できません");
			_result = false;
		}else if(_textbox.match(/admin/)){
			$("#newUserNameError").html("そのユーザー名は使用できません");
			_result = false;
		}else{
			$("#newUserNameError").html("");
		}
		return _result;
	}
</script>

</body>
</html>