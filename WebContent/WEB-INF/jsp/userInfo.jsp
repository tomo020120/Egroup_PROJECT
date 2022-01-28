<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>

<link rel="stylesheet" href="CSS/userInfoStyle.css">
</head>
<body>
	<%@include file="header.jsp" %>

			<a href="userInfoEdit?userId = "${sessionInfo.userId} class="btn-flat-border">ユーザー情報<p>住所、クレジットカードの登録・変更・破棄</p></a>
			<a href="logout"class="btn-flat-border">ログアウト<p>アカウントからログアウトできます</p></a>
			<a href="purchaseHistory"class="btn-flat-border">注文履歴<p>配送状況の確認・返品手続き（仮）</p></a>
			<a href="favorite"class="btn-flat-border">お気に入り<p>お気に入り登録した商品を確認できます（仮）</p></a>


</body>
</html>

