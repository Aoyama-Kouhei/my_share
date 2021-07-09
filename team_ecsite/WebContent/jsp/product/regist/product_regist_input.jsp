<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/header_footer_style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/js/input_check_prototype.js"></script>
<meta charset="UTF-8">
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

<!--ここから書き換え-->
	<div class="processing">
		<!--ページのタイトル-->
		<div class="pageTitle">
			<h3>商品登録入力画面</h3>
		</div>

		<c:forEach var="message" items="${messageList}">
			<div class="errorMsg">${message}<br></div>
		</c:forEach>

		<div class="tableViewer">
			<form action="<%=request.getContextPath()%>/ProductRegistCheck" name="input" method="post">
				<!--上段テーブル-->
				<div class="tableSetter">

					<!--クラス「itemLine」が一行分のオブジェクトの塊-->
					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">商品名：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input type="text" name="productName" id="productName"/></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">価格（円)：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input type="text" name="price" id="price" oninput="checkOnInput('price')"/></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">カテゴリ名：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">
								<select name="categoryId">
									<c:forEach var="category" items="${categoryList}">
										<option value="${category.categoryId}">${category.categoryName}</option>
									</c:forEach>
									<c:forEach var="category" items="${categoryList}">
										<input type="hidden" name="${category.categoryId}" value="${category.categoryName}"/>
									</c:forEach>
							</select>
							</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">説明文：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><textarea name="explainText" id="explainText"></textarea></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">画像URL：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input type="text" name="imageUrl" id="imageUrl"/></div>
						</div>
					</div>

				</div>

				<!--入力内容の送信ボタン-->
				<div class="inputButton">
					<input type="submit" value="確認" onclick="return checkOnClick({ productName: '商品名', price: '価格', explainText: '説明文', imageUrl: '画像URL' },['price'],'登録')"/>
				</div>
			</form>

		</div>

		<!--その他ページ遷移ボタンまとめ-->
		<div class="buttonSetter">

			<form action="<%=request.getContextPath()%>/ProductList">
				<div class="inputButton">
					<input type="submit" value="戻る" />
				</div>
			</form>

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