package jp.co.sss.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.connection.DBManager;

/**
 * カテゴリテーブルにアクセスし、操作を行うDAO
 */
public class CategoryDao {
	/** SQL文(全件検索) */
	private static final String FIND_CATEGORY_ALL_SQL = "SELECT * FROM category WHERE logical_delete_flag = 0 ORDER BY category_id";
	/** SQL文(件数指定検索) */
	private static final String FIND_CATEGORY_LIMIT_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT * FROM category WHERE logical_delete_flag = 0 ORDER BY category_id) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(登録) */
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO category(category_id,category_name) VALUES (?, ?)";
	/** SQL文(更新) */
	private static final String UPDATE_CATEGORY_SQL = "UPDATE category SET category_name = ? WHERE category_id = ?";
	/** SQL文(削除) */
	private static final String DELETE_CATEGORY_SQL = "UPDATE category SET logical_delete_flag = 1 WHERE category_id = ?";

	/**
	 * 全件表示メソッド
	 *
	 * @return CategoryテーブルのBean
	 */
	public static List<Category> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> CategoryList = new ArrayList<Category>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_CATEGORY_ALL_SQL);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				Category c = new Category();
				c.setCategoryId(rs.getString("category_id"));
				c.setCategoryName(rs.getString("category_name"));
				CategoryList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return CategoryList;
	}

	/**
	 * ページング表示用カテゴリ検索メソッド
	 *
	 * @return ページ番号に対応したカテゴリテーブルのBeanのリスト
	 */
	public static List<Category> findLimit(int start, int end) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Category> CategoryList = new ArrayList<Category>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_CATEGORY_LIMIT_SQL);
			ps.setInt(1, start);
			ps.setInt(2, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				Category c = new Category();
				c.setCategoryId(rs.getString("category_id"));
				c.setCategoryName(rs.getString("category_name"));
				CategoryList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con ,rs);
		}
		return CategoryList;
	}

	/**
	 * カテゴリ登録メソッド
	 *
	 * @param category カテゴリ情報
	 */
	public static void insert(Category category) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(INSERT_CATEGORY_SQL);

			//カテゴリ情報をバインド
			ps.setString(1, category.getCategoryId());
			ps.setString(2, category.getCategoryName());

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
	 * カテゴリ情報編集メソッド
	 *
	 * @param category カテゴリ情報
	 */
	public static void update(Category category) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(UPDATE_CATEGORY_SQL);

			//商品IDを検索条件に設定
			ps.setString(2, category.getCategoryId());
			ps.setString(1, category.getCategoryName());

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
	 * カテゴリ情報削除メソッド
	 * @param categoryId カテゴリID
	 */
	public static void delete(String categoryId) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(DELETE_CATEGORY_SQL);

			//商品IDを検索条件に設定
			ps.setString(1, categoryId);

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
