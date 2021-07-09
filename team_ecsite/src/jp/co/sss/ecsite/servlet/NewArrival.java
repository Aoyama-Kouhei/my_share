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
 * Servlet implementation class NewArrival
 */
@WebServlet("/NewArrival")
public class NewArrival extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//検索するカテゴリのIDを受け取り検索しリストを取得
		List<Product> productList = ProductDao.findAllDesc();

		// 10件ごとに表示する場合の、ページ数
		double pageNum = Math.ceil((double) productList.size() / 10);

		// ページ数をリクエストスコープに代入
		request.setAttribute("pageNum", pageNum);

		// ページ番号を取得
		if (!(request.getParameter("paging") == null)) {
			int paging = Integer.parseInt(request.getParameter("paging"));
			int start = (paging - 1) * 10 + 1;
			int end = paging * 10;
			productList = ProductDao.findAllDesc(start, end);
			// 初回アクセス時
		} else {
			productList = ProductDao.findAllDesc(1, 10);
		}

		// サーブレットを識別するリクエストスコープを作成
		request.setAttribute("displayMethod", 2);

		//リクエストスコープにリストを格納
		request.setAttribute("list", productList);
		request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
	}

}
