<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<%@include file="/CSS/productsStyle.css" %>
</head>
<body>
<header><%@include file="header.jsp" %></header>
<h1>商品一覧</h1>
<table border="1">
 <tr><th>商品ID</th><th>画像</th><th>商品名</th><th>価格</th></tr>
 <c:forEach var="product" items="${result}">
  <tr>
  	<td>${product.itemId}</td>
  	<!-- ↓hrefまだ作成中だよ -->
  	<td><a href="productsDetails?itemId=${product.itemId}"><img src="${product.pictPath}"></a></td>
  	<td>${product.name}</td>
  	<td>${product.price}</td>
  </tr>
 </c:forEach>
 </table>

</body>
</html>