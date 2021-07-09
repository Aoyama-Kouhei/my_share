package jp.co.sss.ecsite.servlet.product.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.dao.ProductDao;


/**
 * 商品登録入力画面から値を受け取り、入力チェックを実施後、
 * 商品登録確認画面へフォワードするサーブレット
 */
@WebServlet("/ProductDeleteComplete")
public class ProductDeleteComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームから値を受け取る
		int productId = Integer.parseInt(request.getParameter("productId"));

		// DAOを呼び出してレコード削除　
		ProductDao.delete(productId);

		// 商品削除完了画面へ遷移
		request.getRequestDispatcher("/jsp/product/delete/product_delete_complete.jsp").forward(request, response);
	}
}
