<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_footer_style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" />
<title>ショッピングシステム</title>
</head>
<body>
	<!--ヘッダー-->
	<header>
    <div class="upperNavBar">
      <div class="header_logo">システムジャイアンツ</div>
      <!--↓ここから、管理者の場合表示-->
      <div class="adminMenu">
        <a id="navMemberManagement" href="#">会員管理</a>
        <a id="navProductManagement" href="#">商品管理</a>
        <a id="navCategoryManagement" href="#">カテゴリ管理</a>
        <a id="navOrderManagement" href="#">注文管理</a>
      </div>
      <!--↑ここまで-->
    </div>
    <div class="generalMenu">
      <div class="right_header">
        <a id="sortRank" href="#"><img src="../img/crown.png"> ランキング</a>
        <a id="sortNew" href="#"><img src="../img/new.png"> 新着</a>
      </div>
      <div class="left_header">
        <!--↓ここから、ログイン中かそうでないかで場合分けして表示する-->
        <a id="navLogin" href="#">ログイン</a>
        <a id="navUser" href="#">会員名</a>
        <a id="navAddUser" href="<%=request.getContextPath()%>/UserRegistInput">会員登録</a>
        <a id="navLogout" href="#">ログアウト</a>
        <a id="navShopppingCart" href="#"><img src="../img/cart.png"> カート</a>
        <a id="navFavorite" href="#"><img src="../img/favorite.png"> お気に入り</a>
        <!--↑ここまで-->
      </div>
    </div>
  </header>

	<div class="buttonSetter">

			<form action="<%=request.getContextPath()%>/UserUpdateInput" method="post">
				<div class="inputButton">
					<input type="hidden" name="userId" value="2" />
					<input type="submit" value="会員登録" />
				</div>
			</form>

		</div>

	<!--フッター-->
	<footer>
    <div class="footer_logo">システムジャイアンツ</div>
    <div class="tail_nav">
      <p>利用規約 | プライバシー規約 | 2021年版</p>
    </div>
  </footer>
</body>
</html>
