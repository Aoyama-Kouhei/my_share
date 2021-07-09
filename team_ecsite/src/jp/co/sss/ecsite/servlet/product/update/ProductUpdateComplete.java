package jp.co.sss.ecsite.servlet.product.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.ProductDao;



/**
 * 商品登録入力画面から値を受け取り、入力チェックを実施後、
 * 商品登録確認画面へフォワードするサーブレット
 */
@WebServlet("/ProductUpdateComplete")
public class ProductUpdateComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームから値を受け取る
		int productId = Integer.parseInt(request.getParameter("productId"));
		String productName  = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		String categoryId = request.getParameter("categoryId");
		String explainText = request.getParameter("explainText");
		String imageUrl = request.getParameter("imageUrl");

		// オブジェクトを作成
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(price);
		Category category = new Category();
		category.setCategoryId(categoryId);
		product.setCategory(category);
		product.setExplainText(explainText);
		product.setImageUrl(imageUrl);

		// DAOを呼び出してデータベースに登録
		ProductDao.update(product);

		// 商品登録完了画面へ遷移
		request.getRequestDispatcher("/jsp/product/update/product_update_complete.jsp").forward(request, response);
	}
}
