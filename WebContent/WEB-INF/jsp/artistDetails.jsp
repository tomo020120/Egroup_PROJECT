<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<%@include file="/CSS/productsDetailsStyle.css" %>
</head>
<body>
	<header><%@include file="header.jsp" %></header>
	<table border="1">
		<tr>
		<th>アーティスト名</th>
		<th>アーティスト写真</th>
		<th>国</th>
		<th>グループ</th>
		<th>商品名</th>
		<th>商品の写真</th>
	</tr>
	 <c:forEach var="detail" items="${result}">
	<tr>
		<td>${detail.artistName}</td>
		<td><img src="${detail.artistPict}"></td>
		<td>${detail.coutory}</td>
		<td>${detail.group}</td>
		<td>${detail.name}</td>
		<td><img src="${detail.pictPath}"></td>
	</tr>
	</c:forEach>
	</table>
</body>

