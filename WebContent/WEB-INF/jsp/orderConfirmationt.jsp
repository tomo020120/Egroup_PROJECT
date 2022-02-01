<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入内容確認</title>
<link rel="stylesheet" href="CSS/orderConfirmationStyle.css">
<script>
window.onload = function(){
	var dateObj = new Date();
	dateObj.setDate(dateObj.getDate() + 2); // 2日後にセット

	var fy = dateObj.getFullYear();
	var fm = dateObj.getMonth() + 1;
	var fd = dateObj.getDate();

	var fb = "木金土日月火水".charAt(dateObj.getDay());

	dateObj.setDate(dateObj.getDate() + 2); // 更に2日後(4日後)にセット

	var ly = dateObj.getFullYear();
	var lm = dateObj.getMonth() + 1;
	var ld = dateObj.getDate();
	var lb = "火水木金土日月".charAt(dateObj.getDay());

	document.getElementById("deliveryDate").innerHTML =fy+"年"+fm+"月"+fd+"日("+fb+")"+ "～"+ly+"年"+lm+"月"+ld+"日("+lb+")";
	}
</script>
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
				${con.expirationDate}<br>

		</c:if>
	</c:forEach>
</div>
<h1>お届け日</h1>

<p><span id="deliveryDate"></span></p>

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
<h1><a href="purchaseCompleted">購入を確定する</a></h1>

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