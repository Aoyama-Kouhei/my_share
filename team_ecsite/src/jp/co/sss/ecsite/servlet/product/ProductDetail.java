package jp.co.sss.ecsite.servlet.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.ProductDao;

/**
 * 社員変更入力画面から値を受け取り、入力チェックを実施後、
 * 社員変更確認画面へフォワードするサーブレット
 */
@WebServlet("/ProductDetail")
public class ProductDetail extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォームから値(hidden)を受け取る
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		//System.out.println(productId);
		Product product = new Product();
		
		product = ProductDao.findById(productId);

		// リクエストスコープを作成し、DTOを格納
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/product/product_detail.jsp").forward(request, response);
	}
}
