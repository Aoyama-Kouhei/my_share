package jp.co.sss.ecsite.servlet.user.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.dao.UserDao;

/**
 * Servlet implementation class UserDeleteComplete
 */
@WebServlet("/UserDeleteComplete")
public class UserDeleteComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 削除対象のIDを受け取る
    	int userId = Integer.parseInt(request.getParameter("userId"));

    	// 削除対象情報の社員IDを利用して削除を実行する
    	UserDao.delete(userId);

    	// 削除完了画面に遷移
    	RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/user/delete/user_delete_complete.jsp");
    	dispatcher.forward(request, response);
	}

}

