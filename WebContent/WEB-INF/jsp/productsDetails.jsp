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
<h1><c:out value="${result.get(0).name}"/></h1>
<div class="container">
<div class="item">
<div class="box">
<div class="pic">




		<img id="itemPict" src="${result.get(0).pictPath}" class="pic"><br>
		<p class="p1 targetInfo price"><c:out value="\ ${result.get(0).price}"/></p>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="favoriteBtn" onclick="favoriteClick(this);" value="${result.get(0).itemId}" style="border: 2px solid #000;">❤︎</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</div>
</div>
</div>
<div class="item">
<div class=box2>
<br>
	<form method="post" action="addCartProduct?itemId=${result.get(0).itemId}&price=<c:out value='${result.get(0).price}'/>">
		<c:out value="${result.get(0).name}"/>　　　　　　数量:<select id="orderCount" name="orderCount">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
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

