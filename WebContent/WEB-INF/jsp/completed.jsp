<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="CSS/error.css">
<title>最後</title>
<script>
function delay(){
	setTimeout("location.href='topPage'",5000);
}
</script>
</head>
<body onload="delay();">
<div class="error">
	<h1>購入完了しました。</h1>
	<p>5秒後に自動的にトップページへ戻ります</p>
</div>
</body>
</html>