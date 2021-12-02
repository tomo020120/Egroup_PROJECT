<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント変更</title>
</head>
<body>
	<h1>アカウント変更</h1>
   <header><%@include file="header.jsp" %></header>
   <input type="button" value="編集"  /><br/>
    ユーザー名<input type='text' name='name' value="${result.userName}"/>
    メールアドレス<input type='text' id="emailAddress" value="${result.emailAddress}"/>
    パスワード<input type='text' name='pass' value="${result.userPassword}"/>
    <div>
	    <input type="button" value="追加"  /><input type="button" value="削除"  /><br><br>
	</div>
	<div>
		<c:forEach var="card" items="${result}">

		</c:forEach>
	</div>

	<div>

		電話番号<select><option >aaa</option><option >bbb</option><option >ccc</option></select><br>
	    郵便番号<select><option >aaa</option><option >bbb</option><option >ccc</option></select><br>
	    住所<br><select><option >aaa</option><option >bbb</option><option >ccc</option></select><br>
	    <input type="button" value="追加"  /><input type="button" value="削除"  /><br><br><br><br>
	     <form name="form" method="post" action="/Egroup_PROJECT/">

            <input type="submit" value="完成">

        </form>

	</div>

</body>
</html>