package jp.co.sss.ecsite.servlet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.bean.Product;

/**
 * 商品一覧画面から値を受け取り
 * 商品詳細画面へフォワードするサーブレット
 */
@WebServlet("/ProductDetailAdmin")
public class ProductDetailAdmin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームから値(hidden)を受け取る
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName  = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");
		String explainText = request.getParameter("explainText");
		String imageUrl = request.getParameter("imageUrl");

		// DTOに格納
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(price);
		product.setExplainText(explainText);
		product.setImageUrl(imageUrl);
		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		product.setCategory(category);

		// リクエストスコープを作成し、DTOを格納
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/product/product_detail_admin.jsp").forward(request, response);
	}
}
