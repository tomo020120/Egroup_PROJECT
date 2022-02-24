<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" href="CSS/productsStyle.css">
<link rel="stylesheet" href="CSS/adminProductsPageStyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/adminProductsPageScript.js"></script>
</head>
<body>
<h1>商品編集画面</h1>
<p>${message}</p>
<div id="adminActionTool">
	<button id="addProductBtn" class="actionBtn">商品を追加する</button>
	<button id="selectProductBtn" class="actionBtn">商品を選択する</button>
	<div id="allSelectArea"><input type="checkbox" id="allSelect" class="actionBtn">全選択/解除</div>
	<button id="deleteProductsBtn" class="actionBtn">選択商品の消去</button>
	<button id="canselSelectBtn" class="actionBtn">キャンセル</button>
</div>



<form action="searchByAdmin" method="GET" name="form1">

<fieldset class="info">
	  <legend>色</legend>
	  <input type="hidden" name="color" value="0">
	  <div class=color>
	    <input type="checkbox" id="1" name="color" value="1" onChange="submitColor()">
	    <label for="red">赤色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="2" name="color" value="2" onChange="submitColor()">
	    <label for="brown">茶色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="3" name="color" value="3" onChange="submitColor()">
	    <label for="black">黒色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="4" name="color" value="4" onChange="submitColor()">
	    <label for="blue">青色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="5" name="color" value="5" onChange="submitColor()">
	    <label for="green">緑色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="6" name="color" value="6" onChange="submitColor()">
	    <label for="orange">橙色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="7" name="color" value="7" onChange="submitColor()">
	    <label for="pink">桃色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="8" name="color" value="8" onChange="submitColor()">
	    <label for="purple">紫色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="9" name="color" value="9" onChange="submitColor()">
	    <label for="silver">銀色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="10" name="color" value="10" onChange="submitColor()">
	    <label for="white">白色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="11" name="color" value="11" onChange="submitColor()">
	    <label for="yellow">黄色</label>
	  </div>
	  <div class=color>
	    <input type="checkbox" id="12" name="color" value="12" onChange="submitColor()">
	    <label for="others">他</label>
	  </div>
	  <div class=pos3>
<input type="search" name="productName"placeholder="商品名" value="${sessionScope.holdSearchWord}" class="info1">
<select name="categoryId" id="CATEGORY" class="info1">
	<option value="1">エレキギター</option>
	<option value="2">アコースティックギター</option>
	<option value="3">ベース</option>
</select>
<select name="sort" id = "SORT" class="info1">
	<option value="0">商品ID順</option>
	<option value="1">価格の安い順</option>
	<option value="2">価格の高い順</option>
</select>
<input type="submit" value="検索"/>
<input type="hidden" name="pageNo" value="1">
</div>
	</fieldset>
</form>

<h1>商品一覧</h1>
<div class="pos1">
<table border="1">
 <tr><th id="actionText">編集</th><th>商品名</th><th>画像</th><th>価格</th></tr>
 <c:forEach var="product" items="${result}">
  <tr>

 	<td class="adminAction"><button class="productEditBtn" onclick="location.href='editProductPage?itemId=${product.itemId}'">編集する</button><input type="checkbox" id="${product.name}" class="targetItemCheck actionBtn" name="deleteTargetItem" value="${product.itemId}"></td>
  	<td><a href="productsDetails?itemId=${product.itemId}" class="itemLink">${product.name}</a></td>
  	<td><a href="productsDetails?itemId=${product.itemId}" class="itemLink"><img src="${product.pictPath}" width="500px"></a></td>

  	<td>${product.price}円</td>
  </tr>
 </c:forEach>
 </table>
</div>


<!-- 選択された商品確認画面 -->
 <div id="selectItemList">
 	<p style="color:red">消去した場合元に戻すことはできません<br>選択された商品(ID:商品名)</p>
 	<div id="itemList"></div>
 	<div id="choiseBtn">
	 	<button id="canselBtn">選択を続ける</button>
	 	<button id="deleteBtn">消去</button>
 	</div>
 </div>


<div class="pos">
<button id="previousPageBtn">前のページ</button>
${sessionScope.holdPageNo}/${sessionScope.maxPageNo}
<button id="nextPageBtn">次のページ</button>
</div>
 <input type="hidden" name="maxPage" value="${sessionScope.maxPageNo}" class ="info2">

<div id = "productHistoryFooter">
</div>

<script>
	//requestパラメータの取得
	var queryString = window.location.search;
	var queryObject = new Object();
	//?除き及び複数パラメーター分け
	if(queryString){
	  queryString = queryString.substring(1);
	  var parameters = queryString.split('&');

	  for (var i = 0; i < parameters.length; i++) {
	    var element = parameters[i].split('=');

	    var paramName = decodeURIComponent(element[0]);
	    var paramValue = decodeURIComponent(element[1]);
		//queryObject.パラメータ名で値取得化
	    queryObject[paramName] = paramValue;
	  }
	}

	console.log("検索文字:"+queryObject.productName);
	console.log("ソート順:"+queryObject.sort);
	console.log("色:"+queryObject.color);

	//正規表現導入　asz→ASZ　ｓｚ→sz

	//全てのcheckboxの状態を取得
	function submitColor(){
		var colors = new Array();
		//配列0－12番にId1-13のcheckedを入力
		for(let i=1;i<13;i++){

			colors.push(document.getElementById(i).checked);
			if(document.getElementById(i).checked==true){
				flag=true;
			}

		}
		console.log(colors);
		sessionStorage.setItem("key",colors);
	}

	window.addEventListener('DOMContentLoaded', function(){
		//ソートプルダウン
		document.form1.sort.selectedIndex = parseInt(queryObject.sort);
		document.form1.categoryId.selectedIndex = parseInt(queryObject.categoryId)-1;


		var ckstate = sessionStorage.getItem("key").split(",");
		console.log("読み込み後のkeyget結果:"+ckstate);
		var j=0;
		for(let i=1;i<13;i++){
			console.log("Id:"+i+"に配列"+j+"番目の"+ckstate[j]);
			document.getElementById(i).checked = (ckstate[j]==="true")?true:false;
			j++;
		}

	});


</script>

</body>
</html>