<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
    <div class="upperNavBar">
      <div class="header_logo"><a href="<%=request.getContextPath()%>/BackToIndex">システムジャイアンツ</a></div>
      <!--↓ここから、管理者の場合表示-->

      <c:if test="${loginUserAuthority}">
      	<div class="adminMenu">
        	<a id="navMemberManagement" href="<%=request.getContextPath()%>/UserList">会員管理</a>
        	<a id="navProductManagement" href="<%=request.getContextPath()%>/ProductList">商品管理</a>
        	<a id="navCategoryManagement" href="<%=request.getContextPath()%>/CategoryList">カテゴリ管理</a>
        	<a id="navOrderManagement" href="<%=request.getContextPath()%>/OrderList">注文管理</a>
      	</div>
      </c:if>


      <!--↑ここまで-->
    </div>
    <div class="generalMenu">
      <div class="right_header">
        <a id="sortRank" href="<%=request.getContextPath()%>/Ranking"><img src="<%=request.getContextPath()%>/img/headerPic/crown.png"> ランキング</a>
        <a id="sortNew" href="<%=request.getContextPath()%>/NewArrival"><img src="<%=request.getContextPath()%>/img/headerPic/new.png"> 新着</a>
      </div>
      <div class="left_header">
        <!--↓ここから、ログイン中かそうでないかで場合分けして表示する-->

	    <c:choose>
        	<c:when test="${loginUserId == null}">

          		<a id="navLogin" href="<%=request.getContextPath()%>/jsp/login.jsp">ログイン</a>

        		<a id="navAddUser" href="<%=request.getContextPath()%>/UserRegistInput">会員登録</a>
        	</c:when>
        	<c:otherwise>
        		<a id="navUser" href="<%=request.getContextPath()%>/UserUpdateInput">${loginUserName}</a>
        		<a id="navLogout" href="<%=request.getContextPath()%>/Logout">ログアウト</a>
        		<a id="navShopppingCart" href="<%=request.getContextPath()%>/Cart"><img src="<%=request.getContextPath()%>/img/headerPic/cart.png"> カート</a>
        		<a id="navFavorite" href="<%=request.getContextPath()%>/Favorite"><img src="<%=request.getContextPath()%>/img/headerPic/favorite.png"> お気に入り</a>
        	</c:otherwise>
        </c:choose>
        <!--↑ここまで-->
      </div>
    </div>
  </header>