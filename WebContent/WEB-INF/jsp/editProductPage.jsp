<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/editProductPageScript.js"></script>
</head>
<body>

<h1>製品編集ページ</h1>
<form id="editProductForm" action="editProduct" method="post" enctype="multipart/form-data">
	製品名<input type="text" name="productName" value="${result.name}"><br>
	価格<input type="text" name="price" value="${result.price}"><br>
	カテゴリー番号<input type="text" name="categoryId" value="${result.categoryId}"><br>
	カラー番号<input type="text" name="colorId" value="${result.colorId}"><br>
	形状番号<input type="text" name="shapeId" value="${result.shapeId}"><br>
	【任意】アーティスト番号<input type="text" name="artistId" value="${result.artistId}"><br>
	<input type="hidden" name="exitPictPath" value="${result.pictPath}" >
	<input type="hidden" name="itemId" value="${result.itemId}" >
	製品写真<input type="file" name="productPict" id="productPict" accept="image/*"><br>
	<img id="thumbnail" src="${result.pictPath}"><br>
	<button type="submit">編集完了</button>
</form>
${message}
</body>
</html>