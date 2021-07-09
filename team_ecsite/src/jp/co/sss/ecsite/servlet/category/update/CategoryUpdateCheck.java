package jp.co.sss.ecsite.servlet.category.update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * Servlet implementation class CategoryRegistCheck
 */
@WebServlet("/CategoryUpdateCheck")
public class CategoryUpdateCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");
		Category category = new Category();
		List<String> messageList = new ArrayList<>();
		messageList = ErrorMessage.checkCategoryRegist(categoryId, categoryName);
		System.out.println(messageList);
		if (ErrorMessage.error(messageList)) {
			category.setCategoryId(categoryId);
			category.setCategoryName(categoryName);

			request.setAttribute("category", category);
			request.getRequestDispatcher("/jsp/category/update/category_update_check.jsp").forward(request,
					response);
		} else {
			request.setAttribute("messageList", messageList);
			request.getRequestDispatcher("/jsp/category/update/category_update_input.jsp").forward(request,
					response);
		}

	}
}