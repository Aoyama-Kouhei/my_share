package jp.co.sss.ecsite.servlet.user.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.UserDao;

/**
 * Servlet implementation class UserDeleteCheck
 */
@WebServlet("/UserDeleteCheck")
public class UserDeleteCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		boolean userAuthority = false;
		if(session.getAttribute("loginUserAuthority")!= null) {
			userAuthority = (boolean) session.getAttribute("loginUserAuthority");
		}

		// 削除対象のIDを受け取る
		int userId = Integer.parseInt(request.getParameter("userId"));

		// 削除する社員情報の全項目について検索して取得する
		User user = UserDao.findById(userId);

		// 検索結果の入ったリストをリクエスト属性に登録しておく
		request.setAttribute("user", user);
		request.setAttribute("userId", userId);
		request.setAttribute("userAuthority", userAuthority);

		// 削除情報確認画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/user/delete//user_delete_check.jsp");
		dispatcher.forward(request, response);
	}

}