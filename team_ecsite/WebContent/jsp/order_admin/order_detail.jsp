<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/header_footer_style.css">
<title>ショッピングシステム</title>
</head>
<body>
	<!--ヘッダー-->
	<header>
    <%@ include file="/jsp/header.jsp"%>
  </header>


<!--ここから書き換え-->
<div class="processing">
	<!--ページのタイトル-->
	<div class="pageTitle">
		<h3>注文詳細画面</h3>
	</div>

	<div class="tableViewer">
			<!--上段テーブル-->
			<div class="tableSetter">

				<!--クラス「itemLine」が一行分のオブジェクトの塊-->
				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">注文ID：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${order.getOrderId()}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">会員ID：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${order.getUserId()}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">会員名：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${orderUserName}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">注文日時：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${order.getOrderDate()}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">支払い方法：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">

						<c:choose>
							<c:when test="${order.getPayment() == 1}">
							クレジットカード
							</c:when>
							<c:when test="${order.getPayment() == 2}">
							銀行振り込み
							</c:when>
							<c:when test="${order.getPayment() == 3}">
							着払い
							</c:when>
							<c:otherwise>
							未指定
							</c:otherwise>
						</c:choose>
						</div>
					</div>
				</div>

			</div>

	</div>

	<!--その他ページ遷移ボタンまとめ-->
	<center>
	<table class="history_table" border="1" style="border-collapse: collapse">
				<tr>
					<th class="productId">商品ID</th>
					<th class="productName">商品名</th>
					<th class="quantity">注文個数</th>
					<th class="price">金額</th>
					<th class="button" colspan="2">操作</th>
				</tr>
				<c:forEach  var="hist" items="${historyList}" >
				<tr>
					<td>${hist.productId}</td>
					<td>${hist.productName}</td>
					<td>${hist.quantity}</td>
					<td>${hist.price}</td>
					<td class="button">
						<form action="<%=request.getContextPath()%>/HistoryUpdateInput" method="post">
							<input type="hidden" name="userId" value="${order.getOrderId()}" />
							<input type="hidden" name="historyId" value="${hist.historyId}" />
							<input type="hidden" name="productId" value="${hist.productId}" />
							<input type="hidden" name="productName" value="${hist.productName}" />
							<input type="hidden" name="quantity" value="${hist.quantity}" />
							<input type="hidden" name="price" value="${hist.price}" />
							<input type="submit" value="編集" class="delete" />
						</form>
					</td>
					<td class="button">
						<form action="<%=request.getContextPath()%>/HistoryDeleteCheck" method="post">
							<input type="hidden" name="userId" value="${order.getOrderId()}" />
							<input type="hidden" name="historyId" value="${hist.historyId}" />
							<input type="hidden" name="productId" value="${hist.productId}" />
							<input type="hidden" name="productName" value="${hist.productName}" />
							<input type="hidden" name="quantity" value="${hist.quantity}" />
							<input type="hidden" name="price" value="${hist.price}" />
							<input type="submit" value="削除" class="delete" />
						</form>
					</td>
				</tr>
				</c:forEach>

				</table>
				<br>


				<form action="<%=request.getContextPath()%>/OrderList">
					<div class="form" >
						<div class="label"></div>
						<div class="input">
							<input type="submit" value="戻る" />
						</div>
					</div>
				</form>
				<br>
			</div>
		</center>


	<!--フッター-->
	<footer>
    <div class="footer_logo">システムジャイアンツ</div>
    <div class="tail_nav">
      <p>利用規約 | プライバシー規約 | 2021年版</p>
    </div>
  </footer>
</body>
</html>