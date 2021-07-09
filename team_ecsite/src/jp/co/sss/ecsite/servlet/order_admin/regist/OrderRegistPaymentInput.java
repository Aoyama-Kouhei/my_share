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
 * Servlet implementation class OrderRegistPaymentInput
 */
@WebServlet("/OrderRegistPaymentInput")
public class OrderRegistPaymentInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力された値を格納
		History history = new History();
		Order order = new Order();

		//入力された情報を受け取る
		int userId = (Integer.parseInt(request.getParameter("loginUserId")));
		int productId = (Integer.parseInt(request.getParameter("productId")));
		int quantity = (Integer.parseInt(request.getParameter("quantity")));
		int payment = (Integer.parseInt(request.getParameter("payment")));


		List<String> messageList = new ArrayList<>();

		if(payment == 1) {
			String cregit = request.getParameter("cregit");
			String code = request.getParameter("code");
			String name = request.getParameter("name");

			messageList = ErrorMessage.checkProductyRegist2(cregit,code, name);

		} else if(payment == 2) {

			String bankName = request.getParameter("bankName");
			String branchName = request.getParameter("branchName");
			String bankNum = request.getParameter("bankNum");

			messageList = ErrorMessage.checkProductyRegist2sub(bankName, branchName, bankNum);
		}

		if(!ErrorMessage.error(messageList)) {

			request.setAttribute("messageList", messageList);
			request.setAttribute("payment", payment);
			request.getRequestDispatcher("/jsp/order_admin/regist/order_regist_pyment_input.jsp").forward(request, response);

		}else {

			//情報の入ったリストをリクエスト属性に登録しておく

			order.setUserId(userId);
			history.setProductId(productId);
			history.setQuantity(quantity);
			order.setPayment(payment);

			request.setAttribute("history", history);
			request.setAttribute("order", order);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/jsp/order_admin/regist/order_regist_check.jsp");
			dispatcher.forward(request, response);	}


	}

}
