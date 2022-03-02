<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報編集</title>
<link rel="stylesheet" href="CSS/accountInfoEditStyle.css">
</head>
<body>


	<div class="box">
		<div class="content">ユーザー名:
		<c:out value="${sessionInfo.userName}" />
		<div class="button"><button onclick="location.href='editUserNameForm'" class="btn btn--white">編集</button></div>
		</div>
   		<div class="content">メールアドレス:
   		<c:out value="${sessionInfo.mailAddress}"/>
   		<div class="button"><button onclick="location.href='editUserMailAddressForm'" class="btn btn--white">編集</button></div>
   		</div>
   		<div class="content">現在のパスワード:
   		********
   		<div class="button"><button onclick="location.href='editPasswordForm'" class="btn btn--white">編集</button></div>
   		</div>
	</div>

   	<button onclick="location.href='userInfo'"  class="btn btn--orange">完了</button>
</body>
</html>