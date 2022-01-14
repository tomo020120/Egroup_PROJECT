<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント退会</title>
</head>
<body>
<header><%@include file="header.jsp" %></header>
<h1>アカウント退会しますか？</h1>
<input type="button" onclick="history.back()" value="戻る">
<input type="button" onclick="location.href='executeDeleteAccount'" value="アカウント退会する">
</body>
</html>