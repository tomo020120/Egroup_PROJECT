<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録結果</title>
<link rel="stylesheet" href="CSS/error.css">
<script>
	function delay(){
		setTimeout("location.href='loginCheck'",3000);
	}
</script>
</head>
<body onload="delay()">
<div class="error">
	<h1>登録が完了しました。</h1>
	<p>自動ログインします。数秒お待ちください。</p>
</div>
</body>
</html>