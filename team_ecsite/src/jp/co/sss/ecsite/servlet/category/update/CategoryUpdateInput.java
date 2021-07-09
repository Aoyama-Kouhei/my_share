package jp.co.sss.ecsite.servlet.category.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;

/**
 * Servlet implementation class CategoryRegistInput
 */
@WebServlet("/CategoryUpdateInput")
public class CategoryUpdateInput extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");
		Category category = new Category();

		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);

		request.setAttribute("category", category);
		request.getRequestDispatcher("/jsp/category/update/category_update_input.jsp").forward(request, response);
	}
}