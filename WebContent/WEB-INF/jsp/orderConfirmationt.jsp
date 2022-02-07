<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入内容確認</title>
<link rel="stylesheet" href="CSS/orderConfirmationt.css">
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
	<h1 class="title">注文内容を確認・変更する</h1>
<div class="abc">
<div class="box2">
<h1>お届け先住所</h1>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				${con.deliveryName}<br>
				${con.postalCode}<br>
				${con.address}<br>
				電話番号:${con.tel}
		</c:if>
	</c:forEach>
</div>
<div class="box2">
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
<div class="box3">
<a href="purchaseCompleted" class="btn btn--orange">購入を確定する</a>
<p>注文内容</p>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				商品の小計:<p class="p4">\ ${con.total}</p><br>
				配送料・手数料:<p class="p4">\500</p>
				<p class="p5">ご請求額:</p><p class="p6">\ ${con.total+500}</p>
		</c:if>
	</c:forEach>
	<h1>お届け日</h1>

<p><span id="deliveryDate"></span></p>
</div>
</div>

<div>
<h1 class="title">購入商品</h1>
	<c:forEach var="con" items="${result}">
<div class="box">
			<p class="p1">${con.name}</p>
			<p class="p2">個数:${con.orderCount}</p>
			<p class="p2">\ ${con.subTotal}</p>
			<p class="p3"><img src="${con.pictPath}"class="pic2"></p>
			</div>
	</c:forEach>

</div>
</body>
</html>