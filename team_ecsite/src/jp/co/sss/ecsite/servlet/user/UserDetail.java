package jp.co.sss.ecsite.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.UserDao;


/**
 * Servlet implementation class UserDetail
 */
@WebServlet("/UserDetail")
public class UserDetail extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//受け取ったuserIDを格納
		int userId = Integer.parseInt(request.getParameter("userId"));

		//IDから社員を検索
		User user = UserDao.findById(userId);

		//userデータを渡す
		request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/user/user_detail.jsp").forward(request,response);

	}

}

