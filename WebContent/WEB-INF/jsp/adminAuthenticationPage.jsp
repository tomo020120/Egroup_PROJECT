<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者認証ページ</title>
</head>
<body>

<h1>管理者認証ページ</h1>
<form method="post" action="executeAuthentication" autocomplete="off">
	認証コード:<input type="text" name="admiinAuthenticaionCode" maxLength="6">
	<input type="submit" value="OK">
</form>
<button onclick="history.back();">戻る</button>
メールが届かない場合:<a href="sendAdminAuthenticationMailAgain">再度メール送信</a>
<p>${message}</p>
</body>
</html>