<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	<tr>
		<td>${result.itemId}</td>
		<td>${result.name}</td>
		<td>${result.price}</td>
		<td>${result.releaseDate}</td>
		<td><img src="${result.pictPath}"></td>
	</tr>
	</table>
	<form method="get" action="addCartProduct">
		<select name="orderCount">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<input type="submit" value="カートに入れる">
	</form>
</body>
</html>