package jp.co.sss.ecsite.servlet.user.update;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.Secret;
import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.UserDao;

/**
 * Servlet implementation class UserUpdateComplete
 */
@WebServlet("/UserUpdateComplete")
public class UserUpdateComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 入力された値をBeanに格納
		User user = new User();
		user.setUserId(Integer.parseInt(request.getParameter("userId")));
		user.setUserName(request.getParameter("userName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setAddress(request.getParameter("address"));
		user.setBirthday(request.getParameter("birthday"));
		user.setSecretAnswer(request.getParameter("secretAnswer"));
		user.setAuthority(Integer.parseInt(request.getParameter("authority")));

		Secret secret = new Secret();
		secret.setSecretId(Integer.parseInt(request.getParameter("secretId")));
		user.setSecret(secret);

		// 変更処理を実行する
		try {
			UserDao.update(user);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		// セッションを利用する準備
		HttpSession session = request.getSession();


		// セッション属性に登録してあるログインユーザーの情報を取得,ない場合は0
		int loginUserId = 0;
		if(session.getAttribute("loginUserId")!= null) {
			loginUserId = (Integer) session.getAttribute("loginUserId");
		}

		// ログインユーザーの情報が変更された場合、セッションに登録している情報を変更
		if(loginUserId == user.getUserId()) {
			session.setAttribute("loginUserId", user.getUserId());
			session.setAttribute("loginUserName", user.getUserId());
			session.setAttribute("loginUserAuthority", user.getUserId());
		}

		// 変更完了画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/user/update/user_update_complete.jsp");
		dispatcher.forward(request, response);
	}

}
