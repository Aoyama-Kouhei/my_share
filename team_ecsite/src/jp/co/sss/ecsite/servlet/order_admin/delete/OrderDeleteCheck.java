package jp.co.sss.ecsite.servlet.order_admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Order;
import jp.co.sss.ecsite.dao.OrderDao;

/**
 * Servlet implementation class OrderDeleteCheck
 */
@WebServlet("/OrderDeleteCheck")
public class OrderDeleteCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 削除対象のIDを受け取る
		int orderId =  Integer.parseInt(request.getParameter("orderId"));

		// 削除する注文情報の全項目について検索して取得する
		Order order = OrderDao.findById(orderId);

		// 検索結果の入ったリストをリクエスト属性に登録しておく
		request.setAttribute("order", order);

		// 削除情報確認画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/delete/order_delete_check.jsp");
		dispatcher.forward(request, response);
	}
}
