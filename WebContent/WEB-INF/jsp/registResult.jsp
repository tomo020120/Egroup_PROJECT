<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録結果</title>
<script>
	function delay(){
		setTimeout("location.href='<%=request.getContextPath()%>'",10000);
	}
</script>
</head>
<body>
	<h1>登録が完了しました。</h1>
	<p>トップページに移動します。</p>
</body>
</html>