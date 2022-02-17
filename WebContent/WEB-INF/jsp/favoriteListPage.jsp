<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/productsDetailsScript2.js"></script>

</head>
<body>
<header></header>
<br/><br/><br/><br/><br/>
<h1>お気に入り一覧</h1>
<h2>写真　商品名　値段　</h2>
<c:forEach var="con" items="${result}">
			<img src="${con.pictPath}">
				${con.name}<td><a href="deleteFavoriteForFavPage?itemId=${con.itemId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="favoriteBtn" onclick="favoriteClick(this);" value="${result.get(0).itemId}" style="border: 4px solid #0F0; background-color: #ffc0cb">❤︎</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td><br>
				${con.price}<br>

</c:forEach>
<%@include file="header.jsp"%>
</body>
</html>




