<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード編集画面</title>
</head>
<body>
	<header><%@include file="header.jsp" %></header>
	<h1>パスワード編集画面</h1>
	<form method="post" action="updatePassword">
		<input type="password" name="currentPassword"><br>
		<input type="text" name="newPassword"><br>
		<input type="text" name="againNewPassword"><br>
		<input type="submit" value="変更を保存">
	</form>
</body>
</html>