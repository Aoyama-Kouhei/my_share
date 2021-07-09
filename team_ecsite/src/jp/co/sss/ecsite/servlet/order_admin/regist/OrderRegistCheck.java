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
import jp.co.sss.ecsite.bean.Order;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * Servlet implementation class OrderRegistCheck
 * 注文登録入力確認画面へ遷移
 */
@WebServlet("/OrderRegistCheck")
public class OrderRegistCheck extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力された値を格納
		History history = new History();
		Order order = new Order();

		//入力された情報を受け取る
		int userId = (Integer.parseInt(request.getParameter("loginUserId")));
		String productId = request.getParameter("productId");
		String quantity = request.getParameter("quantity");
		int payment = (Integer.parseInt(request.getParameter("payment")));

		System.out.println(userId);
		System.out.println(productId);
		System.out.println(quantity);
		System.out.println(payment);

		//エラー
		List<String> messageList = new ArrayList<>();
		messageList = ErrorMessage.checkProductyRegist3(productId, quantity);
		System.out.println(messageList);




		if (ErrorMessage.error(messageList) && payment != 3) {


			order.setUserId(userId);
			history.setProductId(Integer.parseInt(productId));
			history.setQuantity(Integer.parseInt(quantity));
			order.setPayment(payment);



			//情報の入ったリストをリクエスト属性に登録しておく
			request.setAttribute("history", history);
			request.setAttribute("order", order);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/jsp/order_admin/regist/order_regist_pyment_input.jsp");
			dispatcher.forward(request, response);

		}

		else if(ErrorMessage.error(messageList)){


			order.setUserId(userId);
			history.setProductId(Integer.parseInt(productId));
			history.setQuantity(Integer.parseInt(quantity));
			order.setPayment(payment);

			//情報の入ったリストをリクエスト属性に登録しておく
			request.setAttribute("history", history);
			request.setAttribute("order", order);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/jsp/order_admin/regist/order_regist_check.jsp");
			dispatcher.forward(request, response);

		}

		else {

			request.setAttribute("messageList", messageList);
			request.getRequestDispatcher("/jsp/order_admin/regist/order_regist_input.jsp").forward(request,
					response);
		}
	}
}
