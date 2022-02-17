<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>閲覧履歴</title>
</head>

<body>
	
	<form action="productHistoryDelete" method="GET">
	<a href="products">商品一覧へ戻る</a>
	<table border="1">
	<tr>
		<th>商品名</th>
		<th>値段</th>
		<th>商品写真</th>
		<th>履歴から削除する</th>
	</tr>
	<c:forEach var="history" items="${result}">
	<tr>
		<td><a href="productsDetails?itemId=${history.itemId}">${history.name}</a></td>
		<td>${history.price}円</td>
		<td><a href="productsDetails?itemId=${history.itemId}"><img src="${history.pictPath}"></a></td>
		<!-- ページ遷移せずに消去したい -->
		<td><input type="submit" value="消去" ></td>
		
		<!-- <td><a href="productHistoryDelete?itemId=${history.itemId}">削除する</a></td> -->
		
	</tr>
	<input type="hidden" name="itemId" value="${history.itemId}">
	</c:forEach>
	</table>
	</form>

	<a href="products">商品一覧へ戻る</a>

</body>
</html>