<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<link rel="stylesheet" href="CSS/cartStyle.css">
</head>
<body>
	<%@include file="header.jsp"%>
<div class="po">
	<h1>ショッピングカート</h1>
<hr width="60%">

	 <c:forEach var="cart" items="${result}">
		<img src="${cart.pictPath}">
		${cart.name}
		${cart.orderCount}
		${cart.subTotal}円
		<a href="deleteCartProduct?itemId=${cart.itemId}">削除</a><br><hr width="60%">
	</c:forEach>
</div>
合計金額:<c:choose><c:when test="${result.size() != 0 }">${result.get(0).total}</c:when><c:otherwise>0</c:otherwise></c:choose>円


	<div>
		<p><a href="purchase" class="shopbtn">レジへ進む</a></p>
	</div>

</body>
</html>