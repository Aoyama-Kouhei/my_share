package jp.co.sss.ecsite.servlet.order_admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Order;
import jp.co.sss.ecsite.dao.OrderDao;

/**
 * Servlet implementation class OrderList
 */
@WebServlet("/OrderList")
public class OrderList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 注文リストを取得する
		List<Order> orderList = OrderDao.findAll();

		// 10件ごとに表示する場合の、ページ数
		double pageNum = Math.ceil((double) orderList.size() / 10);

		// ページ数をリクエストスコープに代入
		request.setAttribute("pageNum", pageNum);

		// ページ番号を取得
		if (!(request.getParameter("paging") == null)) {
			int paging = Integer.parseInt(request.getParameter("paging"));
			int start = (paging - 1) * 10 + 1;
			int end = paging * 10;
			orderList = OrderDao.findLimit(start, end);
			// 初回アクセス時
		} else {
			orderList = OrderDao.findLimit(1, 10);
		}

		// 検索結果の入ったリストをリクエスト属性に登録しておく
		request.setAttribute("orderList", orderList);

		// 一覧表示画面へ画面遷移を行う
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/order_admin/order_list.jsp");
		dispatcher.forward(request, response);
	}

}
