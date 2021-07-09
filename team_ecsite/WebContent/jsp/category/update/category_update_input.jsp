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
	href="<%=request.getContextPath()%>/css/common.css" />
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

	<!--ページのタイトル-->
	<div class="pageTitle">
		<h3>カテゴリ編集入力画面</h3>
		<div class="errorMsg">
			<c:forEach var="st" items="${messageList}">
				<c:choose>
					<c:when test="${!empty st}">
					${st}<br>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</div>
	</div>

	<div class="tableViewer">
		<form action="<%=request.getContextPath()%>/CategoryUpdateCheck"
			method="post">
			<!--上段テーブル-->
			<div class="tableSetter">

				<!--クラス「itemLine」が一行分のオブジェクトの塊-->

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">カテゴリ名：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">
							<input class="inputChar" type="text" name="categoryName" />
						</div>
					</div>
				</div>
			</div>

			<!--入力内容の送信ボタン-->
			<div class="inputButton">
				<input type="hidden" name="categoryId"
					value="${category.categoryId}" /> <input type="submit" value="確認" />
			</div>
		</form>

	</div>

	<!--その他ページ遷移ボタンまとめ-->
	<div class="buttonSetter">

		<form action="<%=request.getContextPath()%>/CategoryList">
			<div class="inputButton">
				<input type="submit" value="戻る" />
			</div>
		</form>

	</div>

	<footer>
		<div class="footer_logo">ジャイアンツショップ</div>
		<div class="tail_nav">
			<p>利用規約 | プライバシー規約 | 2021年版</p>
		</div>
	</footer>
</body>
</html>