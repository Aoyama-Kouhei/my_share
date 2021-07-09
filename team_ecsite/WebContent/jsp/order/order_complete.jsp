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
      <!-- ヘッダー -->
      <div class="main_content">
        <h3>注文確定</h3>
        <div class="massage" align="center">
          ご注文ありがとうございました。




          <br><br>
          <form action="<%=request.getContextPath()%>/BackToIndex" method="get">
            <input type="submit" value="商品一覧画面に戻る">
              </form>
        </div>
      </div>


      <!-- フッター -->
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