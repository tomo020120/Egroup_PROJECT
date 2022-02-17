<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メールアドレス変更</title>
</head>
<body>

	<h1>メールアドレス変更</h1>
	<form method="post" action="judgeCorrectUserMailAddress">
		新しいメールアドレス:<input type="text" name="newUserMailAddress" maxLength="256">
		<input type="submit" value="変更を保存">
	</form>
	<p>${message}</p>
</body>
</html>