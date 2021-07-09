<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header_footer_style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout.css" />
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>


	<article class="main">
		<h3 class="page_title">カテゴリ一覧画面</h3>
		<div class="regist_link">
			<a
				href="<%=request.getContextPath()%>/jsp/category/regist/category_regist_input.jsp">新規カテゴリ登録</a>
		</div>
		<table class="user_list_table">
			<tr>
				<th class="categoryId">カテゴリID</th>
				<th class="categoryName">カテゴリ名</th>
				<th class="button" colspan="3">操作</th>
			</tr>
			<c:forEach var="category" items="${categoryList}">
				<tr>
					<td>${category.categoryId}</td>
					<td>${category.categoryName}</td>
					<td class="button">
						<form action="<%=request.getContextPath()%>/CategoryUpdateInput"
							method="post">
							<input type="hidden" name="categoryId"
								value="${category.categoryId}" /> <input type="hidden"
								name="categoryName" value="${category.categoryName}" /> <input
								type="submit" value="編集" />
						</form>
					</td>
					<td class="button">
						<form action="<%=request.getContextPath()%>/CategoryDeleteCheck"
							method="post">
							<input type="hidden" name="categoryId"
								value="${category.categoryId}" /> <input type="hidden"
								name="categoryName" value="${category.categoryName}" /> <input
								type="submit" value="削除" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div class="paging">
			<table style="border: none;">
				<tr style="border: none;">
					<c:forEach var="i" begin="1" end="${pageNum}" step="1">
						<td style="border: none;">
							<form action="<%=request.getContextPath()%>/CategoryList">
								<input class="paging-button" type="hidden" name="paging"
									value="${i}" /> <input type="submit" value="${i}">
							</form>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</article>


	<footer>
		<div class="footer_logo">ジャイアンツショップ</div>
		<div class="tail_nav">
			<p>利用規約 | プライバシー規約 | 2021年版</p>
		</div>
	</footer>
</body>
</html>