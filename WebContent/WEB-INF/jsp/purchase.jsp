<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お届け先</title>
<link rel="stylesheet" href="CSS/deliveryInfoEditStyle.css">
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script> <!-- 郵便番号による自動住所入力のライブラリ読み込み -->
</head>
<body>
	<%@include file="header.jsp"%>
	<h1>お届け先住所の選択</h1>
	<h1>最近使用した住所</h1>

	<c:forEach var="deliveryInfo" items="${result}">
		<div class="deliveryInfo">
			<p>氏名:${deliveryInfo.deliveryName}</p>
			<p>電話番号:${deliveryInfo.tel}</p>
			<p>郵便番号:${deliveryInfo.postalCode}</p>
			<p>住所:${deliveryInfo.address}</p>
			<a href="choiceCredit?deliveryInfoId=${deliveryInfo.deliveryInfoId}">決定</a>
		</div>
	</c:forEach>
	<h1>新規配送先登録</h1>
	<div id="deliveryFormArea">
		<form id="registDeliveryInfoForm" class="h-adr" method="post" action="addDelivery" >
			<span class="p-country-name" style="display:none;">Japan</span> <!--日本内に検索設定  -->
			【必須】氏名:<input type="text" name="deliveryName" maxlength="25"><br>
			【必須】電話番号(000-0000-0000):<input type="text" name="firstTelephoneNumber" maxlength="3">-<input type="text" name="secondTelephoneNumber" maxlength="4">-<input type="text" name="thirdTelephoneNumber" maxlength="4"><br>
			【必須】郵便番号(ハイフンなし):<input type="text" class="p-postal-code" name="postalCode" size="7" maxlength="7"><br>
			【必須】住所:<input type="text" class="p-region p-locality p-street-address p-extended-address" name="address"/><br>
			【必須】丁目・番地・号(例:1-2-3):<input type="text" name="houseNumber"><br>
			【必須】建物名・部屋番号(例:〇〇マンション〇〇号室)<input type="text" name="buildingName"><br>
			<input type="submit" value="完了">
		</form>
	</div>
	<p>${message}</p>

</body>
</html>