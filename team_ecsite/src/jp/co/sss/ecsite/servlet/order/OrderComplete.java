
package jp.co.sss.ecsite.servlet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.util.OrderUtil;

/**
 * Servlet implementation class OrderComplete
 */
@WebServlet("/OrderComplete")
public class OrderComplete extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//注文登録に不要なセッション値を削除
		session.removeAttribute("orderSumPrice");
		session.removeAttribute("cartList");

		int payment = Integer.parseInt(request.getParameter("payment"));

		int userId = (int)session.getAttribute("loginUserId");
		@SuppressWarnings("unchecked")
		List<History> orderList = (List<History>)session.getAttribute("orderList");

		//注文情報登録
		OrderUtil.registOrderHistory(userId, payment, orderList);

		//注文リスト削除
		session.removeAttribute("orderList");
		//注文確認画面へ遷移
		request.getRequestDispatcher("/jsp/order/order_complete.jsp").forward(request, response);
	}

}
