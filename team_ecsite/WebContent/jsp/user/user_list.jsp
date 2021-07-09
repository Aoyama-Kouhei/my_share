<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/header_footer_style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/layout.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>ジャイアンツショップ</title>
</head>
<body>
	<%@ include file="/jsp/header.jsp"%>

    <div class="mainContentSpace">
      <!--ここに各ページに関するものを表示-->
      <h3 class="page_title">会員一覧画面</h3>
			<div class="regist_link">
				<a href="<%=request.getContextPath()%>/UserRegistInput">新規会員登録</a>
			</div>
			<table class="user_list_table">
				<tr>
					<th class="userId">会員ID</th>
					<th class="email">メールアドレス</th>
					<th class="userName">会員氏名</th>
					<th class="address">住所</th>
					<th class="birthday">誕生日</th>
					<th class="authority">権限</th>
					<th class="button" colspan="3">操作</th>
				</tr>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.email}</td>
						<td>${user.userName}</td>
						<td>${user.address}</td>
						<td>${user.birthday}</td>
						<td>${user.authority == 2 ? '一般' : '管理者'}</td>
						<td class="button">
							<form action="<%=request.getContextPath()%>/UserDetail" method="post">
								<input type="hidden" name="userId" value="${user.userId}" />
								<input type="submit" value="詳細" />
							</form>
						</td>
						<td class="button">
							<form action="<%=request.getContextPath()%>/UserUpdateInput" method="post">
								<input type="hidden" name="userId" value="${user.userId}" />
								<input type="submit" value="編集" />
							</form>
						</td>
						<td class="button">
							<form action="<%=request.getContextPath()%>/UserDeleteCheck" method="post">
								<input type="hidden" name="userId" value="${user.userId}" />
								<input type="submit" value="削除"/>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		<div class="paging">
			<table style="border:none;">
				<tr style="border:none;">
					<c:forEach var="i" begin="1" end="${pageNum}" step="1">
						<td style="border:none;">
							<form action="<%=request.getContextPath()%>/UserList">
								<input class="paging-button" type="hidden" name="paging" value="${i}" />
								<input type="submit" value="${i}">
							</form>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
    </div>

  <footer>
    <div class="footer_logo">ジャイアンツショップ</div>
    <div class="tail_nav">
      <p>利用規約 | プライバシー規約 | 2021年版</p>
    </div>
  </footer>
</body>
</html>