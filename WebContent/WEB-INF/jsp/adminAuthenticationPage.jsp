<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者認証ページ</title>
</head>
<body>
<header><%@include file="header.jsp"%></header>
<h1>管理者認証ページ</h1>
<form method="post" action="executeAuthentication">
	認証コード:<input type="text" name="admiinAuthenticaionCode" maxLength="6">
	<input type="submit" value="OK">
</form>
<button onclick="history.back();">戻る</button>
<p>${message}</p>
</body>
</html>