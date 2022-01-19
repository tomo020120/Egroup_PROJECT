<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>配送先情報変更</title>
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script> <!-- 郵便番号による自動住所入力のライブラリ読み込み -->
<script src="js/updateDeliveryInfoFormScript.js"></script>
</head>
<body>
	<%@include file="header.jsp"%>
	<div id="deliveryFormArea">
		<form id="updateDeliveryInfoForm" class="h-adr" method="post" action="updateDeliveryInfo?deliveryInfoId=${result.deliveryInfoId}">
			<span class="p-country-name" style="display:none;">Japan</span> <!--日本内に検索設定  -->
			【必須】氏名:<input type="text" name="deliveryName" maxlength="25" value="${result.deliveryName}"><br>
			【必須】電話番号(000-0000-0000):<input type="text" id="firstTelephoneNumber" name="firstTelephoneNumber" maxlength="3">-<input type="text" id="secondTelephoneNumber" name="secondTelephoneNumber" maxlength="4">-<input type="text" id="thirdTelephoneNumber" name="thirdTelephoneNumber" maxlength="4"><br>
			【必須】郵便番号(ハイフンなし):<input type="text" class="p-postal-code" name="postalCode" size="7" maxlength="7" value="${result.postalCode}"><br>
			【必須】住所:<input type="text" id="address" class="p-region p-locality p-street-address p-extended-address" name="address"/><br>
			【必須】丁目・番地・号(例:1-2-3):<input type="text" id="houseNumber" name="houseNumber"><br>
			【必須】建物名・部屋番号(例:〇〇マンション〇〇号室)<input type="text" id="buildingName" name="buildingName"><br>
			<input type="submit" value="変更">
		</form>
	</div>
	<p>${message}</p>
	<script>
		setTelNumber('080-3333-3333');
		setAddress('東京都多摩市貝取/貝取/常盤マンション1-309');
	</script>
</body>
</html>