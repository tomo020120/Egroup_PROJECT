<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<%@include file="/CSS/productsDetailsStyle.css" %>
</head>
<body>
	<header><%@include file="header.jsp" %></header>
	<table border="1">
		<tr>
		<th>商品ID</th>
		<th>商品名</th>
		<th>値段</th>
		<th>発売日</th>
		<th>商品写真</th>
	</tr>
	 <c:forEach var="detail" items="${result}">
	<tr>
		<td>${detail.itemId}</td>
		<td>${detail.name}</td>
		<td>${detail.price}</td>
		<td>${detail.releaseDate}</td>
		<td><img src="${detail.pictPath}"></td>
		<td><a href="cart?itemId=${detail.itemId}">カートへ</a></td>
	</tr>
	</c:forEach>
	</table>
</body>
<!--
<body>
	<table border="1">
	<tr>
		<th>商品ID</th>
		<th>商品名</th>
		<th>値段</th>
		<th>発売日</th>
		<th>カテゴリー名</th>
		<th>カラー名</th>
		<th>形</th>
		<th>アーティスト名</th>
		<th>商品写真</th>
	</tr>
	<tr>
		<td>${product.itemId}</td>
		<td>${product.name}</td>
		<td>${product.price}</td>
		<td>${product.releaseDate}</td>
		<td>${product.categoryName}</td>
		<td>${product.colorName}</td>
		<td>${product.shapeName}</td>
		<td>${product.aritstName}</td>
		<td><img src="${product.pictPath}"></td>
	</tr>

	</table>
</body>
-->
</html>