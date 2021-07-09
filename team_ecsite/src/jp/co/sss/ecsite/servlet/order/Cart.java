package jp.co.sss.ecsite.servlet.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.ProductDao;
import jp.co.sss.ecsite.util.OrderUtil;

@WebServlet("/Cart")
public class Cart extends HttpServlet {

	@SuppressWarnings("unchecked")

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String str = request.getParameter("productId");

		//注文リストが残っている場合一旦削除
		if(session.getAttribute("orderList") != null) {
			session.removeAttribute("orderList");
			session.removeAttribute("orderSumPrice");
		}
		//商品IDがなしの場合以下の処理をスキップ
		if (!str.equals("null")) {

			//商品IDをint変換
			int productId = Integer.parseInt(str);

			Product p = new Product();
			//商品情報取得
			p = ProductDao.findById(productId);
			//買い物かごリスト作成
			List<Product> cartList = new ArrayList<Product>();

			//買い物かごリストが空でない場合
			if (session.getAttribute("cartList") != null) {
				cartList = (List<Product>) session.getAttribute("cartList");

				//買い物かご内商品が重複していない場合
				if (!OrderUtil.cartInputCheck(cartList, p)) {
					//買い物かごリストへ商品追加
					cartList.add(OrderUtil.OmitExplain(p));
				}
			} else {
				//買い物かごリストが空の場合
				//買い物かごリストへ商品追加
				cartList.add(OrderUtil.OmitExplain(p));
			}
			//買い物かごリストをセッションへ追加
			session.setAttribute("cartList", cartList);
		}
		request.getRequestDispatcher("/jsp/order/cart.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ヘッダからカートへ遷移
		request.getRequestDispatcher("/jsp/order/cart.jsp").forward(request, response);
	}

}
