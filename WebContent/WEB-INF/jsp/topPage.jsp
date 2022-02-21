<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>

<link rel="stylesheet" href="CSS/topPageStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/productsScript.js"></script>
</head>
<body>



	<div class="container">
	<img class="image" src="images/top_premium2022.jpg" />
	<img class="image" src="images/top_BTBjp.jpg" />
	<img class="image" src="images/top_AZpremium2022.png" />
	</div>
<div id = "productHistoryFooter">
</div>

<input id="token" type="hidden" name="${sessionScope.token.userName }" value="${sessionScope.token}">

<script>
	$(function(){
		var token = $("#token").val();
		var userName = $("#token").attr('name');

		if(token != "" && userName != "管理者"){
			$('#productHistoryFooter').load('productHistoryFooter', function(data, status) {
				  if(status === 'success') {
				    console.log('読み込みが正常に行われました');
				  }
			});
		}
	});
</script>

</body>
</html>