package jp.co.sss.ecsite.servlet.order_admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.dao.OrderDao;

/**
 * Servlet implementation class OrderDeleteComplete
 */
@WebServlet("/OrderDeleteComplete")
public class OrderDeleteComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 削除対象のIDを受け取る
		int orderId =  Integer.parseInt(request.getParameter("orderId"));

		// 削除対象情報の社員IDを利用して削除を実行する
		OrderDao.delete(orderId);

		// 削除完了画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/delete/order_delete_complete.jsp");
		dispatcher.forward(request, response);
	}
}
