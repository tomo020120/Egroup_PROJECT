<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録エラー</title>
<script>
function delay(){
	setTimeout("location.href='<%=request.getContextPath()%>'",10000);
}
</script>
</head>
<body>
	<h1>予期せぬ事態によりしんきとうろくができませんでした</h1>
	<p>10秒後にトップページに戻ります。</p>
</body>
</html>