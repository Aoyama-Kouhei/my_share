package jp.co.sss.ecsite.servlet.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")

public class Order extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		//注文リストが空の場合
		if (session.getAttribute("orderList") == null) {
			int orderListSize = Integer.parseInt(request.getParameter("orderListSize"));
			//注文リスト作成
			List<History> orderList = new ArrayList<History>();
			String err = "";

			//注文合計金額保持用変数
			int orderSumPrice = 0;

			for (int i = 0; i < orderListSize; i++) {
				String sQuantity = request.getParameter("quantity_" + i);
				err = ErrorMessage.checkQuantity(sQuantity);
				//エラーメッセージが生成された場合処理中断
				if (!err.equals("")) {
					break;
				}

				//商品情報取得
				int quantity = Integer.parseInt(sQuantity);
				int productId = Integer.parseInt(request.getParameter("productId_" + i));
				String productName = request.getParameter("productName_" + i);
				int price = Integer.parseInt(request.getParameter("price_" + i));

				//beanへ情報追加
				History tmp = new History();
				tmp.setProductId(productId);
				tmp.setProductName(productName);
				tmp.setQuantity(quantity);
				tmp.setPrice(price);

				//注文リストへ追加
				orderList.add(tmp);

				//合計へ合算
				orderSumPrice += quantity * price;
			}

			//エラーメッセージありの場合カート画面へ戻る
			if (!err.equals("")) {
				request.setAttribute("errorMsg", err);
				request.getRequestDispatcher("/jsp/order/cart.jsp").forward(request, response);
			} else {
				//エラーなしの場合注文合計と注文リストをセッションへ格納
				session.setAttribute("orderSumPrice", orderSumPrice);
				session.setAttribute("orderList", orderList);
				//注文画面へ遷移
				request.getRequestDispatcher("/jsp/order/order.jsp").forward(request, response);

			}

		} else {
			//注文内容が変更なしの場合そのまま注文画面へ遷移
			request.getRequestDispatcher("/jsp/order/order.jsp").forward(request, response);
		}

	}
}
