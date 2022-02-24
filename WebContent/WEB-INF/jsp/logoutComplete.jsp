<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト完了</title>
<link rel="stylesheet" href="CSS/error.css">

<script type="text/javascript">
	function delay(){
		setTimeout("location.href='<%=request.getContextPath()%>'",3000);
	}
</script>
</head>
<body onload="delay();">
<div class="error">
<h1>ログアウトが完了しました。</h1>
<p>自動的に3秒後トップページへ戻ります。</p>
</div>
</body>
</html>