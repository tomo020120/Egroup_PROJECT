<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報編集</title>
<link rel="stylesheet" href="CSS/accountInfoEditStyle.css">
</head>
<body>
	<%@include file="header.jsp" %>
	<p>${message}</p>
	<div>
		ユーザー名:<br>
		${sessionInfo.userName}
		<button onclick="location.href='editUserNameForm'" class="btn btn--white">編集</button><br>
   		メールアドレス:<br>
   		${sessionInfo.mailAddress}
   		<button onclick="location.href='editUserMailAddressForm'" class="btn btn--white">編集</button><br>
   		現在のパスワード:<br>
   		********
   		<button onclick="location.href='editPasswordForm'" class="btn btn--white">編集</button><br>
	</div>

   	<button onclick="location.href='userInfoEdit'"  class="btn btn--orange">完了</button>
</body>
</html>