<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header><%@include file="header.jsp" %></header>
<br/><br/><br/><br/><br/>
<h1>お気に入り一覧</h1>
<h2>写真　商品名　値段　</h2>
<c:forEach var="con" items="${result}">
			<img src="${con.pictPath}">
				${con.name}<br>
				${con.price}<br>

	</c:forEach>
<%@include file="header.jsp"%>
</body>
</html>




