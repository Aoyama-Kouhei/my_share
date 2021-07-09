package jp.co.sss.ecsite.servlet.order_admin.regist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.util.OrderUtil;

/**
 * Servlet implementation class OrderRegistComplete
 */
@WebServlet("/OrderRegistComplete")
public class OrderRegistComplete extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//登録処理で利用するbeanを用意
		History history = new History();

		//入力された情報を受け取り、beanに値を詰める

		int userId = Integer.parseInt(request.getParameter("loginUserId"));
		int payment = Integer.parseInt(request.getParameter("payment"));

		System.out.println(userId);
		System.out.println(payment);

		history.setProductId(Integer.parseInt(request.getParameter("productId")));
		history.setQuantity(Integer.parseInt(request.getParameter("quantity")));

		List<History> orderList = new ArrayList<History>();

		orderList.add(history);

		//自動⇒order)注文、日付、論理削除
		//　　　history)履歴、論理削除　注文はorderで作成されたものを入力

		OrderUtil.registOrderHistory(userId, payment, orderList);

		// 登録完了画面に遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/regist/order_regist_complete.jsp");
		dispatcher.forward(request, response);
	}


}
