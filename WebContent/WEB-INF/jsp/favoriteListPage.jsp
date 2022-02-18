<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/productsDetailsScript.js"></script>
<link rel="stylesheet" href="CSS/purchaseHistory.css">
</head>
<body>

<h1>お気に入り一覧</h1>

<c:forEach var="con" items="${result}">
<div class="box">
			<img src="${con.pictPath}"width="400px"class="pic">
				<p class="p2">${con.name}</p>
				<p class="p2">${con.price}</p>
				<a href="deleteFavoriteForFavPage?itemId=${con.itemId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="favoriteBtn" onclick="favoriteClick(this);" value="${result.get(0).itemId}" style="border: 4px solid #0F0; background-color: #ffc0cb">❤︎</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>


</div>
</c:forEach>

</body>
</html>




