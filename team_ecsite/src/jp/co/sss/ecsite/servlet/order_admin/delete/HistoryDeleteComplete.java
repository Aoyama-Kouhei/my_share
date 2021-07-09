package jp.co.sss.ecsite.servlet.order_admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.dao.HistoryDao;

/**
 * Servlet implementation class HistoryDeleteComplete
 */
@WebServlet("/HistoryDeleteComplete")
public class HistoryDeleteComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 削除対象のIDを受け取る
		int historyId =  Integer.parseInt(request.getParameter("historyId"));
		HistoryDao.delete(historyId);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/delete/history_delete_complete.jsp");
		dispatcher.forward(request, response);
	}

}
