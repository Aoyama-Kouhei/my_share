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
		<h3>会員削除確認画面</h3>
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
						<div class="oneTextLine">${user.email}</div>
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
						<div class="oneTextLine">${user.userName}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">誕生日：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${user.birthday}</div>
					</div>
				</div>

				<div class="itemLine">
					<div class="leftBox">
						<div class="oneTextLine">住所：</div>
					</div>
					<div class="rightBox">
						<div class="oneTextLine">${user.address}</div>
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
								<div class="oneTextLine">管理者</div>
								</c:when>
								<c:when test="${authority == 2}">
								<div class="oneTextLine">一般</div>
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
		<form action="<%=request.getContextPath()%>/UserDeleteComplete"  method="post">
			<div class="inputButton">
				<input type="hidden" name="userId" value="${userId}" />
				<input type="submit" value="確定" />
			</div>
		</form>

	</div>

	<!--その他ページ遷移ボタンまとめ-->
	<div class="buttonSetter">

		<form action="<%=request.getContextPath()%>/UserList">
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