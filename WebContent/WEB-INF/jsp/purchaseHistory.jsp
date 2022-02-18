<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<link rel="stylesheet" href="CSS/purchaseHistory.css">


</head>
<body>

	<h1>注文履歴</h1>
	<c:set var="total" value= "0"/>
	<c:forEach var="result" items="${result}">

		<c:set var="num1" value="${result.date}" />


<c:choose>
	<c:when test="${num != num1 }">
<br>
		<h2>${result.date}</h2>

	</c:when>

</c:choose>
<div class="box">
<img src="${result.pictPath}" width="500px"class="pic2">
		<p class="p1">${result.name}</p>
		<p class="p1">${result.orderCount}個</p>
		<p class="p1">\ ${result.subTotal}</p>

		<!-- <p class="p1">${result.price}円<br></p> -->
</div>

		<c:set var="num" value="${result.date}" />
	</c:forEach>


</body>
</html>