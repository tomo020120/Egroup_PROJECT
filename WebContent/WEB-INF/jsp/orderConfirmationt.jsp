<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入内容確認</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/orderConfirmationt.css">
<script>
window.onload = function(){
	var dateObj = new Date();
	dateObj.setDate(dateObj.getDate() + 2); // 2日後にセット

	var fy = dateObj.getFullYear();
	var fm = dateObj.getMonth() + 1;
	var fd = dateObj.getDate();

	var fb = "日月火水木金土".charAt(dateObj.getDay());

	dateObj.setDate(dateObj.getDate() + 2); // 更に2日後(4日後)にセット

	var ly = dateObj.getFullYear();
	var lm = dateObj.getMonth() + 1;
	var ld = dateObj.getDate();
	var lb = "日月火水木金土".charAt(dateObj.getDay());

	document.getElementById("deliveryDate").innerHTML =fy+"年"+fm+"月"+fd+"日("+fb+")"+ "～"+ly+"年"+lm+"月"+ld+"日("+lb+")";
	}
</script>
</head>

<body>

	<h1 class="title">注文内容を確認</h1>
<div class="abc">
<div class="box2">
<h1>お届け先住所</h1>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				<c:out value="${con.deliveryName}"/><br>
				電話番号:${con.tel}<br>
				${con.postalCode}<br>
				<c:out value="${con.address}"/><br>

		</c:if>
	</c:forEach>
</div>
<div class="box2">
<h1>支払情報</h1>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				${con.cardNo}(カード下四桁)<br>
				${con.cardCompany}<br>
				<c:out value="${con.cardOwnerName}"/><br>
				${con.expirationDate}<br>

		</c:if>
	</c:forEach>
</div>
<div class="box3">
<a href="purchaseCompleted" class="btn btn--orange">購入を確定する</a>
<p>注文内容</p>
	<c:forEach var="con" items="${result}" varStatus="i">
		<c:if test="${i.first}" >
				商品の小計:<p class="p4 price">\ ${con.total}</p><br>
				配送料・手数料:<p class="p4">\500</p>
				<p class="p5">ご請求額:</p><p class="p6 price">\ ${con.total+500}</p>
		</c:if>
	</c:forEach>
	<h1>お届け日</h1>

<p class="p7"><span id="deliveryDate"></span></p>
</div>
</div>

<div>
<h1 class="title">購入商品</h1>
	<c:forEach var="con" items="${result}">
<div class="box">
			<p class="p1"><c:out value="${con.name}"/></p>
			<p class="p2">個数:${con.orderCount}</p>
			<p class="p2 price">\ ${con.subTotal}</p>
			<p class="p3"><img src="${con.pictPath}"class="pic2"></p>
			</div>
	</c:forEach>

</div>

<script>
	$(function(){
		var price = $(".price"); // 値段をカンマ区切りにする

		price.each(function(index,element){
			var priceText = $(element).text();
			priceText = (priceText.replace('\\','').trim());

			var convertPrice = getConvertPrice(priceText);

			$(element).text('\\ ' + convertPrice);
		})
	});

	function getConvertPrice(num){
		return Number(num).toLocaleString();
	}
</script>

</body>
</html>