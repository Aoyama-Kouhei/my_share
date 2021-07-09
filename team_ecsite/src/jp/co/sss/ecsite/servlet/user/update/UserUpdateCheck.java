package jp.co.sss.ecsite.servlet.user.update;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.Secret;
import jp.co.sss.ecsite.dao.SecretDao;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * Servlet implementation class UserUpdateCheck
 */
@WebServlet("/UserUpdateCheck")
public class UserUpdateCheck extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		boolean userAuthority = false;
		if(session.getAttribute("loginUserAuthority")!= null) {
			userAuthority = (boolean) session.getAttribute("loginUserAuthority");
		}
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String userName = request.getParameter("userName");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		String authority = request.getParameter("authority");
		String secretId = request.getParameter("secretId");;
		String secretAnswer = request.getParameter("secretAnswer");;

		List<String> messageList = new ArrayList<>();
		messageList = ErrorMessage.checkUserUpdate(email,password1,password2,userName,address,birthday);

		if(ErrorMessage.error(messageList)) {
			request.setAttribute("userId", userId);
			request.setAttribute("email", email);
			request.setAttribute("password1", password1);
			request.setAttribute("password2", password2);
			request.setAttribute("userName", userName);
			request.setAttribute("birthday", birthday);
			request.setAttribute("address", address);

			request.setAttribute("secretId", secretId);
			request.setAttribute("secretAnswer", secretAnswer);

			request.setAttribute("authority", authority);
			request.setAttribute("userAuthority", userAuthority);
			request.getRequestDispatcher("/jsp/user/update/user_update_check.jsp").forward(request,
					response);
		}else {
			List<Secret> secretList = new ArrayList<>();
			secretList = SecretDao.findAll();
			request.setAttribute("secretList",secretList);
			request.setAttribute("messageList", messageList);

			request.getRequestDispatcher("/jsp/user/update/user_update_input.jsp").forward(request,
					response);
		}
	}

}