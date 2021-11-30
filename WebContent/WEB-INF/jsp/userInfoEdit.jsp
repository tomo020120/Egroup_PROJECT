<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント変更</title>
</head>
<body>
	<h1>アカウント変更</h1>
   <header><%@include file="header.jsp" %></header>
    ユーザー名<input type='text' name='name'/><br/>
    パスワード<input type='text' id="emailAddress"/><br />
   メールアドレス<input type='text' name='pass'/><br/>
    カード番号<input type='text' name='cartCode'/><br>
    住所<input type='text' name='address'/><br>
    郵便番号<input type='text' name='postalCode'/><br>
    電話番号<input type='text' name='tel'/><br>
    <input type="button" value="登録"  />



</body>
</html>