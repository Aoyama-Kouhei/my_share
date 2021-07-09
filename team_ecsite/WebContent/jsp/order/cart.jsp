<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/jsp/login_check.jsp"%>
<!DOCTYPE HTML>
<html>

<head>

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


		<div class="main_content">
			<h3>買い物かご</h3>
			<div class="err_msg">${errorMsg}</div>
			<c:if test="${cartList != null}">
				<form action="<%=request.getContextPath()%>/Order" method="post">
					<div class="list-wrapper">
					<% int i = 0; %>

						<!-- 繰り返し -->
						<c:forEach var="object" items="${cartList}">

							<div class="ordered_product">
								<c:if test="${object.imageUrl == null }">
									<img src="<%=request.getContextPath()%>/img/no_img.jpg"
										class="ordered_pic">
								</c:if>
								<c:if test="${object.imageUrl != null }">
									<img src="<%=request.getContextPath()%>/img/${object.imageUrl}"
										class="ordered_pic">
								</c:if>
								<br>商品ID:${object.productId}
								カテゴリ：${object.category.categoryName}<br>商品名：${object.productName}
								単価：${object.price}<br> 個数：<input type="text"
									name="quantity_<%=i%>" style="width: 30px;"> <br>商品詳細：<a
									href="#">${object.explainText}......</a> <input type="hidden"
									name="productId_<%=i%>" value="${object.productId}">
									<input type="hidden" name="productName_<%=i%>" value="${object.productName}">
									<input type="hidden" name="price_<%=i%>" value="${object.price}">

							</div>
							<% i += 1; %>
						</c:forEach>
						<!-- //繰り返し -->
					</div>
					<input type="hidden" name="orderListSize" value="<%=i%>">

					<div class="basket_summary">
					<br>
						<input type="submit" value="レジへ"> <br> <br>

					</div>
				</form>
			</c:if>
			<c:if test="${cartList == null}">
				<div class="basket_summary">
					買い物かごは空です。<br> <br> <br>


				</div>

			</c:if>
			<form action="<%=request.getContextPath()%>/BackToIndex" method="get">
				<div class="basket_summary">

					<div class="input">
						<input type="submit" value="商品一覧へ戻る" />
					</div>
				</div>
			</form>
		</div>






	</div>

	<%@ include file="/jsp/sideRankingSpace.jsp"%>
	</article>

	<footer>
	<div class="footer_logo">システムジャイアンツ</div>
	<div class="tail_nav">
		<p>利用規約 | プライバシー規約 | 2021年版</p>
	</div>
	</footer>
</body>

</html>