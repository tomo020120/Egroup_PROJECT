<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クレジットカード情報編集</title>
<link rel="stylesheet" href="CSS/creditCardInfoEditStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="js/creditCardInfoEditScript.js"></script>
<script src="js/creditCardInfoInputCheckScript.js"></script>
</head>
<body>

<h1>クレジットカード情報編集</h1>

<div class="creditCardInfo">
	<button id="addCreditCardButton">新しいクレジットカードを追加</button>

		<c:forEach var="creditCardInfo" items="${result}">
			<div id="creditCardList">
				カード番号(下四桁):<span class="lastFourDisits">${creditCardInfo.cardNo}</span><br>
				種類:<span class="cardCompany">${creditCardInfo.cardCompany}</span><br>
				クレジットカード名義人:<span class="cardOwnerName"><c:out value="${creditCardInfo.cardOwnerName}" /></span><br>
				有効期限:<span class="expirationDate">${creditCardInfo.expirationDate}</span><br>
				<button class="openUpdateFormButton" type="button" value="${creditCardInfo.cardId}">編集</button><button class="openDeleteComfirmButton" type="button" value="${creditCardInfo.cardId}">消去</button>
			</div>
		</c:forEach>
	</div>

	<div id="addCreditCardFormArea" class="animFormArea">
		<h2>クレジットカードを追加</h2>
		<div id="flexContent">
			<form id="addCreditCardForm" method="post" action="" autocomplete="off">
				カード番号<br><input id="creditCardNo" type="text" name="creditCardNo" maxLength="16"><br>
				<div class="errorText cardNoError"></div>

				<div id="cardCompany"></div>

				クレジットカード名義人<br><input type="text" id="inputCardOwnerName" name="cardOwnerName" maxLength="50"><br>
				<div class="errorText cardOwnerNameError"></div>

				有効期限<br>
				<select id="inputMonth" name="month">
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
				<select id="inputYear" name="year">
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
				セキュリティコード<br><input id="inputSecurityCode" type="text" name="securityCode" maxLength="4">
				<div class="errorText securityCodeError"></div>
			</form>

			<div id="availableCardCompanyList">
			使用できるカード
				<p>VISA</p>
				<p>JCB</p>
				<p>MasterCard</p>
				<p>Diners Club</p>
			</div>
		</div>
		<button id="canselButton" type="button">キャンセル</button>	<button id="executeAddButton">カードを追加</button>

	</div>

	<div id="updateCardFormArea" class="animFormArea">
		<h2>クレジットカードを編集</h2>
		<div class="crepo">
			<form id="updateCreditCardInfoForm" method="post" action="updateCreditCardInfo">
				カード番号(下四桁)<br><input id="lastFourDisitsCreditCardNo" type="text" name="creditCardNo" readOnly><br>
				<div id="cardCompany"></div>

				クレジットカード名義人<br><input id="cardOwnerName" type="text" name="cardOwnerName" maxLength="50"><br>
				<div class="errorText cardOwnerNameError"></div>

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
				<div class="errorText dateError"></div>
			</form>
				<button id="updateCansel" type="button">キャンセル</button>	<button id="executeUpdateButton">編集完了</button>
		</div>
	</div>

	<div id="deleteCreditCardComfirmPageArea" class = "animFormArea">
		<h3>こちらのクレジットカードを消去してよろしいでしょうか</h3>
		<div class="pos">
		カード番号(下四桁):<span class="lastFourDisits">${creditCardInfo.cardNo}</span><br>
		種類:<span class="cardCompany">${creditCardInfo.cardCompany}</span><br>
		クレジットカード名義人:<span class="cardOwnerName">${creditCardInfo.cardOwnerName}</span><br>
		有効期限:<span class="expirationDate">${creditCardInfo.expirationDate}</span><br>
		<button id="deleteCansel" type="button">キャンセル</button>	<button id="executeDeleteButton">消去する</button>
	</div>
	</div>
</body>
</html>