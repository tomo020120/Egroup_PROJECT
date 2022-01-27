<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート追加完了</title>
<%@include file="/CSS/addCartComplete.css" %>
</head>
<body>
<%@include file="header.jsp" %>
<h1>カートに追加されました</h1>
<c:forEach var="con" items="${result}">
				<img src="${con.pictPath}">
</c:forEach>
<br>
<a href="cart" class="btn btn--white btn--radius">カートに進む</a>
<a href="purchase" class="btn btn--orange btn--radius">レジに進む</a>
<a href="products" class="">買い物を続ける</a>
</body>
</html>
