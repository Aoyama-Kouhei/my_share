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
	<%@ include file="/jsp/header.jsp"%>


<!--ここから書き換え-->
<div class="processing">
	<!--ページのタイトル-->
	<div class="pageTitle">
		<h3>会員登録確認画面</h3>
	</div>

	<div class="tableViewer">
			<!--上段テーブル-->
			<div class="tableSetter">

				<!--クラス「itemLine」が一行分のオブジェクトの塊-->
				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">メールアドレス：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${email}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">パスワード：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">※非表示</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">氏名：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${userName}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">誕生日：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${birthday}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">住所：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${address}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">秘密の質問：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${secretId.getSecretQuestion()}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">秘密の質問の答え：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${secretAnswer}</div>
					</div>
				</div>

				<c:choose>
					<c:when test="${userAuthority == true}">
					<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">権限：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">

							<c:choose>
								<c:when test="${authority == 1}">
								管理者
								</c:when>
								<c:when test="${authority == 2}">
								一般
								</c:when>
							</c:choose>
						</div>
					</div>
					</div>
					</c:when>
					<c:when test="${userAuthority == false}">

					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>

			</div>

			<!--入力内容の送信ボタン-->
		<form action="<%=request.getContextPath()%>/UserRegistComplete"  method="post">
			<div class="inputButton">
				<input type="hidden" name="email" value="${email}" /> <input
								type="hidden" name="password2" value="${password2}" /> <input type="hidden"
								name="userName" value="${userName}" /> <input type="hidden" name="birthday"
								value="${birthday}" /> <input type="hidden" name="address"
								value="${address}" /> <input type="hidden" name="secretId"
								value="${secretId.getSecretId()}" /> <input type="hidden" name="secretAnswer" value="${secretAnswer}" />
								<input type="hidden" name="authority" value="${authority}" /> <input
								type="submit" value="確定" />
			</div>
		</form>

	</div>

	<!--その他ページ遷移ボタンまとめ-->
	<div class="buttonSetter">

		<form action="<%=request.getContextPath()%>/UserRegistInput">
			<div class="inputButton">
				<input type="hidden" name="empId" value="4" />
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