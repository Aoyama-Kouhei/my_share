package jp.co.sss.ecsite.servlet.category.regist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.dao.CategoryDao;

/**
 * Servlet implementation class CategoryRegistComplete
 */
@WebServlet("/CategoryRegistComplete")
public class CategoryRegistComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");
		Category category = new Category();

		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);

		CategoryDao.insert(category);
		request.getRequestDispatcher("/jsp/category/regist/category_regist_complete.jsp").forward(request, response);
	}
}