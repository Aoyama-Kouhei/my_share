<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
if (request.getAttribute("list") == null) {
	request.getRequestDispatcher("/BackToIndex").forward(request, response);
}
%>

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css" />

</head>

<body>
	<%@ include file="/jsp/header.jsp"%>


	<article>
		<%@ include file="/jsp/sideSearchSpace.jsp"%>

		<div class="mainContentSpace">

			<div class="main_content">

				<div class="list_style">
				<h3>商品一覧</h3>
				<form action="<%=request.getContextPath()%>/OutPutProductListByCsv">
					<input type="submit" value="商品情報の出力">
				</form>
					<div class="list-wrapper">

						<!-- //繰り返し -->
						<c:forEach var="object" items="${list}">
							<div class="ordered_product">

								<form action="<%=request.getContextPath()%>/ProductDetail"
									method="POST" name="form_product">
									<a href="javascript:;" onclick="parentNode.submit();"> <c:if
											test="${object.imageUrl == null }">
											<img src="<%=request.getContextPath()%>/img/no_img.jpg"
												class="ordered_pic">
											<br>
										</c:if> <c:if test="${object.imageUrl != null }">
											<img
												src="<%=request.getContextPath()%>/img/${object.imageUrl}"
												class="ordered_pic">
											<br>
										</c:if> 商品ID:${object.productId} カテゴリ：${object.category.categoryName}<br>商品名：${object.productName}
										単価：${object.price}<br> 詳細：${object.explainText}
									</a> <input type="hidden" name="productId"
										value="${object.productId}">
								</form>

							</div>
						</c:forEach>

						<!-- 繰り返し -->
					</div>
					<br> <br>

					<c:choose>
						<c:when test="${displayMethod == 2}">
							<table style="border: none;" align="center">
								<tr style="border: none;">
									<c:forEach var="i" begin="1" end="${pageNum}" step="1">
										<td style="border: none;">
											<form action="<%=request.getContextPath()%>/NewArrival">
												<input class="paging-button" type="hidden" name="paging" value="${i}" /> <input type="submit" value="${i}">
											</form>
										</td>
									</c:forEach>
								</tr>
							</table>
						</c:when>
						<c:when test="${displayMethod == 3}">
							<table style="border: none;" align="center">
								<tr style="border: none;">
									<c:forEach var="i" begin="1" end="${pageNum}" step="1">
										<td style="border: none;">
											<form action="<%=request.getContextPath()%>/Ranking">
												<input class="paging-button" type="hidden" name="paging"
													value="${i}" /> <input type="submit" value="${i}">
											</form>
										</td>
									</c:forEach>
								</tr>
							</table>
						</c:when>
						<c:when test="${displayMethod == 4}">
							<table style="border: none;" align="center">
								<tr style="border: none;">
									<c:forEach var="i" begin="1" end="${pageNum}" step="1">
										<td style="border: none;">
											<form
												action="<%=request.getContextPath()%>/SearchProductByCategory"
												method="post">
												<input class="paging-button" type="hidden" name="paging"
													value="${i}" /> <input class="paging-button" type="hidden"
													name="categoryId" value="${categoryId}" /> <input
													type="submit" value="${i}">
											</form>
										</td>
									</c:forEach>
								</tr>
							</table>
						</c:when>
						<c:when test="${displayMethod == 5}">
							<table style="border: none;" align="center">
								<tr style="border: none;">
									<c:forEach var="i" begin="1" end="${pageNum}" step="1">
										<td style="border: none;">
											<form
												action="<%=request.getContextPath()%>/SearchProductByCredit"
												method="post">
												<input class="paging-button" type="hidden" name="paging"
													value="${i}" /> <input class="paging-button" type="hidden"
													name="priceSeachLower" value="${priceSeachLower}" /> <input
													class="paging-button" type="hidden" name="priceSeachUpper"
													value="${priceSeachUpper}" /> <input type="submit"
													value="${i}">
											</form>
										</td>
									</c:forEach>
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<table style="border: none;" align="center">
								<tr style="border: none;">
									<c:forEach var="i" begin="1" end="${pageNum}" step="1">
										<td style="border: none;">
											<form action="<%=request.getContextPath()%>/BackToIndex">
												<input class="paging-button" type="hidden" name="paging"
													value="${i}" /> <input type="submit" value="${i}">
											</form>
										</td>
									</c:forEach>
								</tr>
							</table>
						</c:otherwise>
					</c:choose>

				</div>
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