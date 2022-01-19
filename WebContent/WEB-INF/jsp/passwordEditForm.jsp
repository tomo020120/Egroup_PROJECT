<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード編集画面</title>
</head>
<body>
<%@include file="header.jsp"%>
	<h1>パスワード編集画面</h1>
	<form method="post" action="updatePassword">
		現在のパスワード:<input type="password" name="currentPassword" maxLength="20"><br>
		新規パスワード:<input type="text" name="newPassword" maxLength="20"><br>
		新規パスワード(再入力):<input type="text" name="againNewPassword" maxLength="20"><br>
		<input type="submit" value="変更を保存">
	</form>
</body>
</html>