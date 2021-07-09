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
	<header>
		<%@ include file="/jsp/header.jsp"%>
	</header>


	<div class="pageTitle">
		<h3>注文履歴削除確認画面</h3>
	</div>

	<div class="tableViewer">
		<form action="<%=request.getContextPath()%>/HistoryDeleteComplete"
			method="post">
			<!--上段テーブル-->
			<div class="tableSetter">

				<!--クラス「itemLine」が一行分のオブジェクトの塊-->
				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">商品ID：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${history.productId}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">商品名：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${history.productName}</div>
					</div>
				</div>
				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">注文個数：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${history.quantity}</div>
					</div>
				</div>
				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">個数：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${history.price}</div>
					</div>
				</div>
			</div>

			<!--入力内容の送信ボタン-->
			<div class="inputButton">
				<input type="hidden" name="historyId"value="${history.historyId}" />
				<input type="submit" value="確定" />
			</div>
		</form>

	</div>

	<!--その他ページ遷移ボタンまとめ-->
	<div class="buttonSetter">

		<form action="<%=request.getContextPath()%>/OrderList">
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