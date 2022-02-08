<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クレジットカード</title>

<link rel="stylesheet" href="CSS/chiceCreditStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/purchaseCreditCardScript.js"></script>
<link rel="stylesheet" href="CSS/creditCardInfoEditStyle.css">
</head>
<body>
<%@include file="header.jsp"%>
<h1>クレジットカード</h1>
<hr>
<p>${message}</p>

	<div class="creditCardInfo">
		<c:forEach var="creditCardInfo" items="${result}">
			<div id="creditCardList">
				<p>カード番号(下四桁):<span class="lastFourDisits">${creditCardInfo.cardNo}</span></p>
				<p>種類:<span class="cardCompany">${creditCardInfo.cardCompany}</span></p>
				<p>クレジットカード名義人:<span class="cardOwnerName">${creditCardInfo.cardOwnerName}</span></p>
				<p>有効期限:<span class="expirationDate">${creditCardInfo.expirationDate}</span></p>
				<a href="orderConfirmation?creditCardInfoId=${creditCardInfo.cardId}" class="decisionButton">決定</a><br>
				<button class="openUpdateFormButton" type="button" value="${creditCardInfo.cardId}">編集</button>
				<button class="openDeleteComfirmButton" type="button" value="${creditCardInfo.cardId}">消去</button>
			</div>
		</c:forEach>
	</div>



	<div id="updateCardFormArea" class="animFormArea">
		<h1>クレジットカードを編集</h1>
		<div>
			<form id="updateCreditCardInfoForm" method="post" action="updateCreditCardInfo">
				カード番号(下四桁)<input id="lastFourDisitsCreditCardNo" type="text" name="creditCardNo" readOnly>
				<textarea id="cardCompany" name="cardCompany" readOnly></textarea><br>
				クレジットカード名義人<input id="cardOwnerName" type="text" name="cardOwnerName" maxLength="50"><br>
				有効期限
				<select id="month" name="month">
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
				<select id="year" name="year">
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
				<button id="updateCansel" type="button">キャンセル</button>	<button id="executeUpdateButton">編集完了</button>
			</form>
		</div>
	</div>

	<div id="deleteCreditCardComfirmPageArea" class = "animFormArea">
		<h1>こちらのクレジットカードを消去してよろしいでしょうか</h1>
		カード番号(下四桁):<span class="lastFourDisits">${creditCardInfo.cardNo}</span><br>
		種類:<span class="cardCompany">${creditCardInfo.cardCompany}</span><br>
		クレジットカード名義人:<span class="cardOwnerName">${creditCardInfo.cardOwnerName}</span><br>
		有効期限:<span class="expirationDate">${creditCardInfo.expirationDate}</span><br>
		<button id="deleteCansel" type="button">キャンセル</button>	<button id="executeDeleteButton">消去する</button>
	</div>
<hr>
<div class="allPosition">
	<h1>クレジットカードを追加</h1>
		<div id="flexContent">
			<form id="addCreditCardForm" method="post" action="PurchseInsertCreditCardInfo">
				カード番号<br><input id="creditCardNo" type="text" name="creditCardNo" maxLength="19">
				<textarea id="cardCompanyArea" name="cardCompany" readOnly></textarea><br>
				クレジットカード名義人<br><input type="text" name="cardOwnerName" maxLength="50"><br>
				有効期限<br>
				<select name="month">
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
				<select name="year">
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
				セキュリティコード<br><input type="text" name="securityCode" maxLength="3">
			</form>

			<div id="availableCardCompanyList">
			使用できるカード
				<p>VISA</p>
				<p>JCB</p>
				<p>MasterCard</p>
				<p>American Express</p>
				<p>Diners Club</p>
			</div>
		</div>
		<button id="canselButton" type="button">キャンセル</button>	<button id="executeAddButton">カードを追加</button>
</div>
</body>
</html>
