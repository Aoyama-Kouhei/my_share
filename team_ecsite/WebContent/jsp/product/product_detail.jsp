<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML>
<html>

<head>
	<meta charset="utf-8">
	<title>システムジャイアンツ</title>
	<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header_footer_style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/side_bar_style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/order_layout.css">
</head>

<body>
	<%@ include file="/jsp/header.jsp"%>

	<article>
		<%@ include file="/jsp/sideSearchSpace.jsp"%>

		<div class="mainContentSpace">
			<h3>商品詳細画面</h3>

			<div class="detail_whole">

				<div class="detail_pic">
					<c:if test="${product.imageUrl == null }">
    					<img src="<%=request.getContextPath()%>/img/no_img.jpg"  width="300px"><br>
    					</c:if>
    					<c:if test="${product.imageUrl != null }">
    					<img src="<%=request.getContextPath()%>/img/${product.imageUrl}"  width="300px" ><br>
    					</c:if>
				</div>

				<div class="detail_sentence">

					商品名：<br>
					${product.productName}<br>

					価格(円)：<br>
					${product.price}<br>

					カテゴリ名：<br>
					${product.category.categoryName}<br>

					説明文：<br>
					${product.explainText}<br>


					<br><br>

				</div>

			</div>
			<div class="detail_btns">
				<form action="<%=request.getContextPath()%>/Cart" method="post">
					<div class="form">
					<c:if test="${loginUserId != null}">
						<div class="input">
							<input type="hidden" name="productId" value="${product.productId}" />
							<input type="submit" value="カートに入れる" />
						</div>
						</c:if>
					</div>
				</form>
	<br>
				<form action="<%=request.getContextPath()%>/BackToIndex" method="get">
					<div class="form">

						<div class="input">
							<input type="submit" value="戻る" />
						</div>
					</div>
				</form>
			</div>

		</div>

		<div class="sideRankingSpace">
			<div class="productRanking">
				<p>売れ筋ランキング</p>
				<div class="upperProducts" id="p001">
					<div class="productPic"><img src="../img/upperRank.jpg" width="120" height="120"></div>
					商品名<br>
					￥999<br>
				</div>
				<div class="upperProducts" id="p002">
					<div class="productPic"><img src="../img/upperRank.jpg" width="120" height="120"></div>
					商品名<br>
					￥999<br>
				</div>
				<div class="upperProducts" id="p003">
					<div class="productPic"><img src="../img/upperRank.jpg" width="120" height="120"></div>
					商品名<br>
					￥999<br>
				</div>
			</div>
		</div>
	</article>

	<footer>
		<div class="footer_logo">システムジャイアンツ</div>
		<div class="tail_nav">
			<p>利用規約 | プライバシー規約 | 2021年版</p>
		</div>
	</footer>
</body>

</html>