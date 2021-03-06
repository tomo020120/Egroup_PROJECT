<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSS/cartStyle.css">
</head>
<body>
<div id="empty"></div>

<div class="container">
<div class="item">

	<h1>ショッピングカート</h1>


	 <c:forEach var="cart" items="${result}">
	 <div class="box">
		<img src="${cart.pictPath}" class="pic">
		<div class="pos">
		<p class="p1"><c:out value="${cart.name}"/></p>
		<p class="p2">数量:${cart.orderCount}</p>
		<p class="p2 price">\ ${cart.subTotal}</p>
		<p class="p3"><a href="deleteCartProduct?itemId=${cart.itemId}"><img src="images/batu.png" class="pic2"></a></p>
		</div>
	</div>
	<input id="itemId" type="hidden" value="${cart.itemId}">
	</c:forEach>

</div>
<div class="item">
<div class=box2>
<h1>注文内容</h1>
<hr>
小計:<p class="p4 price">\<c:choose><c:when test="${result.size() != 0 }">${result.get(0).total}</c:when><c:otherwise>0</c:otherwise></c:choose></p>
<br>配送料：<p class="p4">\500</p>
<hr>
<br>合計:<p class="p4 price">\<c:choose><c:when test="${result.size() != 0 }">${result.get(0).total+500}</c:when><c:otherwise>0</c:otherwise></c:choose></p>
	<div>
		<p><a href="purchase" class="shopbtn">レジへ進む</a></p>
	</div>
</div>
</div>
</div>

<script>
	$(function(){
		var price = $(".price"); // 値段をカンマ区切りにする

		price.each(function(index,element){
			var priceText = $(element).text();
			priceText = (priceText.replace('\\','').trim());

			var convertPrice = getConvertPrice(priceText);
			$(element).text('\\ ' + convertPrice);
		});

		var itemId = $("#itemId").val();

		if(itemId == undefined){
			$(".container").hide();
			$("#empty").html("<h1 style='text-align:center;'>カートに商品は入っていません</h1>");
		}else{
			$(".container").show();
			("#empty").html("");
		}
	});

	function getConvertPrice(num){
		return Number(num).toLocaleString();
	}

</script>

</body>
</html>