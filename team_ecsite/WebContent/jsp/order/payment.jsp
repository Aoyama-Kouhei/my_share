<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/jsp/login_check.jsp"%>

<!DOCTYPE HTML>
<html>

<head>
<meta charset="utf-8">
<title>システムジャイアンツ</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header_footer_style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/side_bar_style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/order_layout.css">
</head>

<body>
	<%@ include file="/jsp/header.jsp"%>

	<article>
	<%@ include file="/jsp/sideSearchSpace.jsp"%>

	<div class="mainContentSpace">
		<div class="main_content">

			<c:if test="${payment == 1}">

				<h3>支払い情報詳細画面</h3>
				<h4>クレジットカード情報入力</h4>

				<c:forEach var="message" items="${messageList}">
					<div class="errorMsg">${message}<br></div>
				</c:forEach>

				<div class="input_range" align="center">
					<form action="<%=request.getContextPath()%>/OrderCheck" method="POST">
						<input type="radio" name="credit_type" value="1" checked="checked">VISA
						<input type="radio" name="credit_type" value="2">MASTER <input
							type="radio" name="credit_type" value="3">JCB <input
							type="radio" name="credit_type" value="4">AMEX<br> <br>
						クレジットカード名義人&nbsp;：&nbsp; <input type="text" name="credit_name"
							style="width: 140px;"><br> <br>
						クレジットカード番号&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp; <input type="text"
							name="credit_no" maxlength="16" style="width: 140px;"><br>
						<br> 有効期限 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月：&nbsp;<select
							name="month" id="a">
							<option value="1" selected="selected">01</option>
							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
							<option value="6">06</option>
							<option value="7">07</option>
							<option value="8">08</option>
							<option value="9">09</option>
							<option value="10">10</option>
							<option value="10">11</option>
							<option value="10">12</option>
						</select> &nbsp;&nbsp;&nbsp; 年：<select name="year" id="a">
							<option value="1" selected="selected">21</option>
							<option value="2">22</option>
							<option value="3">23</option>
							<option value="4">24</option>
							<option value="5">25</option>
							<option value="6">26</option>
							<option value="7">27</option>
							<option value="8">28</option>
							<option value="9">29</option>
							<option value="10">30</option>
						</select><br> <br> セキュリティコード&nbsp;&nbsp;&nbsp;： <input
							type="text" name="cvv" style="width: 50px;" maxlength="4">
						<br> <br> <br> <input type="hidden" name="payment"
							value="${payment}"> <input type="submit" value="確認">
					</form><br>
					<form action="<%=request.getContextPath()%>/Order" method="POST">
						<input type="submit" value="戻る">
					</form>
				</div>

			</c:if>

			<c:if test="${payment == 2}">
				<h4>口座情報入力</h4>

				<c:forEach var="message" items="${messageList}">
					<div class="errorMsg">${message}<br></div>
				</c:forEach>

				<div class="input_range" align="center">
					<form action="<%=request.getContextPath()%>/OrderCheck" method="POST">
						銀行名&nbsp;&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="text" name="bank_name"><br>
						<br> 支店コード&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp; <input type="text"
							name="bank_no"> <br>
						<br> 口座番号&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp; <input type="text"
							name="account_no"> <br>
						<br>
						<br> <input type="hidden" name="payment" value="${payment}">
						<input type="submit" value="確認">
					</form>
					<br>
					<form action="<%=request.getContextPath()%>/Order" method="POST">
						<input type="submit" value="戻る">
					</form>
				</div>

			</c:if>

		</div>
	</div>

	<%@ include file="/jsp/sideRankingSpace.jsp"%>
	</article>

	<footer>
	<div class="footer_logo">システムジャイアンツ</div>
	<div class="tail_nav">
		<p>利用規約 | プライバシー規約 | 2021年版</p>
	</div>
	</footer>
</body>

</html>