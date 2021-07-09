package jp.co.sss.ecsite.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.sss.ecsite.bean.Secret;
import jp.co.sss.ecsite.bean.User;
import jp.co.sss.ecsite.connection.DBManager;
import jp.co.sss.ecsite.util.Constant;
import jp.co.sss.ecsite.util.PasswordHash;

/**
 * 会員テーブルにアクセスし、操作を行うDAO
 */
public class UserDao {
	/** SQL文(全件検索) */
	public static final String FIND_USER_ALL_SQL = "SELECT user_id, email, pass, user_name, address, TO_CHAR(birthday, 'YYYY/MM/DD') AS birthday, secret_id, secret_answer, authority FROM ec_user WHERE logical_delete_flag = 0 ORDER BY user_id";
	/** SQL文(件数指定検索) */
	public static final String FIND_USER_LIMIT_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT * FROM ec_user WHERE logical_delete_flag = 0 ORDER BY user_id) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(ユーザーID指定検索) */
	public static final String FIND_USER_BY_USERID_SQL = "SELECT user_id, email, pass, user_name, address, TO_CHAR(birthday, 'YYYY/MM/DD') AS birthday, secret_id, secret_answer, authority FROM ec_user WHERE user_id = ? AND logical_delete_flag = 0";
	/** SQL文(ログインチェック) */
	public static final String LOGIN_CHECK_SQL = "SELECT * FROM ec_user WHERE email = ? AND pass = ? AND logical_delete_flag = 0";
	/** SQL文(秘密の質問でのログインチェック) */
	public static final String SECRET_LOGIN_CHECK_SQL = "SELECT * FROM ec_user WHERE email = ? AND secret_id = ? AND secret_answer = ? AND logical_delete_flag = 0";
	/** SQL文(登録) */
	public static final String INSERT_USER_SQL = "INSERT INTO ec_user VALUES (seq_user.nextval, ?, ?, ?, ?, ?, ?, ?, ?,0)";
	/** SQL文(編集) */
	public static final String UPDATE_USER_SQL = "UPDATE ec_user SET email = ?, pass = ?, user_name = ?, address = ?, birthday = ?, secret_id = ?, secret_answer = ?, authority = ? WHERE user_id = ?";
	/** SQL文(削除) */
	public static final String DELETE_USER_SQL = "UPDATE ec_user SET logical_delete_flag = 1 WHERE user_id = ?";

	/**
	 * 全件表示メソッド
	 *
	 * @return Userテーブルのリスト
	 */
	public static List<User> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_USER_ALL_SQL);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
				user.setBirthday(sdf.format(rs.getDate("birthday")));
				Secret secret = new Secret();
				secret.setSecretId(rs.getInt("secret_id"));
				// Secret型のtmpにfindByIdで得た結果を格納
				Secret tmp = new Secret();
				tmp = SecretDao.findById(rs.getInt("secret_id"));
				secret.setSecretQuestion(tmp.getSecretQuestion());
				user.setSecret(secret);
				user.setSecretAnswer(rs.getString("secret_answer"));
				user.setAuthority(rs.getInt("authority"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return userList;
	}

	/**
	 * ページング表示用会員検索メソッド
	 *
	 * @return ページ番号に対応した会員テーブルのBeanのリスト
	 */
	public static List<User> findLimit(int start, int end) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_USER_LIMIT_SQL);
			ps.setInt(1, start);
			ps.setInt(2, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				// yyyy-MM-dd形式
				SimpleDateFormat sdf1 = new SimpleDateFormat(Constant.DATE_FORMAT_GET);
				// yyyy/MM/dd形式
				SimpleDateFormat sdf2 = new SimpleDateFormat(Constant.DATE_FORMAT);
				// 誕生日を文字列で取得
				String dateString = rs.getString("birthday");
				Date date = null;
				try {
					// 誕生日文字列をyyyy-MM-dd形式で日付に変換
					date = sdf1.parse(dateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(date != null) {
				// 誕生日をyyyy/MM/dd形式で文字列に変換
				dateString = sdf2.format(date);
				}
				user.setBirthday(dateString);
				Secret secret = new Secret();
				secret.setSecretId(rs.getInt("secret_id"));
				// Secret型のtmpにfindByIdで得た結果を格納
				Secret tmp = new Secret();
				tmp = SecretDao.findById(rs.getInt("secret_id"));
				secret.setSecretQuestion(tmp.getSecretQuestion());
				user.setSecret(secret);
				user.setSecretAnswer(rs.getString("secret_answer"));
				user.setAuthority(rs.getInt("authority"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return userList;
	}

	/**
	 * ユーザーID検索用メソッド
	 *
	 * @param userId ユーザーID
	 * @return ユーザーテーブルのBean
	 * 結果が0件の場合nullが返される
	 */
	public static User findById(int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();

		try {
			//DB接続
			con = DBManager.getConnection();
			//SQL文作成
			ps = con.prepareStatement(FIND_USER_BY_USERID_SQL);

			//ユーザーIDを検索条件に設定
			ps.setInt(1, userId);

			//SQL実行
			rs = ps.executeQuery();

			//結果をbeanに格納
			if (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				user.setBirthday(rs.getString("birthday"));
				Secret secret = new Secret();
				secret.setSecretId(rs.getInt("secret_id"));
				// Secret型のtmpにfindByIdで得た結果を格納
				Secret tmp = new Secret();
				tmp = SecretDao.findById(rs.getInt("secret_id"));
				secret.setSecretQuestion(tmp.getSecretQuestion());
				user.setSecret(secret);
				user.setSecretAnswer(rs.getString("secret_answer"));
				user.setAuthority(rs.getInt("authority"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return user;
	}

	/**
	 * ログインチェックメソッド
	 *
	 * @param email メールアドレス
	 * @param password パスワード
	 * @return UserテーブルのBean,存在しない場合nullを返す
	 * @throws NoSuchAlgorithmException 暗号例外
	 */
	public static User loginCheck(String email, String password) throws NoSuchAlgorithmException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(LOGIN_CHECK_SQL);

			//ユーザーIDを検索条件に設定
			ps.setString(1, email);
			ps.setBytes(2, PasswordHash.passwordHash(password));

			//SQL実行
			rs = ps.executeQuery();

			//結果をbeanに格納
			if (rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				user.setBirthday(rs.getString("birthday"));
				Secret secret = new Secret();
				secret.setSecretId(rs.getInt("secret_id"));
				// Secret型のtmpにfindByIdで得た結果を格納
				Secret tmp = new Secret();
				tmp = SecretDao.findById(rs.getInt("secret_id"));
				secret.setSecretQuestion(tmp.getSecretQuestion());
				user.setSecret(secret);
				user.setSecretAnswer(rs.getString("secret_answer"));
				user.setAuthority(rs.getInt("authority"));
			}
		} catch (SQLException e) {
			user = null;
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return user;
	}

	/**
	 * 秘密の質問でのログインチェックメソッド
	 *
	 * @param email メールアドレス
	 * @param secretId 秘密の質問ID
	 * @param secretAnswer 秘密の質問回答
	 * @return UserテーブルのBean
	 */
	public static User secretLoginCheck(String email, int secretId, String secretAnswer) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(SECRET_LOGIN_CHECK_SQL);

			//ユーザーIDを検索条件に設定
			ps.setString(1, email);
			ps.setInt(2, secretId);
			ps.setString(3, secretAnswer);

			//SQL実行
			rs = ps.executeQuery();

			//結果をbeanに格納
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));
				user.setUserName(rs.getString("user_name"));
				user.setAddress(rs.getString("address"));
				user.setBirthday(rs.getString("birthday"));
				Secret secret = new Secret();
				secret.setSecretId(rs.getInt("secret_id"));
				// Secret型のtmpにfindByIdで得た結果を格納
				Secret tmp = new Secret();
				tmp = SecretDao.findById(rs.getInt("secret_id"));
				secret.setSecretQuestion(tmp.getSecretQuestion());
				user.setSecret(secret);
				user.setSecretAnswer(rs.getString("secret_answer"));
				user.setAuthority(rs.getInt("authority"));
			}

		} catch (SQLException e) {
			user = null;
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return user;
	}

	/**
	 * 会員登録メソッド
	 *
	 * @param user 会員情報
	 * @throws NoSuchAlgorithmException 暗号例外
	 */
	public static void insert(User user) throws NoSuchAlgorithmException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(INSERT_USER_SQL);

			//ユーザー情報をバインド
			ps.setString(1, user.getEmail());
			ps.setBytes(2, PasswordHash.passwordHash(user.getPassword()));
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getBirthday());

			// Secret型のtmpに引数から得たSecretを格納、質問番号を取り出す
			Secret tmp = new Secret();
			tmp = user.getSecret();
			ps.setInt(6, tmp.getSecretId());
			ps.setString(7, user.getSecretAnswer());
			ps.setInt(8, user.getAuthority());

			//SQL実行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con);
		}
	}

	/**
	 * 会員情報編集メソッド
	 *
	 * @param user 会員情報
	 * @throws NoSuchAlgorithmException 暗号例外
	 */
	public static void update(User user) throws NoSuchAlgorithmException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(UPDATE_USER_SQL);

			//商品IDを検索条件に設定
			ps.setInt(9, user.getUserId());
			ps.setString(1, user.getEmail());
			ps.setBytes(2, PasswordHash.passwordHash(user.getPassword()));
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getBirthday());

			// Secret型のtmpに引数から得たSecretを格納、質問番号を取り出す
			Secret tmp = new Secret();
			tmp = user.getSecret();
			ps.setInt(6, tmp.getSecretId());
			ps.setString(7, user.getSecretAnswer());
			ps.setInt(8, user.getAuthority());

			//SQL実行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con);
		}
	}

	/**
	 * 会員情報削除メソッド
	 *
	 * @param userId 削除するユーザーID
	 */
	public static void delete(int userId) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(DELETE_USER_SQL);

			//商品IDを検索条件に設定
			ps.setInt(1, userId);

			//SQL実行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con);
		}
	}
}
