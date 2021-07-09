<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/jsp/login_check.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>

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
        <h3>注文確認</h3>
        <table border="1" align="center" class="order_list">
          <tr>
            <th>商品ID</th>
            <th>商品名</th>
            <th>個数</th>
            <th>小計</th>
          </tr>

          <c:forEach var="object" items="${orderList}">

          <tr>

            <td>${object.productId}</td>
            <td>${object.productName}</td>
            <td>${object.quantity}</td>
            <td><fmt:formatNumber value="${object.quantity * object.price}" type="number"/></td>
          </tr>



          </c:forEach>


          <tr>
            <td colspan="3">合計</td>
            <td><fmt:formatNumber value="${orderSumPrice}" type="number"/></td>

          </tr>
        </table>


          <div class="payment_radio_wrapper" align="center">
            お支払方法<br><br>
            <form action="<%=request.getContextPath()%>/Payment" method="post">
            <input type="radio" name="payment" value="1" checked="checked">クレジットカード
            <input type="radio" name="payment" value="2">銀行振り込み
            <input type="radio" name="payment" value="3">着払い
            <br><br>
            <input type="submit" value="次へ">
            </form>
			<br>
            <form action="<%=request.getContextPath()%>/Cart" method="post">
			<input type="hidden" name="productId" value="null">
            <input type="submit" value="戻る">
            </form>
          </div>


      </div>


      <!-- サーブレットにてクレジット払いはpayment.jsp、着払いと銀行振り込みはorder_check.jspへ
        フォワードする。 -->
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