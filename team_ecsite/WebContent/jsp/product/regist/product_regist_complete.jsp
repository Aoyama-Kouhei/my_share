<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/header_footer_style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

<!--ここから書き換え-->
	<div class="processing">
		<!--ページのタイトル-->
		<div class="pageTitle">
			<h3>商品登録完了画面</h3>
		</div>

		<div class="completeMsg">
			<p>商品登録処理が完了しました。</p>
		</div>

		<div class="backToList">
			<a href="<%=request.getContextPath()%>/ProductList">商品一覧画面に戻る</a>
		</div>

		<br>
	</div>

  <footer>
    <div class="footer_logo">ジャイアンツショップ</div>
    <div class="tail_nav">
      <p>利用規約 | プライバシー規約 | 2021年版</p>
    </div>
  </footer>
</body>
</html>