<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet" href="CSS/userInfoEditStyle.css">
</head>
<body>
	<%@include file="header.jsp" %>
	<h1>ユーザー情報編集</h1>
	<div id="selectLink">
		<div id="acountInfoEditLinkArea"><a href="accountInfoEdit">アカウント情報編集へ</a></div>
		<div id="deliveryInfoEditLinkArea"><a href="deliveryInfoEdit">配送先情報編集へ</a></div>
		<div id="creditCardInfoEditLinkArea"><a href="creditCardInfoEdit">クレジットカード情報編集へ</a></div>
	</div>
</body>
</html>