<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<%@include file="/CSS/productsDetailsStyle.css" %>
</head>
<body>
	<%@include file="header.jsp" %>
	<table border="1">
	<tr>
		<th>商品ID</th>
		<th>商品名</th>
		<th>値段</th>
		<th>商品の写真</th>
		<th>小計</th>
	</tr>
	<c:forEach var="result" items="${result}">
	<tr>
		<td>${result.itemId}</td>
		<td>${result.name}</td>
		<td>${result.price}円</td>
		<td><img src="${result.pictPath}"></td>
		<td>${result.subTotal}</td>
<td>${result.date}</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>