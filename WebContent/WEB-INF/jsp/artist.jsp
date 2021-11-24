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
	<header><%@include file="header.jsp" %></header>
	<h1>artist</h1>
	<table border="1">
 <tr><th>写真</th><th>名前</th></tr>
<c:forEach var="artists" items="${result}">
  <tr>
  <td><a href="artistsDetails?artistId=${artists.artistId}"><img src="${artists.artistPict}"></a></td>

  	<td>${artists.artistName}</td>

  </tr>
 </c:forEach>
 </table>

</body>
</html>