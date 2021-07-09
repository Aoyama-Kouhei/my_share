package jp.co.sss.ecsite.servlet.product;

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
 * DAOを呼び出して商品一覧を取得し、リクエストスコープを作成後、
 * 商品一覧画面へフォワードするサーブレット
 */
@WebServlet("/ProductList")
public class ProductList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 商品リストを取得
		List<Product> productList = ProductDao.findAll();

		// 10件ごとに表示する場合の、ページ数
		double pageNum = Math.ceil((double)productList.size()/10);

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

		// リクエストスコープを作成
		request.setAttribute("productList", productList);

		// 商品一覧画面へ遷移
		request.getRequestDispatcher("/jsp/product/product_list.jsp").forward(request, response);
	}
}
