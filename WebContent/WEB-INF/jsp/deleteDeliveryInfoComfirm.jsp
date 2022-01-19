<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>配送先情報消去</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<h1>配送先情報消去</h1>
	<input type="button" onclick="history.back()" value="戻る">
	<input type="button" onclick="location.href='deleteDeliveryInfo?deliveryInfoId=${result}'" value="消去します。">
</body>
</html>