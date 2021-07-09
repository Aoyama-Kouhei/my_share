package jp.co.sss.ecsite.servlet.order_admin.regist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderRegistInput
 */
@WebServlet("/OrderRegistInput")
public class OrderRegistInput extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//登録入力画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/regist/order_regist_input.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 入力フォームクラスの宣言


		// 検索結果の入ったリストをリクエスト属性に登録しておく

		// 登録入力画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/regist/order_regist_input.jsp");
		dispatcher.forward(request, response);
	}

}
