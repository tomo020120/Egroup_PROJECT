<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お届け先</title>

</head>
<body>
	<%@include file="header.jsp"%>
	<h1>クレジットカードの選択</h1>
	<h1>最近使用したクレジットカード</h1>

	<c:forEach var="creditCardInfo" items="${result}">
		<div class="creditCardInfo">
			<p>カード番号:${creditCardInfo.cardNo}</p>
			<p>種類:${creditCardInfo.cardCompany}</p>
			<p>クレジットカード名義人:${creditCardInfo.cardOwnerName}</p>
			<p>有効期限:${creditCardInfo.expirationDate}</p>
			<a href="updateCreditCardInfoForm?creditCardInfoId=${creditCardInfo.cardId}">変更</a> | <a href="deleteCreditCardInfoForm?creditCardInfoId=${creditCardInfo.cardId}">消去</a>
			<a href="orderConfirmation?creditCardInfoId=${creditCardInfo.cardId}">これに決めた</a>
		</div>
	</c:forEach>
	<h1>新しいクレジットカード</h1>




</body>
</html>