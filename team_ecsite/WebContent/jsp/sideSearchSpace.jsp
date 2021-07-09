<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sideSearchSpace">
	<div class="search">
		<div class="categorySearch">
			<form action="<%=request.getContextPath()%>/SearchProductByCategory" method="post">
				<p>カテゴリ検索</p>
				<select name="categoryId">
					<c:forEach var="categorys" items="${categoryList}">
						<c:if test="${categoryId == categorys.categoryId}">
							<option value="${categorys.categoryId}" selected>${categorys.categoryName}</option>
						</c:if>
						<c:if test="${categoryId != categorys.categoryId}">
							<option value="${categorys.categoryId}">${categorys.categoryName}</option>
						</c:if>
					</c:forEach>
				</select>
				<input type="submit" value="検索" />
			</form>
		</div>
		<br>
		<div class="priceSearch">
			<form action="<%=request.getContextPath()%>/SearchProductByCredit" method="post">
				<p>価格検索</p>
				<div class="errorMsg">${message}</div>
				最低価格
				<c:choose>
					<c:when test="${priceSeachLower == null}">
						<input type="text" name="priceSeachLower"/><br>
					</c:when>
					<c:otherwise>
						<input type="text" name="priceSeachLower" value="${priceSeachLower}"/><br>
					</c:otherwise>
				</c:choose>
				最高価格
				<c:choose>
					<c:when test="${priceSeachLower == null}">
						<input type="text" name="priceSeachUpper"/> <br> <br>
					</c:when>
					<c:otherwise>
						<input type="text" name="priceSeachUpper" value="${priceSeachUpper}"/> <br> <br>
					</c:otherwise>
				</c:choose>

				<input type="submit" value="検索" />
			</form>
			<br>
		</div>
	</div>
</div>