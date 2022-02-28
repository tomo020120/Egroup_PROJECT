<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クレジットカード情報入力</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/guestCreditCardInfoFormScript.js"></script>
<link rel="stylesheet" href="CSS/guestDeliveryInfoFormStyle.css">
</head>
<body>

<div class="allPosition">
<h1>2 クレジットカード情報入力</h1>
<div id="addCreditCardFormArea" class="animFormArea">
	<form id="addCreditCardForm" method="post" action="addCreditCardInfo">
		カード番号<br><input id="creditCardNo" class="target" type="text" name="creditCardNo" maxLength="16"><br>
				<div class="errorText cardNoError"></div>

				<div id="cardCompany" class="target"></div>

				クレジットカード名義人<br><input type="text" id="inputCardOwnerName" class="target" name="cardOwnerName" maxLength="50"><br>
				<div class="errorText cardOwnerNameError"></div>

				有効期限<br>
				<select id="inputMonth" class="target" name="month">
					<option value="">-</option>
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>月 /
				<select id="inputYear" class="target" name="year">
					<option value="">-</option>
					<option value="2022">2022</option>
					<option value="2023">2023</option>
					<option value="2024">2024</option>
					<option value="2025">2025</option>
					<option value="2026">2026</option>
					<option value="2027">2027</option>
					<option value="2028">2028</option>
					<option value="2029">2029</option>
					<option value="2030">2030</option>
					<option value="2031">2031</option>
					<option value="2032">2032</option>
					<option value="2033">2033</option>
					<option value="2034">2034</option>
					<option value="2035">2035</option>
					<option value="2036">2036</option>
					<option value="2037">2037</option>
					<option value="2038">2038</option>
					<option value="2039">2039</option>
					<option value="2040">2040</option>
					<option value="2041">2041</option>
					<option value="2042">2042</option>
				</select>年 <br>
				<div class="errorText dateError"></div>
				セキュリティコード<br><input id="inputSecurityCode" class="target" type="text" name="securityCode" maxLength="3">
				<div class="errorText securityCodeError"></div>
	</form>
		<button type="button" id="guestPurchaseConfirmPageJumpBtn">次へ</button>

</div>
</div>
</body>
</html>