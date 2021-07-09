package jp.co.sss.ecsite.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//各種セッションの破棄
		HttpSession session = request.getSession();
		//ユーザーID
		session.removeAttribute("loginUserId");
		//氏名
		session.removeAttribute("loginUserName");
		//権限
		session.removeAttribute("loginUserAuthority");
		//カート
		if(session.getAttribute("cartList")!=null) {
			session.removeAttribute("cartList");
		}
		//注文リスト
		if(session.getAttribute("orderList")!=null) {
			session.removeAttribute("cartList");
			session.removeAttribute("orderSumPrice");
		}

		//画面遷移
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//画面遷移
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}
}
