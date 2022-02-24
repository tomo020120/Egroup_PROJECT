<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー切り替え</title>
<link rel="stylesheet" href="CSS/error.css">
</head>
<body>
<div class="error">
	<h1>そのページにアクセスする権限は現在ログインしているユーザーにはありません</h1>
	<a href="switchUser">ログアウトして、ほかのユーザーに切り替える</a>
	<a href="javascript:history.back()">戻る</a>
</div>
</body>
</html>