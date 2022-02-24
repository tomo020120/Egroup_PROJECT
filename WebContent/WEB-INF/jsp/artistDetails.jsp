<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" href="CSS/artistDetailStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>



<div class="allPosition">
	 <c:forEach var="detail" items="${result}">
		<h1>${detail.artistName}</h1>
		<img src="${detail.artistPict}" width="800" height="800"><br>
	</c:forEach>
</div>
<c:forEach var="detail" items="${result}">

	<div class="artistInfo">
		<h3>GROUP</h3>
		${detail.group}<br>
	</div>
	<div class="artistInfo">
		<h3>COUNTORY</h3>
		${detail.coutory}<br>
	</div>
		<div class="productsPosition">
		<h2>SIGNATURE MODEL</h2>
		<a id="itemPict" href="productsDetails?itemId=${detail.itemId}"><img src="${detail.pictPath}" width="500" height="200"></a><br>
		<div id="dummy"></div>
		<p>　${detail.name}</p>
		</div>
	<input id="itemId" type="hidden" value="${detail.pictPath}">
	</c:forEach>


	<script>
		$(function(){
			console.log($("#itemId").val());

			if($("#itemId").val() === ''){
				$("#itemPict").hide();
				$("#dummy").html("<img src='images/null.png' width='500px' height='200px';>");
			}else{
				$("#itemPict").show();
				$("#dummy").html("");
			}
		});
	</script>
</body>

