<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<h3>支払方法入力画面</h3>
		</div>


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

		<c:if test="${order.payment == 1}">

			<%--
		<c:if test="${!empty errorMessageList}">
			<c:forEach var="errorMessage" items="${errorMessageList}">
				<div style="color: red; text-align: center;">${errorMessage}</div>
			</c:forEach>
		</c:if>
 --%>

			<div class="tableViewer">


				<form action="<%=request.getContextPath()%>/OrderRegistPaymentInput"
					method="post">
					<!--上段テーブル-->
					<div class="tableSetter">


						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">カードの種類：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<label><input type="radio" name="pay" value="1" />VISA</label>
									<label><input type="radio" name="pay" value="2" />MASTER</label>
									<label><input type="radio" name="pay" value="3" />JCB</label>
									<label><input type="radio" name="pay" value="4" />AMEX</label>
								</div>
							</div>
						</div>


						<!--クラス「itemLine」が一行分のオブジェクトの塊-->
						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">カード番号：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<input class="inputChar" type="number" name="cregit" />
								</div>
							</div>
						</div>


						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">有効期限（月）：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<select name="month" id="a">
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
									</select>
								</div>
							</div>
						</div>


						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">有効期限（年）：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<select name="year" id="a">
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
									</select>
								</div>
							</div>
						</div>

						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">セキュリティコード：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<input class="inputChar" type="number" name="code" />
								</div>
							</div>
						</div>

						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">名義：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<input class="inputChar" type="text" name="name" />
								</div>
							</div>
						</div>

					</div>

					<!--入力内容の送信ボタン-->
					<div class="inputButton">
						<input type="hidden" name="loginUserId" value="${loginUserId}" /> <input
							type="hidden" name="productId" value="${history.productId}" /> <input
							type="hidden" name="quantity" value="${history.quantity}" />  <input
							type="hidden" name="payment" value="${order.payment}" />
							 <input type="submit"
							value="確認" />
					</div>
				</form>
			</div>
		</c:if>



		<c:if test="${order.payment == 2}">
			<div class="tableViewer">
				<form action="<%=request.getContextPath()%>/OrderRegistPaymentInput"
					method="post">
					<!--上段テーブル-->
					<div class="tableSetter">

						<!--クラス「itemLine」が一行分のオブジェクトの塊-->
						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">銀行名：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<input class="inputChar" type="text" name="bankName" />
								</div>
							</div>
						</div>

						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">支店コード：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<input class="inputChar" type="number" name="branchName" />
								</div>
							</div>
						</div>

						<div class="itemLine">
							<div class="leftBox">
								<div class="oneTextLine">口座番号：</div>
							</div>
							<div class="rightBox">
								<div class="oneTextLine">
									<input class="inputChar" type="number" name="bankNum" />
								</div>
							</div>
						</div>

					</div>

					<!--入力内容の送信ボタン-->
					<div class="inputButton">
						<input type="hidden" name="loginUserId" value="${loginUserId}" /> <input
							type="hidden" name="productId" value="${history.productId}" /> <input
							type="hidden" name="quantity" value="${history.quantity}" /> <input
							type="hidden" name="date" value="${order.orderDate}" /> <input
							type="hidden" name="payment" value="${order.payment}" />
							 <input type="submit"
							value="確認" />
					</div>
				</form>
			</div>
		</c:if>

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


	<!--フッター-->
	<footer>
		<div class="footer_logo">ジャイアンツショップ</div>
		<div class="tail_nav">
			<p>利用規約 | プライバシー規約 | 2021年版</p>
		</div>
	</footer>
</body>
</html>