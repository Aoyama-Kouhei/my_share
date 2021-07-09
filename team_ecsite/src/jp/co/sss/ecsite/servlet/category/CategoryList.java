package jp.co.sss.ecsite.servlet.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.dao.CategoryDao;

/**
 * Servlet implementation class CategoryList
 */
@WebServlet("/CategoryList")
public class CategoryList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = CategoryDao.findAll();

		// 10件ごとに表示する場合の、ページ数

				double pageNum = Math.ceil((double) categoryList.size() / 10);

		// ページ数をリクエストスコープに代入
		request.setAttribute("pageNum", pageNum);

		// ページ番号を取得
		if (!(request.getParameter("paging") == null)) {
			int paging = Integer.parseInt(request.getParameter("paging"));
			int start = (paging - 1) * 10 + 1;
			int end = paging * 10;
			categoryList = CategoryDao.findLimit(start, end);
			// 初回アクセス時
		} else {
			categoryList = CategoryDao.findLimit(1, 10);
		}
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/jsp/category/category_list.jsp").forward(request,
				response);
	}

}
