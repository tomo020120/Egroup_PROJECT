<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート追加完了</title>

<link rel="stylesheet" href="CSS/addCartComplete.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<div class="item">
<div class="box">
<div class="pic">
<c:forEach var="con" items="${result}">

<img src="${con.pictPath}"width="50%">
<img src="images/check.png" width="5%"class="pic1">
<h1>追加されました</h1>
</c:forEach>
</div>
</div>
</div>

<div class="item">
<div class=box2>
<c:forEach var="con" items="${result}">
<p>カート内の小計:${con.totalAmount}</p>
<a href="cart" class="cartbtn">カートに進む</a><br>
<a href="purchase"class="regibtn">レジに進む</a><br>
<a href="products?categoryId=${con.categoryId}" class="shopbtn">買い物を続ける</a><br>
</c:forEach>
</div>
</div>
</div>
</body>
</html>
