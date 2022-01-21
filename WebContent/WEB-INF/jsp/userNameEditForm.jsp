<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー名変更</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<h1>ユーザー名変更</h1>
	<form method="post" action="updateUserName">
		新しい名前:<input type="text" name="newUserName" maxLength="25">
		<input type="submit" value="変更を保存">
	</form>
</body>
</html>