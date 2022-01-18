<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet" href="CSS/headerStyle.css">

</head>
<body>


<header>
  <div class="h-menu">
      <input id="h-menu_checkbox" class="h-menuCheckbox" type="checkbox">
      <label class="h-menu_icon" for="h-menu_checkbox"><span class="hamburger-icon"></span></label>
      <label id="h-menu_black" class="h-menuCheckbox" for="h-menu_checkbox"></label>
      <div id="h-menu_content">
        <ul>
          <li><a href="news">ニュース</a></li>
          <li><a href="products">商品一覧</a></li>
          <li><a href="artist">アーティスト</a></li>
         </ul>
      </div>
      <div class=logo>
      	<a href="topPage"><img src="images/logo.png" alt="ロゴ" width="180" ></a>
      </div>
      <a href="userInfoEdit?userId = "${sessionInfo.userId}>ユーザー情報編集</a>


	<a href="favorite">お気に入りリスト</a>
		<a href="logout">ログアウト</a>
	 <div class=position>
      <a href="cart"><img src="images/cart-removebg-preview.png" alt="カート" width="40" ></a>
	  <a href="login"><img src="images/people-removebg-preview.png" alt="ログイン" width="40" ></a>
	   </div>
	  <p>${sessionInfo.userName}</p>
  </div>
</header>
</body>
</html>

