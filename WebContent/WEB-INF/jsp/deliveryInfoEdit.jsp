<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>配送先情報編集</title>
<link rel="stylesheet" href="CSS/deliveryInfoEditStyle.css">
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script> <!-- 郵便番号による自動住所入力のライブラリ読み込み -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/deliveryInfoEditScript.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<h1>配送先情報編集</h1>
	<button id="addDeliveryInfoButton">新しい住所を追加</button>

	<div id="deliveryInfoList">
		<c:forEach var="deliveryInfo" items="${result}">
			<div class="deliveryInfo">
				<p>氏名:<span class="deliveryName">${deliveryInfo.deliveryName}</span></p>
				<p>電話番号:<span class="tel">${deliveryInfo.tel}</span></p>
				<p>郵便番号:<span class="postalCode">${deliveryInfo.postalCode}</span></p>
				<p>住所:<span class="address">${deliveryInfo.address}</span></p>
				<button type="button" class="openUpdateFormButton" value="${deliveryInfo.deliveryInfoId}">編集</button><button type="button" class="openDeleteComfirmButton" value="${deliveryInfo.deliveryInfoId}">消去</button>
			</div>
		</c:forEach>
	</div>

	<div id="addDeliveryInfoFormArea" class="animFormArea">
		<h1>配送先情報の追加</h1>
		<form id="addDeliveryInfoForm" class="h-adr" method="post" action="addDeliveryInfo" >
			<span class="p-country-name" style="display:none;">Japan</span> <!--日本内に検索設定  -->
			【必須】氏名:<input type="text" name="deliveryName" maxlength="25"><br>
			【必須】電話番号(000-0000-0000):<input type="text" name="firstTelephoneNumber" maxlength="3">-<input type="text" name="secondTelephoneNumber" maxlength="4">-<input type="text" name="thirdTelephoneNumber" maxlength="4"><br>
			【必須】郵便番号(ハイフンなし):<input type="text" class="p-postal-code" name="postalCode" size="7" maxlength="7"><br>
			【必須】住所:<input type="text" class="p-region p-locality p-street-address p-extended-address" name="address"/><br>
			【必須】丁目・番地・号(例:1-2-3):<input type="text" name="houseNumber"><br>
			【必須】建物名・部屋番号(例:〇〇マンション〇〇号室)<input type="text" name="buildingName"><br>
			<button id="canselButton" type="button">キャンセル</button>	<button type="button" id="executeAddButton">追加</button>
		</form>
	</div>

	<div id="updateDeliveryFormArea" class="animFormArea">
		<h1>配送先情報編集</h1>
		<form id="updateDeliveryInfoForm" class="h-adr" method="post" action="updateDeliveryInfo">
			<span class="p-country-name" style="display:none;">Japan</span> <!--日本内に検索設定  -->
			【必須】氏名:<input id="deliveryName" type="text" name="deliveryName" maxlength="25"><br>
			【必須】電話番号(000-0000-0000):<input id="firstTelNo" type="text" id="firstTelephoneNumber" name="firstTelephoneNumber" maxlength="3">-<input type="text" id="secondTelNo" name="secondTelephoneNumber" maxlength="4">-<input type="text" id="thirdTelNo" name="thirdTelephoneNumber" maxlength="4"><br>
			【必須】郵便番号(ハイフンなし):<input id="postalCode" type="text" class="p-postal-code" name="postalCode" size="7" maxlength="7"><br>
			【必須】住所:<input type="text" id="address" class="p-region p-locality p-street-address p-extended-address" name="address"/><br>
			【必須】丁目・番地・号(例:1-2-3):<input type="text" id="houseNumber" name="houseNumber"><br>
			【必須】建物名・部屋番号(例:〇〇マンション〇〇号室)<input type="text" id="buildingName" name="buildingName"><br>
			<button id="updateCansel" type="button">キャンセル</button><button id="executeUpdateButton" type="button">編集完了</button>
		</form>
	</div>

	<div id="deleteDeliveryInfoArea" class="animFormArea">
		<h1>こちらの配送先情報を消去してよろしいでしょうか</h1>
		<p>氏名:<span class="name"></span></p>
		<p>電話番号:<span class="tel"></span></p>
		<p>郵便番号:<span class="postalCode"></span></p>
		<p>住所:<span class="address"></span></p>
		<button id="deleteCansel" type="button">キャンセル</button>	<button id="executeDeleteButton">消去する</button>
	</div>

	<p>${message}</p>
</body>
</html>