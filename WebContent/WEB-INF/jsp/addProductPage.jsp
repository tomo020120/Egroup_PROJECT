<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>製品追加ページ</title>
<link rel="stylesheet" href="CSS/adminProductsPageStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/addProductPageScript.js"></script>
</head>
<body>


<h1>製品追加ページ</h1>
<div class="allPosition">
<form id="addProductForm" action="addProduct" method="post" enctype="multipart/form-data">
	製品名<br><input type="text" name="productName"><br>
	<div id="nameErrorText" class="errorText"></div>

	価格<br><input type="text" name="price"><br>
	<div id="priceErrorText" class="errorText"></div>

	カテゴリー番号<br><input type="text" name="categoryId"><br>
	<div id="categoryIdErrorText" class="errorText"></div>

	カラー番号<br><input type="text" name="colorId"><br>
	<div id="colorIdErrorText" class="errorText"></div>

	【任意】アーティスト番号<br><input type="text" name="artistId"><br>
	<div class="artistIdErrorText" class="errorText"></div>

	製品写真:<input type="file" name="productPict" id="productPict" accept="image/*"><br>
	<img id="thumbnail" src="" width="400px"><br>
	<button id="submitBtn" type="button">追加</button>
</form>
</div>

<div class="linkpos"><a href="adminProductsPage">商品一覧へ戻る</a></div>
</body>
</html>