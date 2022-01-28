<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>

<link rel="stylesheet" href="CSS/artistStyle.css">
</head>

<body>
	<%@include file="header.jsp" %>
	<h1>artist</h1>
<div id="item-list">
<ul>
	<c:forEach var="artists" items="${result}">
	<li>

			<a href="artistsDetails?artistId=${artists.artistId}">
				<div class="img_wrap"><img src="${artists.artistPict}"width="225" height="225">

			</a>
			<p>${artists.artistName}</p>
		</div>
	</li>

 	</c:forEach>
</ul>
</div>

</body>
</html>