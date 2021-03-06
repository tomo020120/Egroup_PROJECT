<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入内容確認</title>
<link rel="stylesheet" href="CSS/guestPurchaseConfirmPage.css">
<link rel="stylesheet" href="CSS/orderConfirmationt.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="js/guestPurchaseConfirmPageScript.js"></script>
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

	<h1 class="title">購入内容を確認</h1>
	<div class="abc">
<div class="box2">
<h1>お届け先住所</h1>
	<div id="guestDeliveryInfoList"></div>
</div>

<div class="box2">

<h1>支払情報</h1>
	<div id="guestCreditCardInfoList"></div>

</div>
<div class="box2">

<button id="guestPurchaseDetermineBtn" class="btn btn--orang">購入を確定</button>
<p>注文内容</p>
		商品の小計: <span id="subTotal" class="p4"></span><br>
		配送料・手数料:<p class="p4">\500</p>

		<p class="p5">ご請求額:</p><p class="p6" id="BillingAmount"></p>



<h1>お届け日</h1>

<p class="p7"><span id="deliveryDate"></span></p>
</div>
</div>
<div>
<h1 class="title">購入商品</h1>
<div class="box">

<div id="itemName" class="p1"></div>
<div id="itemCount" class="p2"></div>
<div id="itemPrice" class="p2"></div>
<div id="itemPic" class="pic3" ></div>
</div>
</div>




</body>
</html>