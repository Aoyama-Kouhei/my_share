<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/header_footer_style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" />
<meta charset="UTF-8">
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

	<!--ここから書き換え-->
	<div class="processing">
		<!--ページのタイトル-->
		<div class="pageTitle">
			<h3>注文削除確認画面</h3>
		</div>

		<div class="tableViewer">
			<form action="<%=request.getContextPath()%>/OrderDeleteComplete"
				method="post">
				<!--上段テーブル-->
				<div class="tableSetter">

					<!--クラス「itemLine」が一行分のオブジェクトの塊-->
					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">注文ID：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">${order.orderId}</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">会員ID：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">${order.userId}</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">注文日時：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">${order.orderDate}</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">支払方法：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">${order.payment}</div>
						</div>
					</div>

				</div>

				<!--入力内容の送信ボタン-->
				<div class="inputButton">
					<input type="hidden" name="orderId" value="${order.orderId}" />
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
	</div>

</body>
</html>