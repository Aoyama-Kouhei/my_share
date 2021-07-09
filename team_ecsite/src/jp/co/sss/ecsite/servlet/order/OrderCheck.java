package jp.co.sss.ecsite.servlet.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.util.ErrorMessage;


@WebServlet("/OrderCheck")
public class OrderCheck extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int payment = Integer.parseInt(request.getParameter("payment"));

		List<String> messageList = new ArrayList<>();
		if(payment == 1) {
			//クレジット情報取得
			String creditName = request.getParameter("credit_name");
			String creditNo = request.getParameter("credit_no");
			String cvv = request.getParameter("cvv");

			//クレジット情報入力エラーチェック
			messageList = ErrorMessage.checkProductyRegist2(creditNo, cvv, creditName);

		} else if(payment == 2) {
			//口座情報取得
			String bankName = request.getParameter("bank_name");
			String bankNo = request.getParameter("bank_no");
			String accountNo = request.getParameter("account_no");

			//口座情報入力エラーチェック
			messageList = ErrorMessage.checkProductyRegist2sub(bankName, bankNo, accountNo);
		}

		//エラーありの場合
		if(!ErrorMessage.error(messageList)) {
			//エラーメッセージをリクエストスコープに設定
			request.setAttribute("messageList", messageList);
			request.setAttribute("payment", payment);
			//支払い画面に戻る
			request.getRequestDispatcher("/jsp/order/payment.jsp").forward(request, response);
		}else {
			//エラーなしの場合
			//注文確認画面へ遷移
			request.setAttribute("payment", payment);
			request.getRequestDispatcher("/jsp/order/order_check.jsp").forward(request, response);
		}



	}

}
