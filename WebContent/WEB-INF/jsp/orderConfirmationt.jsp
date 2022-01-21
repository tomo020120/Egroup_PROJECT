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
	<%@include file="header.jsp"%>
	<h1>最終確認</h1>
	<table border="1">
	<tr>


	</tr>

	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
			<tr>
				<td>${con.deliveryName}</td>
				<td>${con.postalCode}</td>
				<td>${con.address}</td>
				<td>${con.tel}</td>
				<td>${con.cardOwnerName}</td>
				<td>${con.cardNo}</td>
				<td>${con.cardCompany}</td>
				<td>${con.expirationDate}</td>
				<td>${con.total}</td>
			</tr>
		</c:if>
			<tr>
				<td>${con.name}</td>
				<td>${con.orderCount}</td>
				<td>${con.subTotal}</td>
				<td>${con.itemId}</td>
				<td><img src="${con.pictPath}"></td>

			</tr>

	</c:forEach>


	</table>
	<a href="purchaseCompleted">購入完了</a>
</body>
</html>