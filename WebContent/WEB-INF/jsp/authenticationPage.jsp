<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>認証コード入力</title>
</head>
<body>

<form method="post" action="updateUserMailAddress">
	認証コード:<input type="text" name="authenticaionCode" maxLength="6">
	<input type="submit" value="OK">
</form>
<button onclick="history.back();">戻る</button>
<p>${message}</p>
</body>
</html>