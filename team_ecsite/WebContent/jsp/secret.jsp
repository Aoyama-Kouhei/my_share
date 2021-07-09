<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<html>
<head>
<meta charset="utf-8">
<title>ジャイアンツショップ</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/header_footer_style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css" />
</head>
<body>
	<!--ヘッダー-->
	<%@ include file="/jsp/header.jsp"%>


	<!--ここから書き換え-->
	<div class="processing">
		<!--ページのタイトル-->
		<div class="pageTitle">
			<h3>秘密の質問回答画面</h3>
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
			<form action="<%=request.getContextPath()%>/LoginSecret"
				method="post">
				<!--上段テーブル-->
				<div class="tableSetter">

					<!--クラス「itemLine」が一行分のオブジェクトの塊-->
					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">メールアドレス：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">
								<input class="inputChar" type="email" name="mail" />
							</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">秘密の質問：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">
								<select name="question">
									<option value="1">好きな食べ物は？</option>
									<option value="2">小学校時代の恩師の名前は？</option>
									<option value="3">ジャンケンは何から出す？</option>
								</select>
							</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">秘密の質問回答：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">
								<input class="inputChar" type="text" name="secretAnswer" />
							</div>
						</div>
					</div>
				</div>

				<!--入力内容の送信ボタン-->
				<div class="inputButton">
					<input type="submit" value="ログイン" />
				</div>
			</form>

		</div>

		<!--その他ページ遷移ボタンまとめ-->
		<div class="buttonSetter">

			<form action="<%=request.getContextPath()%>/Logout" method="post">
				<div class="inputButton">
					<input type="submit" value="戻る" />
				</div>
			</form>

		</div>
	</div>

	<!--フッター-->
	<footer>
	<div class="footer_logo">システムジャイアンツ</div>
	<div class="tail_nav">
		<p>利用規約 | プライバシー規約 | 2021年版</p>
	</div>
	</footer>
</body>
</html>
