package jp.co.sss.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.ecsite.bean.Secret;
import jp.co.sss.ecsite.connection.DBManager;

/**
 * 秘密の質問テーブルにアクセスし、操作を行うDAO
 */
public class SecretDao {

	/** 論理削除が0(未削除)のみ検索 */
	public static final String FIND_SECRET_ALL_SQL = "SELECT * FROM secret WHERE logical_delete_flag = 0 ORDER BY secret_id";
	/** 論理削除が0(未削除)かつそのIdの秘密の質問名を検索 */
	public static final String FIND_PRODUCT_BY_PROID_SQL = "SELECT secret_question FROM secret WHERE secret_id = ? AND logical_delete_flag = 0";

	/**
	 * 秘密の質問全件検索メソッド
	 *
	 * @return 秘密の質問テーブルのBeanのリスト
	 */
	public static List<Secret> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Secret> secretList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_SECRET_ALL_SQL);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				//オブジェクト生成
				Secret s = new Secret();

				//秘密の質問ID
				s.setSecretId(rs.getInt("secret_id"));

				//秘密の質問名
				s.setSecretQuestion(rs.getString("secret_question"));

				secretList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return secretList;
	}

	/**
	 * 秘密の質問ID検索用メソッド
	 *
	 * @param secretId 秘密の質問ID
	 * @return IDに該当する秘密の質問名
	 * 結果が0件、もしくは論理削除が1の場合空文字が返される。
	 */
	public static Secret findById(int secretId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Secret secret = new Secret();

		try {
			//DB接続
			con = DBManager.getConnection();
			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_BY_PROID_SQL);

			//秘密の質問IDから質問名をバインド
			ps.setInt(1, secretId);

			//SQL実行
			rs = ps.executeQuery();

			//結果をbeanに格納
			if (rs.next()) {
				//秘密の質問名
				secret.setSecretQuestion(rs.getString("secret_question"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return secret;
	}
}
