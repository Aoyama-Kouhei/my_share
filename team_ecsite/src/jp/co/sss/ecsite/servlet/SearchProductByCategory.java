package jp.co.sss.ecsite.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.ProductDao;

/**
 * Servlet implementation class SearchProductByCategory
 */
@WebServlet("/SearchProductByCategory")
public class SearchProductByCategory extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメーターから値の取得
		String categoryId = request.getParameter("categoryId");

		//検索するカテゴリのIDを受け取り検索しリストを取得
		List<Product> productList = ProductDao.findByCategory(categoryId);

		// 10件ごとに表示する場合の、ページ数
		double pageNum = Math.ceil((double) productList.size() / 10);

		// ページ数をリクエストスコープに代入
		request.setAttribute("pageNum", pageNum);

		// ページ番号を取得
		if (!(request.getParameter("paging") == null)) {
			int paging = Integer.parseInt(request.getParameter("paging"));
			int start = (paging - 1) * 10 + 1;
			int end = paging * 10;
			productList = ProductDao.findByCategory(categoryId,start, end);
			// 初回アクセス時
		} else {
			productList = ProductDao.findByCategory(categoryId, 1, 10);
		}

		// サーブレットを識別するリクエストスコープを作成
		request.setAttribute("displayMethod", 4);

		//ページング表示用にカテゴリIDをリクエストスコープに格納
		request.setAttribute("categoryId", categoryId);

		//リクエストスコープにリストを格納
		request.setAttribute("list", productList);
		request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
	}

}
