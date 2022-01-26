<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>最後</title>
<script>
function delay(){
	setTimeout("location.href='<%=request.getContextPath()%>'",5000);
}
</script>
</head>
<body onload="delay();">
	<h1>購入完了しました。</h1>
	<p>5秒後に自動的にトップページへ戻ります</p>
</body>
</html>