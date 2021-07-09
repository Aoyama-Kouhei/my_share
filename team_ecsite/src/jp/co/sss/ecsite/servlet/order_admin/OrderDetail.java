package jp.co.sss.ecsite.servlet.order_admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.bean.Order;
import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.HistoryDao;
import jp.co.sss.ecsite.dao.OrderDao;
import jp.co.sss.ecsite.dao.UserDao;

/**
 * Servlet implementation class OrderDetail
 */
@WebServlet("/OrderDetail")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderId = request.getParameter("orderId");
		Order order = new Order();
		User user = new User();

		List<History> historyList = new ArrayList<>();

		historyList = HistoryDao.findHistoryByOrderId(Integer.parseInt(orderId));
		order = OrderDao.findById(Integer.parseInt(orderId));
		user = UserDao.findById(order.getUserId());

		request.setAttribute("orderUserName", user.getUserName());
		request.setAttribute("order", order);
		request.setAttribute("historyList", historyList);
		// 登録入力画面に遷移
		request.getRequestDispatcher("/jsp/order_admin/order_detail.jsp").forward(request,
				response);
	}

}
