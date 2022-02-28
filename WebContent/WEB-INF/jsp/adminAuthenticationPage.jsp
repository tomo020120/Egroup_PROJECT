<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者認証ページ</title>
<link rel="stylesheet" href="CSS/adminPage.css">
</head>
<body>

<h1>管理者認証ページ</h1>
<div class="pos">
<form method="post" action="executeAuthentication" autocomplete="off">
	認証コード:<input type="text" name="admiinAuthenticaionCode" maxLength="6">
	<input type="submit" value="OK">
</form>

メールが届かない場合:<a href="sendAdminAuthenticationMailAgain">再度メール送信</a>

</div>
</body>
</html>