package jp.co.sss.ecsite.servlet.user.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.UserDao;

/**
 * Servlet implementation class UserUpdateInput
 */
@WebServlet("/UserUpdateInput")
public class UserUpdateInput extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		boolean userAuthority = false;
		if(session.getAttribute("loginUserAuthority")!= null) {
			userAuthority = (boolean) session.getAttribute("loginUserAuthority");
		}
		//受け取ったuserIDを格納
		int userId = Integer.parseInt(request.getParameter("userId"));

		//IDから社員を検索
		User user = UserDao.findById(userId);

		//userデータを渡す
		request.setAttribute("userAuthority", userAuthority);
		request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/user/update/user_update_input.jsp").forward(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//boolean userAuthority = (boolean) session.getAttribute("loginUserAuthority");

		//受け取ったuserIDを格納
		int userId = (int) session.getAttribute("loginUserId");

		//IDから社員を検索
		User user = UserDao.findById(userId);

		//userデータを渡す
		//request.setAttribute("userAuthority",userAuthority);
		request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/user/update/user_update_input.jsp").forward(request,response);
	}

}
