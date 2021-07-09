package jp.co.sss.ecsite.servlet.order_admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.History;

/**
 * Servlet implementation class HistoryDeleteCheck
 */
@WebServlet("/HistoryDeleteCheck")
public class HistoryDeleteCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 削除対象のIDを受け取る
		int historyId =  Integer.parseInt(request.getParameter("historyId"));
		int productId =  Integer.parseInt(request.getParameter("productId"));
		String productName =  request.getParameter("productName");
		int quantity=  Integer.parseInt(request.getParameter("quantity"));
		int price=  Integer.parseInt(request.getParameter("price"));
		History history = new History();

		history.setHistoryId(historyId);
		history.setProductId(productId);
		history.setProductName(productName);
		history.setQuantity(quantity);
		history.setPrice(price);

		request.setAttribute("history", history);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/delete/history_delete_check.jsp");
		dispatcher.forward(request, response);
	}

}