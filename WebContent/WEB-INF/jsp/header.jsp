<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<%@include file="/CSS/headerStyle.css" %>
</head>
<body>
	<h1>Ibanez</h1>
	<div id="nav">
		<a href="news">ニュース</a>
		<a href="products">商品一覧</a>
		<a href="artist">アーティスト</a>
		<a href="userInfoEdit?userId = "${sessionInfo.userId}>ユーザー情報編集</a>
		<a href="cart"><img src="images/cart.png" alt="カート" width="40" ></a>
		<a href="login"><img src="images/people.png" alt="ログイン" width="40" ></a>
		<a href="logout">ログアウト</a>
	</div>
	<p>${sessionInfo.userName}</p>
</body>
</html>