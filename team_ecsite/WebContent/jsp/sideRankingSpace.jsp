<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sideRankingSpace">
		<div class="productRanking">
			<p>売れ筋ランキング</p>

			<c:forEach var="rankObject" items="${topRankingList}">
				<div class="upperProducts">
					<div class="productPic">
						<img src="<%=request.getContextPath()%>/img/no_img.jpg" width="120" height="120">
					</div>
					${rankObject.productName}<br> ${rankObject.price}円<br>
				</div>
				<br>
			</c:forEach>


		</div>
	</div>