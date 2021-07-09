package jp.co.sss.ecsite.servlet.user.regist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sss.ecsite.bean.Secret;
import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.dao.SecretDao;
import jp.co.sss.ecsite.dao.UserDao;
import jp.co.sss.ecsite.util.ErrorMessage;

/**
 * Servlet implementation class UserRegistComplete
 */
@WebServlet("/UserRegistComplete")
public class UserRegistComplete extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			boolean userAuthority = true;
			if(session.getAttribute("loginUserAuthority")!= null) {
				userAuthority = (boolean) session.getAttribute("loginUserAuthority");
			}

			String mail = request.getParameter("email");
			String password2 = request.getParameter("password2");
			String name = request.getParameter("userName");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String question = request.getParameter("secretId");
			String answer = request.getParameter("secretAnswer");
			String authority = request.getParameter("authority");

			User user = new User();
			Secret secret = new Secret();

			user.setAddress(address);
//			if(!authority.equals("")) {
//				user.setAuthority(Integer.parseInt(authority));
//			}else {
//				//一般で権限に入力がなかった場合
//				authority = "2";
//				user.setAuthority(Integer.parseInt(authority));
//			}
			user.setAuthority(Integer.parseInt(authority));
			user.setBirthday(birthday);
			user.setEmail(mail);
			user.setPassword(password2);
			user.setUserName(name);
			int questionNum = Integer.parseInt(question);

			secret = SecretDao.findById(questionNum);
			secret.setSecretId(questionNum);
			user.setSecret(secret);

			user.setSecretAnswer(answer);

			UserDao.insert(user);

			String message = ErrorMessage.registComplete("会員");
			request.setAttribute("registMessage", message);
			request.setAttribute("userAuthority", userAuthority);

			request.getRequestDispatcher("/jsp/user/regist/user_regist_complete.jsp").forward(request,
					response);
		} catch (Exception e) {
			System.out.println("システムエラーが発生しました");
			e.printStackTrace();
		}
	}

}
