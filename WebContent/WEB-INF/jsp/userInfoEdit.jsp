<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント変更</title>
<%@ include file="/CSS/userInfoEditStyle.css" %>
</head>
<body>
	<h1>アカウント変更</h1>
   <header><%@include file="header.jsp" %></header>
   <input type="button" value="編集"  /><br/>
    ユーザー名<input type='text' name='name' value="${sessionInfo.userName}"/><br>
    メールアドレス<input type='text' id="emailAddress" value="${sessionInfo.mailAddress}"/><br>
    パスワード<input type='password' name='pass' value="${sessionInfo.userPassword}"/>
    <div>
	    支払いクレジットカード情報:<input type="button" value="追加"  /><input type="button" value="削除"  /><br>
	</div>
	<div>
		<c:forEach var="card" items="${sessionScope.userCardInfo}">
			<div id="cardBox" class="box-design">
				カード番号<input type="text" name="cardNo" value="${card.cardNo}"><br>
				会社<input type="text" name="cardCompany" value="${card.cardCompany}"><br>
				カード名義<input type="text" name="cardOwnerName" value="${card.cardOwnerName}"><br>
				有効期限<input type="text" name="expirationDate" value="${card.expirationDate}"><br>
			</div>
		</c:forEach>
	</div>

	<div>
	    配送先情報:<input type="button" value="追加"  /><input type="button" value="削除"  />
	</div>
	<div>
		<c:forEach var="address" items="${sessionScope.userAddressInfo}">
			<div id="addressBox" class="box-design">
				郵便番号<input type="text" name="postalCode" value="${address.postalCode}"><br>
				住所<input type="text" name="address" value="${address.address}"><br>
				電話番号<input type="text" name="tel" value="${address.tel}"><br>
			</div>
		</c:forEach>
	</div>

	<form method="post" action="userInfoEditConfirm">
		<input type="submit" value="編集完了">
	</form>

</body>
</html>