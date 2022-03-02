<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お気に入り一覧</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="js/productsDetailsScript.js"></script>
<link rel="stylesheet" href="CSS/purchaseHistory.css">
</head>
<body>

<div id="empty"></div>
<div class="displayArea">
<h1>お気に入り一覧</h1>
<c:forEach var="con" items="${result}">
<div class="box">
			<a href="productsDetails?itemId=${con.itemId}"><img src="${con.pictPath}"width="400px"class="pic"></a>
				<p class="p2"><c:out value="${con.name}" /></p>
				<p class="p2 price"><c:out value="\ ${con.price}" /></p>
				<a href="deleteFavoriteForFavPage?itemId=${con.itemId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="favoriteBtn" onclick="favoriteClick(this);" value="${result.get(0).itemId}" style="border: 2px solid #000; background-color: #ffc0cb">❤︎</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
<input id="itemId" type="hidden" value="${con.itemId}">
</div>
</c:forEach>
</div>

<script>
	$(function(){
		var itemId = $("#itemId").val();

		if(itemId == undefined){
			$(".displayArea").hide();
			$("#empty").html("<h1 style='text-align:center;'>お気に入りに追加された商品は存在しません</h1>");
		}else{
			$(".container").show();
			("#empty").html("");
		}
	});
</script>

</body>
</html>




