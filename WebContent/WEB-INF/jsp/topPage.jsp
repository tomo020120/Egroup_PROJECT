<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<%@include file="/CSS/stylesheet.css" %>
<title>トップページ</title>
</head>
<body>
	<h1>Ibanez</h1>
	<a href="news">ニュース</a>
	<a href="products">商品一覧</a>
	<a href="artist">アーティスト</a>
	<a href="cart">カート</a>
	<a href="login">ログイン</a>


	<img src="<%=request.getContextPath() %>/image/red.jpg">
</body>
</html>