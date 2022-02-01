<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" href="CSS/productsDetailsStyle.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/productsDetailsScript.js"></script>
</head>
<body data-favorite="${result.get(1)}">
	<%@include file="header.jsp" %>
	<table border="1">
	<tr>
		<th>商品ID</th>
		<th>商品名</th>
		<th>値段</th>
		<th>発売日</th>
		<th>商品写真</th>
		<th>お気に入り</th>
	</tr>
	<tr id="itemInfoList">
		<td>${result.get(0).itemId}</td>
		<td class="targetInfo">${result.get(0).name}</td>
		<td class="targetInfo">${result.get(0).price}円</td>
		<td>${result.get(0).releaseDate}</td>
		<td class="targetInfo"><img id="itemPict" src="${result.get(0).pictPath}"></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="favoriteBtn" onclick="favoriteClick(this);" value="${result.get(0).itemId}" style="border: 4px solid #0F0;">❤︎</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	</table>
	<form method="post" action="addCartProduct?itemId=${result.get(0).itemId}&price=${result.get(0).price} ">
		<select id="orderCount" name="orderCount">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<input type="submit" value="カートに入れる">
	</form>

	<button id="nologinPurchaseBtn">ログインせずに購入する</button>

	<a href="products">商品一覧へ戻る</a>
</body>
</html>