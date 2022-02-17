<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<link rel="stylesheet" href="CSS/productsDetailsStyle.css">

</head>
<body>

	<h1>注文履歴</h1>
	<c:set var="total" value= "0"/>
	<c:forEach var="result" items="${result}">

		<c:set var="num1" value="${result.date}" />


<c:choose>
	<c:when test="${num != num1 }">
		<hr>
		<h2>${result.date}</h2>

	</c:when>

</c:choose>
		${result.name}<br>
		<img src="${result.pictPath}" width="500px">
		<br>
		${result.price}円<br>
		${result.orderCount}個<br>
		${result.subTotal}<br>
		<c:set var="num" value="${result.date}" />
	</c:forEach>
</body>
</html>