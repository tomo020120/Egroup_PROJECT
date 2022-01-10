<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント情報編集</title>
</head>
<body>
	<header><%@include file="header.jsp" %></header>
	<p>${message}</p>
	<div>
		ユーザー名<input type='text' name='name' value="${sessionInfo.userName}" readOnly><span><button onclick="location.href='editUserNameForm'">編集</button></span><br>
   		メールアドレス<input id='mailAddress' type='text' name='mail' value="${sessionInfo.mailAddress}" readOnly><span><button onclick="location.href='editUserMailAddressForm'">編集</button></span><br>
   		現在のパスワード<input type='password' id='inputpass' name='pass' maxlength="20" value="${sessionInfo.userPassword}" readOnly><span><button onclick="location.href='editPasswordForm'">編集</button></span><br>
	</div>

   	<button onclick="location.href='userInfoEdit'">完了</button>
</body>
</html>