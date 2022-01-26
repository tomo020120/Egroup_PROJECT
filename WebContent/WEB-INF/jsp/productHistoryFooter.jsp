<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>閲覧履歴</title>
</head>

<body>
	最新の閲覧履歴
	<table border="1">
	
	<c:forEach var="history" items="${result}">
	<tr>
		<td><a href="productsDetails?itemId=${history.itemId}">${history.name}</a></td>
		<td>${history.price}円</td>
		<td><a href="productsDetails?itemId=${history.itemId}"><img src="${history.pictPath}"></a></td>
	</tr>
	</c:forEach>
	
	</table>

</body>
</html>