<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date, java.text.DateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header_footer_style.css" />
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

	<!--ここから書き換え-->
	<div class="processing">
		<!--ページのタイトル-->
		<div class="pageTitle">
			<h3>注文登録確認画面</h3>
		</div>

		<div class="tableViewer">

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
						<div class="oneTextLine">個数：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${history.quantity}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">支払方法：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">
							<c:choose>
								<c:when test="${order.payment == 1}"> クレジットカード</c:when>
								<c:when test="${order.payment == 2}"> 銀行振込</c:when>
								<c:otherwise>着払い</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>

			</div>

			<!--入力内容の送信ボタン-->
			<form action="<%=request.getContextPath()%>/OrderRegistComplete"
				method="post">
				<div class="inputButton">
					<input type="hidden" name="loginUserId" value="${loginUserId}" /> <input
						type="hidden" name="productId" value="${history.productId}" /> <input
						type="hidden" name="quantity" value="${history.quantity}" /> <input
						type="hidden" name="payment" value="${order.payment}" />
						<input type="submit"
						value="登録" />
				</div>
			</form>

		</div>

		<!--その他ページ遷移ボタンまとめ-->
		<div class="buttonSetter">

			<form action="<%=request.getContextPath()%>/OrderRegistInput">
				<div class="inputButton">
					<input type="hidden" name="loginUserId" value="${loginUserId}" />
					 <input type="submit"
						value="戻る" />
				</div>
			</form>

		</div>
	</div>

</body>
</html>