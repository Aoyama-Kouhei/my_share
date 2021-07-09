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
import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.CategoryDao;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * 商品登録入力画面から値を受け取り、入力チェックを実施後、
 * 商品登録確認画面へフォワードするサーブレット
 */
@WebServlet("/ProductUpdateCheck")
public class ProductUpdateCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームから値を受け取る
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String price = request.getParameter("price");
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter(categoryId);
		String explainText = request.getParameter("explainText");
		String imageUrl = request.getParameter("imageUrl");

		// エラーメッセージリストを作成し、メッセージを格納
		List<String> messageList = new ArrayList<>();
		messageList = ErrorMessage.checkProductUpdate(productName, price,explainText);

		// エラーがあるかどうかで分岐
		if (ErrorMessage.error(messageList)) {
			// オブジェクトを作成
			Product product = new Product();
			product.setProductId(Integer.parseInt(productId));
			product.setProductName(productName);
			product.setPrice(Integer.parseInt(price));
			Category category = new Category();
			category.setCategoryId(categoryId);
			category.setCategoryName(categoryName);
			product.setCategory(category);
			product.setExplainText(explainText);
			product.setImageUrl(imageUrl);

			// リクエストスコープを作成
			request.setAttribute("product", product);

			// 商品登録確認画面へ遷移
			request.getRequestDispatcher("/jsp/product/update/product_update_check.jsp").forward(request, response);
		} else {
			// エラーがあれば、エラーメッセージリストをリクエストスコープに格納
			request.setAttribute("messageList", messageList);

			// サーブレット「/ProductUpdateInput」と同様の処理
			List<Category> categoryList = new ArrayList<Category>();
			categoryList = CategoryDao.findAll();
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("productId", productId);
			request.getRequestDispatcher("jsp/product/update/product_update_input.jsp").forward(request, response);
		}

	}
}
