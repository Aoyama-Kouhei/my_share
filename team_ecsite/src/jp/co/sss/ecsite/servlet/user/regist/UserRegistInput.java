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

/**
 * Servlet implementation class UserRegistInput
 */
@WebServlet("/UserRegistInput")
public class UserRegistInput extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		List<Secret> secretList = new ArrayList<>();

		//セッションスコープからログイン時に使用したオブジェクトを取り出す。
		boolean userAuthority = false;
		if(session.getAttribute("loginUserAuthority")!= null) {
			userAuthority = (boolean) session.getAttribute("loginUserAuthority");
		}

		secretList = SecretDao.findAll();

		request.setAttribute("userAuthority",userAuthority);
		request.setAttribute("secretList",secretList);

		//前回サンプルの入力画面へフォワードする
		request.getRequestDispatcher("/jsp/user/regist/user_regist_input.jsp").forward(request,
				response);

	}
}
