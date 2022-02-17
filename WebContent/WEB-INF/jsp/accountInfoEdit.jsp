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

	<p>${message}</p>
	<div class="box">
		<div class="content">ユーザー名:
		${sessionInfo.userName}
		<div class="button"><button onclick="location.href='editUserNameForm'" class="btn btn--white">編集</button></div></div>
   		<div class="content">メールアドレス:
   		${sessionInfo.mailAddress}
   		<div class="button"><button onclick="location.href='editUserMailAddressForm'" class="btn btn--white">編集</button></div></div>
   		<div class="content">現在のパスワード:
   		********
   		<div class="button"><button onclick="location.href='editPasswordForm'" class="btn btn--white">編集</button></div></div>
	</div>

   	<button onclick="location.href='userInfoEdit'"  class="btn btn--orange">完了</button>
</body>
</html>