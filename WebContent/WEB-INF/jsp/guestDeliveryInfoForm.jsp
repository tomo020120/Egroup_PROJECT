<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報・配送先情報入力画面</title>
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script> <!-- 郵便番号による自動住所入力のライブラリ読み込み -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/guestDeliveryInfoFormScript.js"></script>
<link rel="stylesheet" href="CSS/guestDeliveryInfoFormStyle.css">
</head>
<body>

<div class=allPosition>
<h1>１お届け先住所</h1>
<div id="userInfoInputArea">
	【必須】氏名<br><input type="text" name="userName" maxLength="50"><br>
	【必須】メールアドレス<br><input type="text" name="mailAddress" maxLength="256">
</div>
<div id="addDeliveryInfoFormArea" class="animFormArea">
	<form id="addDeliveryInfoForm" class="h-adr" method="post" action="addDeliveryInfo" >
		<span class="p-country-name" style="display:none;">Japan</span> <!--日本内に検索設定  -->
		【必須】宛名<br><input type="text" name="deliveryName" maxlength="25"><br>
		【必須】電話番号(000-0000-0000)<br><input type="tel" name="firstTelephoneNumber" maxlength="3">-<input type="tel" name="secondTelephoneNumber" maxlength="4">-<input type="tel" name="thirdTelephoneNumber" maxlength="4"><br>
		【必須】郵便番号(ハイフンなし)<br><input type="text" class="p-postal-code" name="postalCode" size="7" maxlength="7"><br>
		【必須】住所<br><input type="text" class="p-region p-locality p-street-address p-extended-address" name="address"/><br>
		【必須】丁目・番地・号(例:1-2-3)<br><input type="text" name="houseNumber"><br>
		【任意】建物名・部屋番号(例:〇〇マンション〇〇号室)<br><input type="text" name="buildingName"><br>
		<button type="button" id="guestCreditCardInfoFormJumpBtn">次へ</button>
	</form>
</div>
</div>
</body>
</html>