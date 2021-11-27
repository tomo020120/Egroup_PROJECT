<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
<script>
function delay(){
	setTimeout("location.href='<%=request.getContextPath()%>'",10000);
}
</script>
</head>
<body onload="delay()">
	<h1>予期せぬ事態が起き実行できませんでした。</h1>
	<p>10秒後にトップページに戻ります。</p>
</body>
</html>