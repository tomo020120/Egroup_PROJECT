<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<%@include file="/CSS/artistStyle.css" %>
</head>
<body>
	<%@include file="header.jsp" %>
	<h1>artist</h1>


	<c:forEach var="artists" items="${result}">

	  	<div class=pic><a href="artistsDetails?artistId=${artists.artistId}"><img src="${artists.artistPict}"></a></div>
		<div><p>${artists.artistName}</p></div>


 </c:forEach>


</body>
</html>