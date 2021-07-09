package jp.co.sss.ecsite.servlet.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Payment")
public class Payment extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int payment = Integer.parseInt(request.getParameter("payment"));

		switch (payment) {

		case 1:
			//クレジット支払いの場合
			request.setAttribute("payment", payment);
			request.getRequestDispatcher("/jsp/order/payment.jsp").forward(request, response);
			break;

		case 2:
			//口座振り込みの場合
			request.setAttribute("payment", payment);
			request.getRequestDispatcher("/jsp/order/payment.jsp").forward(request, response);
			break;

		case 3:
			//代引きの場合
			request.setAttribute("payment", payment);
			request.getRequestDispatcher("/jsp/order/order_check.jsp").forward(request, response);
			break;

		default:

			request.getRequestDispatcher("/jsp/order/payment.jsp").forward(request, response);
			break;
		}
	}

}
