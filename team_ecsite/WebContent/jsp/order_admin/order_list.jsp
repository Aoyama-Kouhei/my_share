<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link href="<%=request.getContextPath()%>/css/header_footer_style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/layout.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet" />
<title>ショッピングシステム</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>
	<h3 class="page_title">注文一覧画面</h3>
	<div class="regist_link">
		<a href="<%=request.getContextPath()%>/OrderRegistInput">新規注文登録</a>
	</div>
	<table class="user_list_table">
		<tr>
			<th class="orderId">注文ID</th>
			<th class="userId">会員ID</th>
			<th class="orderDate">注文日時</th>
			<th class="payment">支払方法</th>
			<th class="button" colspan="3">操作</th>
		</tr>
		<c:forEach var="o" items="${orderList}">
			<tr>
				<td>${o.orderId}</td>
				<td>${o.userId}</td>
				<td>${o.orderDate}</td>
				<td>${o.payment}</td>

				<td class="button">
					<form action="<%=request.getContextPath()%>/OrderDetail" method="post">
						<input type="hidden" name="orderId" value="${o.orderId}"/>
						<input type="hidden" name="userId" value="${o.userId}"/>
						<input type="submit" value="詳細"/>
					</form>
				</td>
				<td class="button">
					<form action="<%=request.getContextPath()%>/OrderUpdateInput" method="post">
						<input type="hidden" name="orderId" value="${o.orderId}"/>
						<input type="hidden" name="userId" value="${o.userId}"/>
						<input type="submit" value="編集"/>
					</form>
				</td>
				<td class="button">
					<form action="<%=request.getContextPath()%>/OrderDeleteCheck" method="post">
						<input type="hidden" name="orderId" value="${o.orderId}"/>
						<input type="hidden" name="userId" value="${o.userId}"/>
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
						<form action="<%=request.getContextPath()%>/OrderList">
							<input class="paging-button" type="hidden" name="paging" value="${i}" />
							<input type="submit" value="${i}">
						</form>
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
	<footer>
		<div class="footer_logo">ジャイアンツショップ</div>
		<div class="tail_nav">
			<p>利用規約 | プライバシー規約 | 2021年版</p>
		</div>
	</footer>
</body>
</html>