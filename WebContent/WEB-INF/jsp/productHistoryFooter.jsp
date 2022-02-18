<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>閲覧履歴</title>
<link rel="stylesheet" href="CSS/productsStyle.css">
</head>

<body>
	最新の閲覧履歴  <div class="pos2"><a href="productHistory">閲覧履歴編集へ</a></div>



<div class="d-demo">
   <div class="d-demo__wrap">
       <ul class="d-demo__list d-demo__list--left">
  <c:forEach var="history" items="${result}">
    <li><a href="productsDetails?itemId=${history.itemId}"><img src="${history.pictPath}"width="400px">${history.name}</a></li>
    </c:forEach>
  </ul>
</div>
</div>


</body>
</html>