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
		<td>${cart.subTotal}</td>
		<td>${cart.total}</td>
		<td><img src="${cart.pictPath}"></td>
		<td><a href="cartde?itemId=${cart.itemId}">削除</a></td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>