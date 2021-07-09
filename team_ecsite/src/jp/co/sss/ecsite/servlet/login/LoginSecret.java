package jp.co.sss.ecsite.servlet.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.ProductDao;
import jp.co.sss.ecsite.dao.UserDao;
import jp.co.sss.ecsite.util.ErrorMessage;
import jp.co.sss.ecsite.util.OrderUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginSecret")
public class LoginSecret extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//情報の取得
		String email = request.getParameter("mail");
		int secretId = Integer.parseInt(request.getParameter("question"));
		String secretAnswer = request.getParameter("secretAnswer");
		//ログインチェック
		User user = UserDao.secretLoginCheck(email, secretId, secretAnswer);
		//入力チェック
		List<String> messageList = new ArrayList<>();
		messageList = ErrorMessage.checkLoginSecret(email, secretAnswer, user);
		//ログインできたとき
		if (ErrorMessage.error(messageList)) {
			//各種セッションの設定
			HttpSession session = request.getSession();
			//ユーザーID
			session.setAttribute("loginUserId", user.getUserId());
			//氏名
			session.setAttribute("loginUserName", user.getUserName());
			//権限(管理者:true,一般:false)
			if (user.getAuthority() == 1) {
				session.setAttribute("loginUserAuthority", true);
			} else {
				session.setAttribute("loginUserAuthority", false);
			}
			List<Product> productList = new ArrayList<Product>();

			productList = ProductDao.findAll();

			// 10件ごとに表示する場合の、ページ数
			double pageNum = Math.ceil((double)productList.size()/10);

			// ページ数をリクエストスコープに代入
			request.setAttribute("pageNum", pageNum);

			// ページ番号を取得
			if (!(request.getParameter("paging") == null)) {
				int paging = Integer.parseInt(request.getParameter("paging"));
				int start = (paging - 1) * 10 + 1;
				int end = paging * 10;
				productList = ProductDao.findLimit(start, end);
				// 初回アクセス時
			} else {
				productList = ProductDao.findLimit(1, 10);
			}

			productList = OrderUtil.OmitAllExplain(productList);

			request.setAttribute("list", productList);
			//画面遷移
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		} else {
			//ログインできなかったとき
			request.setAttribute("messageList", messageList);
			//画面遷移
			request.getRequestDispatcher("/jsp/secret.jsp").forward(request, response);
		}
	}

}
