package jp.co.sss.ecsite.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.ProductDao;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * Servlet implementation class SearchProductByCredit
 */
@WebServlet("/SearchProductByCredit")
public class SearchProductByCredit extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//検索するカテゴリのIDを受け取り検索しリストを取得
		String priceSeachLower = request.getParameter("priceSeachLower");
		String priceSeachUpper = request.getParameter("priceSeachUpper");

		// エラーメッセージリストを作成し、メッセージを格納
		String message;
		message = ErrorMessage.checkPrice(priceSeachLower);
		message = ErrorMessage.checkPrice(priceSeachUpper);

		// エラーがあるかどうかで分岐
		if (message.equals("")) {
			List<Product> productList = ProductDao.findByPrice(priceSeachLower, priceSeachUpper);

			// 10件ごとに表示する場合の、ページ数
			double pageNum = Math.ceil((double) productList.size() / 10);

			// ページ数をリクエストスコープに代入
			request.setAttribute("pageNum", pageNum);

			// ページ番号を取得
			if (!(request.getParameter("paging") == null)) {
				int paging = Integer.parseInt(request.getParameter("paging"));
				int start = (paging - 1) * 10 + 1;
				int end = paging * 10;
				productList = ProductDao.findByPrice(priceSeachLower, priceSeachUpper, start, end);
				// 初回アクセス時
			} else {
				productList = ProductDao.findByPrice(priceSeachLower, priceSeachUpper, 1, 10);
			}

			// サーブレットを識別するリクエストスコープを作成
			request.setAttribute("displayMethod", 5);

			//ページング表示用に価格帯をリクエストスコープに格納
			request.setAttribute("priceSeachLower", priceSeachLower);
			request.setAttribute("priceSeachUpper", priceSeachUpper);

			//リクエストスコープにリストを格納
			request.setAttribute("list", productList);
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		} else {
			List<Product> productList = new ArrayList<Product>();
			productList = ProductDao.findAll();

			// 10件ごとに表示する場合の、ページ数
			double pageNum = Math.ceil((double) productList.size() / 10);

			// ページ数をリクエストスコープに代入
			request.setAttribute("pageNum", pageNum);

			// ページ番号を取得
			if (!(request.getParameter("paging") == null)) {
				int paging = Integer.parseInt(request.getParameter("paging"));
				int start = (paging - 1) * 10 + 1;
				int end = paging * 10;
				productList = ProductDao.findLimit(start, end);
				// 初回アクセス時
			} else {
				productList = ProductDao.findLimit(1, 10);
			}

			request.setAttribute("list", productList);
			// エラーがあれば、エラーメッセージリストをリクエストスコープに格納
			request.setAttribute("message", message);
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}

	}

}
