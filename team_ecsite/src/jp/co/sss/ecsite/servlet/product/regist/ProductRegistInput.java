package jp.co.sss.ecsite.servlet.product.regist;

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
@WebServlet("/ProductRegistInput")
public class ProductRegistInput extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList = CategoryDao.findAll();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("jsp/product/regist/product_regist_input.jsp").forward(request, response);
	}

}
