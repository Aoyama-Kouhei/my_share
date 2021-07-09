package jp.co.sss.ecsite.servlet.category.delete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.dao.CategoryDao;

/**
 * Servlet implementation class CategoryDeleteComplete
 */
@WebServlet("/CategoryDeleteComplete")
public class CategoryDeleteComplete extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");


		CategoryDao.delete(categoryId);
		request.getRequestDispatcher("/jsp/category/delete/category_delete_complete.jsp").forward(request, response);
	}
}