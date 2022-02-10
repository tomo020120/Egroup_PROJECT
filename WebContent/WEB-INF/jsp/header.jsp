<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet" href="CSS/headerStyle.css">

<link rel="icon" href="images/favicon.ico" type="image/vnd.microsoft.icon">
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
          <li><a href="products?categoryId=1">商品一覧(エレキギター)</a></li>
           <li><a href="products?categoryId=2">商品一覧(アコースティックギター)</a></li>
            <li><a href="products?categoryId=3">商品一覧（ベース）</a></li>
          <li><a href="artist">アーティスト</a></li>
         </ul>
      </div>
     </div>
      <div class=logo>
      	<a href="topPage"><img src="images/logo.png" alt="ロゴ" width="180" ></a>
      </div>


	 <div class=position>
	 <a href="purchaseHistory" class=""><img src="images/history-removebg-preview.png" alt="カート" width="40" class="white"></a>
      <a href="cart"><img src="images/cart-removebg-preview.png" alt="カート" width="40" ></a>





	  <div class=name>
	 	 <p>${sessionInfo.userName}</p>
	 </div>

	 <c:choose>
    <c:when test="${!empty sessionInfo.userName}">
        <a href="userInfo"><img src="images/people-removebg-preview.png" alt="ログイン" width="40" ></a>
    </c:when>
    <c:otherwise>
         <a href="login"><img src="images/people-removebg-preview.png" alt="ログイン" width="40" ></a>
    </c:otherwise>
</c:choose>
</div>

</header>
<div class=kara>　　
　　
　　
　　</div>
</body>
</html>
