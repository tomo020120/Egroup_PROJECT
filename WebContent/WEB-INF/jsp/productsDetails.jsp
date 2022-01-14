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
		<td>
		<select name="orderCount">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		</td>
		
		
		<!-- 以下のdetail.itemIdのところはセッション保持されてるUserIdに書き換える -->
		<td><a href="addCartProduct?itemId=${detail.itemId}&orderCount=orderCount">カートに入れる</a></td>
		<td><a href="favoriteProduct?itemId=${detail.itemId}">お気に入りに入れる</a></td>
		
	</tr>
	</c:forEach>
	</table>
</body>
</html>