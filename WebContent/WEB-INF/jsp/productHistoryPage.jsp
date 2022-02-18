<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>閲覧履歴</title>
<link rel="stylesheet" href="CSS/productHistoryPage.css">
</head>

<body>

	<form action="productHistoryDelete" method="GET">

<h1>閲覧履歴</h1>

<div class="creditCardInfo">
	<c:forEach var="history" items="${result}">
		<div id="creditCardList">

		<a href="productsDetails?itemId=${history.itemId}"><img src="${history.pictPath}"width="400px"class="pic2"></a>
		<p class="p1"><a href="productsDetails?itemId=${history.itemId}">${history.name}</a></p>
		<!-- ${history.price}円-->

		<!-- ページ遷移せずに消去したい -->
		<input type="submit" value="消去" >




	<input type="hidden" name="itemId" value="${history.itemId}">
	</div>

	</c:forEach>
</div>
	</form>
<a href="products?categoryId=1">商品一覧へ戻る</a>

</body>
</html>