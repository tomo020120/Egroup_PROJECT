<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
<link rel="stylesheet" href="CSS/logout.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="allPosition">
<h1>ログアウトしますか？</h1>
<input type="button" onclick="history.back()" value="戻る">
<input type="button" onclick="location.href='executeLogout'" value="ログアウトする">
</div>
</body>
</html>