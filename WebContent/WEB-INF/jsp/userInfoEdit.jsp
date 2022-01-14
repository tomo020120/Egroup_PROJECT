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
<<<<<<< HEAD
    ユーザー名<input type='text' name='name'/><br/>
    パスワード<input type='text' id="emailAddress"/><br />
   メールアドレス<input type='text' name='pass'/><br/>
    カード番号<input type='text' name='cartCode'/><br>
    住所<input type='text' name='address'/><br>
    郵便番号<input type='text' name='postalCode'/><br>
    電話番号<input type='text' name='tel'/><br>
    <input type="button" value="登録"  />
=======
    ユーザー名<input type='text' name='name'/><input type="button" value="編集"  /><br/>
    メールアドレス<input type='text' id="emailAddress"/><input type="button" value="編集"  /><br />
    パスワード<input type='text' name='pass'/><input type="button" value="編集"  /><br/></br>
    <div>
>>>>>>> branch 'master' of git@github.com:tomo020120/Egroup_PROJECT.git

	    カード番号<select><option >aaa</option><option >bbb</option><option >ccc</option></select>
	    <input type="button" value="追加"  /><input type="button" value="削除"  /><br><br>

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