<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>配送先情報編集</title>
<link rel="stylesheet" href="CSS/deliveryInfoEditStyle.css">
</head>
<body>
	<header><%@include file="header.jsp"%></header>
	<h1>配送先情報編集</h1>
	<a href="addDeliveryInfoForm">新しい住所を追加</a>

	<c:forEach var="deliveryInfo" items="${result}">
		<div class="deliveryInfo">
			<p>氏名:${deliveryInfo.deliveryName}</p>
			<p>電話番号:${deliveryInfo.tel}</p>
			<p>郵便番号:${deliveryInfo.postalCode}</p>
			<p>住所:${deliveryInfo.address}</p>
			<a href="updateDeliveryInfoForm?deliveryInfoId=${deliveryInfo.deliveryInfoId}">変更</a> | <a href="deleteDeliveryInfoForm?deliveryInfoId=${deliveryInfo.deliveryInfoId}">消去</a>
		</div>
	</c:forEach>
</body>
</html>