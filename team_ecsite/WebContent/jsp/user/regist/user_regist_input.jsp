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
			<h3>会員登録入力画面</h3>
			<div class="errorMsg">
			<c:forEach  var="st" items="${messageList}" >
				<c:choose>
					<c:when test="${!empty st}">
					${st}<br>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			</div>
		</div>

		<div class="tableViewer">
			<form action="<%=request.getContextPath()%>/UserRegistCheck" method="post">
				<!--上段テーブル-->
				<div class="tableSetter">

					<!--クラス「itemLine」が一行分のオブジェクトの塊-->
					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">メールアドレス：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">
								<input class="inputChar" type="e-mail" name="email" />
							</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">パスワード：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input class="inputChar" type="password" name="password1" /></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">パスワード(再確認)：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input class="inputChar" type="password" name="password2" /></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">氏名：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input class="inputChar" type="text" name="userName" /></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">誕生日：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input class="inputChar" type="text" name="birthday" /></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">住所：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input class="inputChar" type="text" name="address" /></div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">秘密の質問：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine">
							<div class="input">
							<select name="secretId">

								<c:forEach  var="st" items="${secretList}" >
									<option value="${st.getSecretId()}">${st.getSecretQuestion()}</option>
								</c:forEach>

							</select>
						</div>
							</div>
						</div>
					</div>

					<div class="itemLine">
						<div class="leftBox">
							<div class="oneTextLine">秘密の質問の答え：</div>
						</div>
						<div class="rightBox">
							<div class="oneTextLine"><input class="inputChar" type="text" name="secretAnswer" /></div>
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
								<label><input type="radio" name="authority" value="1"/>管理者</label>
								<label><input type="radio" name="authority" value="2" checked/>一般</label>
							</div>
						</div>
						</div>
						</c:when>
						<c:when test="${userAuthority == false}">
							<input type="hidden" name="authority" value="2"/>
						</c:when>
						<c:otherwise>
							<input type="hidden" name="authority" value="2"/>
						</c:otherwise>
					</c:choose>

				</div>

				<!--入力内容の送信ボタン-->
				<div class="inputButton">
					<input type="submit" value="確認" />
				</div>
			</form>

		</div>

		<!--その他ページ遷移ボタンまとめ-->
		<div class="buttonSetter">
			<c:choose>
				<c:when test="${loginUserAuthority == null}">
					<form action="<%=request.getContextPath()%>/jsp/login.jsp">
						<div class="inputButton">
							<input type="submit" value="戻る" />
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<form action="<%=request.getContextPath()%>/UserList" method="get">
						<div class="inputButton">
							<input type="submit" value="戻る" />
						</div>
					</form>
				</c:otherwise>
			</c:choose>


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