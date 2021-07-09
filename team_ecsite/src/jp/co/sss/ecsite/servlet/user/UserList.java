package jp.co.sss.ecsite.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.UserDao;

/**
 * DAOを呼び出して商品一覧を取得し、リクエストスコープを作成後、
 * 商品一覧画面へフォワードするサーブレット
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 会員リストを取得
		List<User> userList = UserDao.findAll();

		// 10件ごとに表示する場合の、ページ数
		double pageNum = Math.ceil((double) userList.size() / 10);

		// ページ数をリクエストスコープに代入
		request.setAttribute("pageNum", pageNum);

		// ページ番号を取得
		if (!(request.getParameter("paging") == null)) {
			int paging = Integer.parseInt(request.getParameter("paging"));
			int start = (paging - 1) * 10 + 1;
			int end = paging * 10;
			userList = UserDao.findLimit(start, end);
			// 初回アクセス時
		} else {
			userList = UserDao.findLimit(1, 10);
		}

		// リクエストスコープを作成
		request.setAttribute("userList", userList);

		// 会員一覧画面へ遷移
		request.getRequestDispatcher("/jsp/user/user_list.jsp").forward(request, response);
	}
}

