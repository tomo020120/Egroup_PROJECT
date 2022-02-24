<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メール送信エラー</title>
<link rel="stylesheet" href="CSS/error.css">
<script>
function delay(){
	setTimeout("location.href='<%=request.getContextPath()%>'",10000);
}
</script>
</head>
<body onload="delay();">
<div class="error">
	<h1>メール送信ができませんでした。</h1>
	<p>10秒後に自動的にトップページへ戻ります</p>
	<img src="images/error.png"width="400px">
</div>
</body>
</html>