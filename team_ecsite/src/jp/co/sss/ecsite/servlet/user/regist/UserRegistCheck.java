package jp.co.sss.ecsite.servlet.user.regist;

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
 * Servlet implementation class UserRegistCheck
 */
@WebServlet("/UserRegistCheck")
public class UserRegistCheck extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean userAuthority = false;
		if(session.getAttribute("loginUserAuthority")!= null) {
			userAuthority = (boolean) session.getAttribute("loginUserAuthority");
		}
		String mail = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("userName");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		String question = request.getParameter("secretId");
		String answer = request.getParameter("secretAnswer");
		String authority = request.getParameter("authority");

		Secret secret = new Secret();

		List<String> messageList = new ArrayList<>();
		messageList = ErrorMessage.checkUser(mail,password1,password2,name,address,birthday,answer);

		if(ErrorMessage.error(messageList)) {
			request.setAttribute("email", mail);
			request.setAttribute("password1", password1);
			request.setAttribute("password2", password2);
			request.setAttribute("userName", name);
			request.setAttribute("birthday", birthday);
			request.setAttribute("address", address);

			secret = SecretDao.findById(Integer.parseInt(question));
			secret.setSecretId(Integer.parseInt(question));
			request.setAttribute("secretId", secret);

			request.setAttribute("secretAnswer", answer);
			request.setAttribute("authority", authority);
			request.setAttribute("userAuthority", userAuthority);
			request.getRequestDispatcher("/jsp/user/regist/user_regist_check.jsp").forward(request,
					response);
		}else {
			List<Secret> secretList = new ArrayList<>();
			secretList = SecretDao.findAll();
			request.setAttribute("secretList",secretList);
			request.setAttribute("messageList", messageList);
			request.setAttribute("userAuthority", userAuthority);

			System.out.println(messageList);
			request.getRequestDispatcher("/jsp/user/regist/user_regist_input.jsp").forward(request,
					response);
		}
	}
}
