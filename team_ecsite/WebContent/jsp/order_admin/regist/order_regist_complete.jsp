<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/login_check.jsp"%>
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
			<h3>注文登録完了画面</h3>
		</div>

		<div class="completeMsg">
			<p>注文登録が完了しました。</p>
		</div>

		<div class="backToList">
			<a href="<%=request.getContextPath()%>/OrderList">注文一覧画面に戻る</a>
		</div>

		<br>
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