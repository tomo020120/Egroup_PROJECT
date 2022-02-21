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

<div class="position">
<!--<a href="userInfoEdit?userId = "${sessionInfo.userId} class="btn-flat-border">ユーザー情報<p>住所、クレジットカードの登録・変更・破棄</p></a>-->
			<a href="logout"class="btn-flat-border">ログアウト<p>アカウントからログアウトできます</p></a>
			<a href="purchaseHistory"class="btn-flat-border">注文履歴<p>ユーザーが注文済みの商品を閲覧できます</p></a>
			<a href="favorite"class="btn-flat-border">お気に入り<p>お気に入り商品を確認できます</p></a>
			<a href="accountInfoEdit"class="btn-flat-border">アカウント情報編集<p>アカウント情報の変更</p></a>
			<a href="deliveryInfoEdit"class="btn-flat-border">配送先情報編集<p>登録住所の追加・変更</p></a>
			<a href="creditCardInfoEdit"class="btn-flat-border">クレジットカード情報編集<p>登録クレジットカードの追加・変更</p></a>
			<a href="deleteAccount" class="btn-flat-border">アカウント退会<p>アカウントを削除できます</p></a>
</div>
</body>
</html>

