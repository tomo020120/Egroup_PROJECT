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
<h1>${result.get(0).name}</h1>
<div class="container">
<div class="item">
<div class="box">
<div class="pic">




		<img id="itemPict" src="${result.get(0).pictPath}" class="pic"><br>
		<p class="p1 targetInfo">\ ${result.get(0).price}</p>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="favoriteBtn" onclick="favoriteClick(this);" value="${result.get(0).itemId}" style="border: 4px solid #0F0;">❤︎</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</div>
</div>
</div>
<div class="item">
<div class=box2>
<br>
	<form method="post" action="addCartProduct?itemId=${result.get(0).itemId}&price=${result.get(0).price} ">
		${result.get(0).name}　　　　　　数量:<select id="orderCount" name="orderCount">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select><br>
		<input type="submit" value="カートに入れる">
	</form>

	<button id="nologinPurchaseBtn">ログインせずに購入する</button><br>

	<a href="products?categoryId=${result.get(0).categoryId}"class="shopbtn">商品一覧へ戻る</a>
	<input id="token" type="hidden" value="${sessionScope.token}">
	<input id="itemName" type="hidden" value="${result.get(0).name}">
	<input id="itemPrice" type="hidden" value="${result.get(0).price}">
</div>
</div>
</div>
</body>
</html>

