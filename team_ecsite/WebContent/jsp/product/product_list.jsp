<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/header_footer_style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

    <div class="mainContentSpace">
      <!--ここに各ページに関するものを表示-->
      <h3 class="page_title">商品一覧画面</h3>
			<div class="regist_link">
				<a href="<%=request.getContextPath()%>/ProductRegistInput">新規商品登録</a>
			</div>
			<table class="user_list_table">
				<tr>
					<th class="productId">商品ID</th>
					<th class="proName">商品名</th>
					<th class="price">価格(円)</th>
					<th class="categoryName">カテゴリ名</th>
					<th class="image">画像</th>
					<th class="button" colspan="3">操作</th>
				</tr>
				<c:forEach var="product" items="${productList}">
					<tr>
						<td>${product.productId}</td>
						<td>${product.productName}</td>
						<td>${product.price}</td>
						<td>${product.category.categoryName}</td>
						<td><img src="${product.imageUrl}"></td>
						<td class="button">
							<form action="<%=request.getContextPath()%>/ProductDetailAdmin" method="post">
								<input type="hidden" name="productId" value="${product.productId}" />
								<input type="hidden" name="productName" value="${product.productName}" />
								<input type="hidden" name="price" value="${product.price}" />
								<input type="hidden" name="categoryId" value="${product.category.categoryId}" />
								<input type="hidden" name="categoryName" value="${product.category.categoryName}" />
								<input type="hidden" name="explainText" value="${product.explainText}" />
								<input type="hidden" name="imageUrl" value="${product.imageUrl}" />
								<input type="submit" value="詳細" />
							</form>
						</td>
						<td class="button">
							<form action="<%=request.getContextPath()%>/ProductUpdateInput" method="post">
								<input type="hidden" name="productId" value="${product.productId}" />
								<input type="submit" value="編集" />
							</form>
						</td>
						<td class="button">
							<form action="<%=request.getContextPath()%>/ProductDeleteCheck" method="post">
								<input type="hidden" name="productId" value="${product.productId}" />
								<input type="hidden" name="productName" value="${product.productName}" />
								<input type="hidden" name="price" value="${product.price}" />
								<input type="hidden" name="categoryId" value="${product.category.categoryId}" />
								<input type="hidden" name="categoryName" value="${product.category.categoryName}" />
								<input type="hidden" name="explainText" value="${product.explainText}" />
								<input type="hidden" name="imageUrl" value="${product.imageUrl}" />
								<input type="submit" value="削除"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		<div class="paging">
			<table style="border:none;">
				<tr style="border:none;">
					<c:forEach var="i" begin="1" end="${pageNum}" step="1">
						<td style="border:none;">
							<form action="<%=request.getContextPath()%>/ProductList">
								<input class="paging-button" type="hidden" name="paging"
									value="${i}" /> <input type="submit" value="${i}">
							</form>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>

  <footer>
    <div class="footer_logo">ジャイアンツショップ</div>
    <div class="tail_nav">
      <p>利用規約 | プライバシー規約 | 2021年版</p>
    </div>
  </footer>
</body>
</html>