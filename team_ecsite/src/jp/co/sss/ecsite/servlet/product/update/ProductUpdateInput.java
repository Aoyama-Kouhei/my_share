package jp.co.sss.ecsite.servlet.product.update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.dao.CategoryDao;



/**
 * Servlet implementation class ProductRegistInpt
 */
@WebServlet("/ProductUpdateInput")
public class ProductUpdateInput extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// カテゴリテーブルから全レコードを取得
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = CategoryDao.findAll();

		// 編集したい商品の商品IDを取得
		String productId = request.getParameter("productId");

		// 商品IDを格納
		request.setAttribute("productId", productId);

		// 全カテゴリをリクエストスコープに格納
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("jsp/product/update/product_update_input.jsp").forward(request, response);
	}

}
