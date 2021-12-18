<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<%@include file="/CSS/cartStyle.css" %>
</head>
<body>
	<header><%@include file="header.jsp" %></header>
	<h1>カート</h1>
	<table border="1">
	<tr>
		<th>名前</th>
		<th>個数</th>
		<th>小計</th>
		<th>商品の写真</th>
	</tr>

	 <c:forEach var="cart" items="${result}">
	<tr>
		<td>${cart.name}</td>
		<td>${cart.orderCount}</td>
		<td>${cart.subTotal}円</td>
		<td><img src="${cart.pictPath}"></td>
		<td><a href="cartde?itemId=${cart.itemId}">削除</a></td>
	</tr>
	</c:forEach>

	<tr>
		<td colspan="5">合計金額:<c:choose><c:when test="${result.size() != 0 }">${result.get(0).total}</c:when><c:otherwise>0</c:otherwise></c:choose>円</td>
	</tr>

	</table>

	<div>
		<p><a href="">購入へ進む(仮)</a></p>
	</div>
</body>
</html>