<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header_footer_style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" />
<title>ショッピングシステム</title>
</head>
<body>
	<!--ヘッダー-->
	<%@ include file="/jsp/header.jsp"%>

	<!--ここから書き換え-->
	<div class="processing">
		<!--ページのタイトル-->
		<div class="pageTitle">
			<h3>会員削除完了画面</h3>
		</div>

		<div class="completeMsg">
			<p>会員削除処理が完了しました。</p>
		</div>

		<div class="backToList">
			<a href="<%=request.getContextPath()%>/UserList">会員一覧画面に戻る</a>
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