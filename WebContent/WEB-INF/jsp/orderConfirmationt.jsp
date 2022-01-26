<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<%@include file="/CSS/orderConfirmationt.css" %>

</head>
<body>
	<%@include file="header.jsp"%>
	<h1>最終確認</h1>
<h1>お届け先住所</h1>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				${con.deliveryName}<br>
				${con.postalCode}<br>
				${con.address}<br>
				電話番号:${con.tel}
		</c:if>
	</c:forEach>
<div class="cardInfo">
<h1>支払情報</h1>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				${con.cardOwnerName}<br>
				${con.cardNo}<br>
				${con.cardCompany}<br>
				${con.expirationDate}

		</c:if>
	</c:forEach>
</div>

<h1>購入商品</h1>
<table border="1">
	<tr>
		<th>商品名</th>
		<th>購入個数</th>
		<th>値段</th>
		<th>商品写真</th>
	</tr>
	<c:forEach var="con" items="${result}">
		<tr>
			<td>${con.name}</td>
			<td>${con.orderCount}</td>
			<td>${con.subTotal}</td>
			<td><img src="${con.pictPath}"></td>
		</tr>
	</c:forEach>
</table>
<div class="purchase">
<h1><a href="purchaseCompleted">購入完了</a></h1>

	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				商品の小計:\ ${con.total}<br>
				配送料・手数料:\500<br>
				ご請求額:\ ${con.total+500}
		</c:if>
	</c:forEach>
</div>
</body>
</html>