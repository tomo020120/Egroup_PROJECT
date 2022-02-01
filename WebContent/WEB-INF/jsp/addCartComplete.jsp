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

<c:forEach var="con" items="${result}">
<h1>カートに追加されました</h1>
				<img src="${con.pictPath}">
</c:forEach>
</div>
</div>
<div class="item">
<div class=box2>
<a href="cart">カートに進む</a><br>
<a href="purchase">レジに進む</a><br>
<a href="products" class="">買い物を続ける</a><br>
</div>
</div>
</div>
</body>
</html>
