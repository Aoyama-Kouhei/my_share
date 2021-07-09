<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/header_footer_style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

<!--ここから書き換え-->
<div class="processing">
	<!--ページのタイトル-->
	<div class="pageTitle">
		<h3>商品編集確認画面</h3>
	</div>

	<div class="tableViewer">
		<form action="<%=request.getContextPath()%>/ProductUpdateComplete" method="post">
			<!--上段テーブル-->
			<div class="tableSetter">

				<!--クラス「itemLine」が一行分のオブジェクトの塊-->
				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">商品ID:</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${product.productId}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">商品名:</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${product.productName}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">価格(円)：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${product.price}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">カテゴリ名：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${product.category.categoryName}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">説明文：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${product.explainText}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">画像URL：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${product.imageUrl}</div>
					</div>
				</div>

			</div>

			<!--入力内容の送信ボタン-->
			<div class="inputButton">
				<input type="hidden" name="productId" value="${product.productId}" />
				<input type="hidden" name="productName" value="${product.productName}" />
				<input type="hidden" name="price" value="${product.price}" />
				<input type="hidden" name="categoryId" value="${product.category.categoryId}" />
				<input type="hidden" name="categoryName" value="${product.category.categoryName}"/>
				<input type="hidden" name="explainText" value="${product.explainText}" />
				<input type="hidden" name="imageUrl" value="${product.imageUrl}" />
				<input type="submit" value="確定" />
			</div>
		</form>

	</div>

	<!--その他ページ遷移ボタンまとめ-->
	<div class="buttonSetter">

		<form action="<%=request.getContextPath()%>/ProductUpdateInput" method="post">
			<div class="inputButton">
				<input type="hidden" name="productId" value="${product.productId}" />
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