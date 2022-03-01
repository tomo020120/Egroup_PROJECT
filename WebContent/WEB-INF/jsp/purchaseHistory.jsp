<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<a href="productsDetails?itemId=${result.itemId}"><img src="${result.pictPath}" width="500px"class="pic2"></a>
		<p class="p1">${result.name}</p>
		<p class="p1">${result.orderCount}個</p>
		<p class="p1 price">\ ${result.subTotal}</p>

		<!-- <p class="p1">${result.price}円<br></p> -->
</div>

		<c:set var="num" value="${result.date}" />
	</c:forEach>

<script>
	$(function(){
		console.log("hello");

		var price = $(".price");

		price.each(function(index,element){
			var priceText = $(element).text();

			console.log(priceText);

			priceText = (priceText.replace('\\','').trim());

			console.log(priceText);

			var convertPrice = getConvertPrice(priceText);
			$(element).text('\\ ' + convertPrice);
		});
	});

	function getConvertPrice(num){
		return Number(num).toLocaleString();
	}
</script>

</body>
</html>