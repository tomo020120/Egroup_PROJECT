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

 <a href="userInfoEdit?userId = "${sessionInfo.userId}>ユーザー情報編集</a>


	<a href="favorite">お気に入り</a>
	<a href="logout">ログアウト</a>
	<a href="purchaseHistory">注文履歴</a>

</body>
</html>

