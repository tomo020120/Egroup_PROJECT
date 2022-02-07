<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>製品追加ページ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/addProductPageScript.js"></script>
</head>
<body>
<header><%@include file="header.jsp"%></header>
<h1>製品追加ページ</h1>
<form action="addProduct" method="post" enctype="multipart/form-data">
	製品名<input type="text" name="productName"><br>
	価格<input type="text" name="price"><br>
	カテゴリー番号<input type="text" name="categoryId"><br>
	カラー番号<input type="text" name="colorId"><br>
	形状番号<input type="text" name="shapeId"><br>
	【任意】アーティスト番号<input type="text" name="artistId"><br>
	製品写真<input type="file" name="productPict" id="productPict" accept="image/*"><br>
	<img id="thumbnail" src=""><br>
	<input type="submit" value="追加">
</form>

</body>
</html>