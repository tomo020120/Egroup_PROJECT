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
</head>
<body>
<header><%@include file="header.jsp"%></header>
<h1>クレジットカード情報編集</h1>
<p>${message}</p>
	<button id="addCreditCardButton">新しいクレジットカードを追加</button>

	<c:forEach var="creditCardInfo" items="${result}">
		<div class="creditCardInfo">
			<p>カード番号:${creditCardInfo.cardNo}</p>
			<p>種類:${creditCardInfo.cardCompany}</p>
			<p>クレジットカード名義人:${creditCardInfo.cardOwnerName}</p>
			<p>有効期限:${creditCardInfo.expirationDate}</p>
			<a href="updateCreditCardInfoForm?creditCardInfoId=${creditCardInfo.cardId}">変更</a> | <a href="deleteCreditCardInfoForm?creditCardInfoId=${creditCardInfo.cardId}">消去</a>
		</div>
	</c:forEach>

	<div id="addCreditCardFormArea">
		<h1>クレジットカードを追加</h1>
		<div id="flexContent">
			<form id="addCreditCardForm" method="post" action="addCreditCardInfo">
				カード番号<input id="creditCardNo" type="text" name="creditCardNo" maxLength="20">
				<span id="cardCompany"></span><br>
				クレジットカード名義人<input type="text" name="ownerName" maxLength="50"><br>
				有効期限
				<select name="month">
					<option value="">-</option>
					<option value="1">01</option>
					<option value="2">02</option>
					<option value="3">03</option>
					<option value="4">04</option>
					<option value="5">05</option>
					<option value="6">06</option>
					<option value="7">07</option>
					<option value="8">08</option>
					<option value="9">09</option>
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
	<p>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br>テスト<br></p>
</body>
</html>