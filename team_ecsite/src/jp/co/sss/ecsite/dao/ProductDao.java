package jp.co.sss.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.connection.DBManager;

/**
 * 商品テーブルにアクセスし、操作を行うDAO
 */
public class ProductDao {
	/** SQL文(全件検索) */
	public static final String FIND_PRODUCT_ALL_SQL = "SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name  FROM product p, category c WHERE p.logical_delete_flag = 0 AND p.category_id = c.category_id ORDER BY p.product_id";
	/** SQL文(件数指定検索) */
	public static final String FIND_PRODUCT_LIMIT_ALL_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM ( SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name    AS category_name FROM product   p INNER JOIN category  c ON p.logical_delete_flag = 0 AND p.category_id = c.category_id ORDER BY p.product_id ) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(新着件数指定検索) */
	public static final String FIND_PRODUCT_LIMIT_ALL_PROID_DESC_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name  FROM product p, category c WHERE p.logical_delete_flag = 0 AND p.category_id = c.category_id ORDER BY p.product_id DESC) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(カテゴリ件数指定検索) */
	public static final String FIND_PRODUCT_LIMIT_BY_CATID_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name FROM product p, category c  WHERE p.category_id = c.category_id  AND p.category_id = ?  AND p.logical_delete_flag = 0) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(価格帯件数指定検索) */
	public static final String FIND_PRODUCT_LIMIT_BY_PRICE_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name FROM product p, category c WHERE  p.category_id = c.category_id AND p.logical_delete_flag = 0  AND p.price BETWEEN ? AND ?) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(ランキング件数指定検索) */
	public static final String FIND_PRODUCT_LIMIT_BY_RANK_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT l.product_id, l.product_name, l.price, l.category_id AS category_id, l.explain_text, l.image_url, c.category_name AS category_name FROM (SELECT p.product_id, p.product_name, p.price, p.category_id AS category_id, p.explain_text, p.image_url FROM product p RIGHT OUTER JOIN (SELECT product_id, SUM(quantity) AS quantity FROM history GROUP BY  product_id ) m ON p.product_id = m.product_id WHERE p.logical_delete_flag = 0 ORDER BY m.quantity DESC) l LEFT OUTER JOIN category c ON l.category_id = c.category_id) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(新着順検索) */
	public static final String FIND_PRODUCT_ALL_PROID_DESC_SQL = "SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name  FROM product p, category c WHERE p.logical_delete_flag = 0 AND p.category_id = c.category_id ORDER BY p.product_id DESC";
	/** SQL文(カテゴリID指定検索) */
	public static final String FIND_PRODUCT_BY_CATID_SQL = "SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name FROM product p, category c  WHERE p.category_id = c.category_id  AND p.category_id = ?  AND p.logical_delete_flag = 0";
	/** SQL文(価格帯指定検索) */
	public static final String FIND_PRODUCT_BY_PRICE_SQL = "SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name FROM product p, category c WHERE  p.category_id = c.category_id AND p.logical_delete_flag = 0  AND p.price BETWEEN ? AND ?";
	/** SQL文(ランキング検索) */
	public static final String FIND_PRODUCT_BY_RANK_SQL = "SELECT l.product_id, l.product_name, l.price, l.category_id AS category_id, l.explain_text, l.image_url, c.category_name AS category_name FROM (SELECT p.product_id, p.product_name, p.price, p.category_id AS category_id, p.explain_text, p.image_url FROM product p RIGHT OUTER JOIN (SELECT product_id, SUM(quantity) AS quantity FROM history GROUP BY  product_id ) m ON p.product_id = m.product_id WHERE p.logical_delete_flag = 0 ORDER BY m.quantity DESC) l LEFT OUTER JOIN category c ON l.category_id = c.category_id";
	/** SQL文(商品ID指定検索) */
	public static final String FIND_PRODUCT_BY_PROID_SQL = "SELECT product_id, product_name, price, p.category_id AS category_id, explain_text, image_url, c.category_name AS category_name FROM product p, category c WHERE p.product_id = ? AND p.logical_delete_flag = 0";
	/** SQL文(登録) */
	public static final String INSERT_PRODUCT_SQL = "INSERT INTO product(product_id, product_name, price, category_id, explain_text, image_url) VALUES(seq_product.nextval,?, ?, ?, ?, ?)";
	/** SQL文(更新) */
	public static final String UPDATE_PRODUCT_SQL = "UPDATE product SET product_name = ?, price = ?, category_id = ?, explain_text = ?, image_url = ? WHERE product_id = ?";
	/** SQL文(削除) */
	public static final String DELETE_PRODUCT_SQL = "UPDATE product SET logical_delete_flag = 1 WHERE product_id = ?";
	/** SQL文(上位5件検索) */
	public static final String FIND_PRODUCT_TOP_5 = "SELECT product_id, product_name, price, image_url FROM(SELECT l.product_id, l.product_name, l.price, l.category_id AS category_id, l.explain_text, l.image_url, c.category_name AS category_name FROM (SELECT p.product_id, p.product_name, p.price, p.category_id AS category_id, p.explain_text, p.image_url FROM product p RIGHT OUTER JOIN (SELECT product_id, SUM(quantity) AS quantity FROM history  GROUP BY  product_id  ) m ON p.product_id = m.product_id WHERE p.logical_delete_flag = 0 ORDER BY m.quantity DESC) l LEFT OUTER JOIN category c ON l.category_id = c.category_id) WHERE rownum <= 5";

	/**
	 * 商品全件検索メソッド
	 *
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findAll(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_ALL_SQL);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * ページング用商品全件検索メソッド
	 *
	 * @return ページ番号に対応した商品テーブルのBeanのリスト
	 */
	public static List<Product> findLimit(int start, int end){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_LIMIT_ALL_SQL);

			// ページング用のプレースホルダに値を代入
			ps.setInt(1,start);
			ps.setInt(2, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * 商品新着順全件検索メソッド
	 *
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findAllDesc(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_ALL_PROID_DESC_SQL);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * ページング用商品新着順全件検索メソッド
	 *
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findAllDesc(int start, int end){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_LIMIT_ALL_PROID_DESC_SQL);

			// ページング用のプレースホルダに値を代入
			ps.setInt(1, start);
			ps.setInt(2, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * カテゴリ検索用メソッド
	 *
	 * @param categoryId カテゴリID
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findByCategory(String categoryId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_BY_CATID_SQL);

			//カテゴリIDを検索条件に設定
			ps.setString(1, categoryId);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * ページング用カテゴリ検索用メソッド
	 *
	 * @param categoryId カテゴリID
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findByCategory(String categoryId, int start, int end) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_LIMIT_BY_CATID_SQL);

			//カテゴリIDを検索条件に設定
			ps.setString(1, categoryId);

			// ページング用のプレースホルダに値を代入
			ps.setInt(2, start);
			ps.setInt(3, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * 価格検索用メソッド
	 *
	 * @param min 最低金額
	 * @param max 最高金額
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findByPrice(String min, String max) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_BY_PRICE_SQL);

			//最低金額を検索条件に設定
			ps.setString(1, min);

			//最高金額を検索条件に設定
			ps.setString(2, max);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * ページング用価格検索用メソッド
	 *
	 * @param min 最低金額
	 * @param max 最高金額
	 * @return 商品テーブルのBeanのリスト
	 */
	public static List<Product> findByPrice(String min, String max, int start, int end) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_LIMIT_BY_PRICE_SQL);

			//最低金額を検索条件に設定
			ps.setString(1, min);

			//最高金額を検索条件に設定
			ps.setString(2, max);

			// ページング用のプレースホルダに値を代入
			ps.setInt(3, start);
			ps.setInt(4, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * 売れ筋検索メソッド
	 *
	 * @return 商品リスト
	 */
	public static List<Product> findByRank() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_BY_RANK_SQL);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * ページング用売れ筋検索メソッド
	 *
	 * @return 商品リスト
	 */
	public static List<Product> findByRank(int start, int end) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_LIMIT_BY_RANK_SQL);

			// ページング用のプレースホルダに値を代入
			ps.setInt(1, start);
			ps.setInt(2, end);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}

	/**
	 * 商品ID検索用メソッド
	 *
	 * @param proId 商品ID
	 * @return 商品テーブルのBean
	 * 結果が0件の場合nullが返される。
	 */
	public static Product findById(int proId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = new Product();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_BY_PROID_SQL);

			ps.setInt(1, proId);

			//SQL実行
			rs = ps.executeQuery();

			//結果をbeanに格納
			if(rs.next()) {
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setExplainText(rs.getString("explain_text"));
				product.setImageUrl(rs.getString("image_url"));
				Category category = new Category();
				category.setCategoryId(rs.getString("category_id"));
				category.setCategoryName(rs.getString("category_name"));
				product.setCategory(category);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return product;
	}

	/**
	 * 商品登録メソッド
	 *
	 * @param pro 商品オブジェクト
	 */
	public static void insert(Product pro) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(INSERT_PRODUCT_SQL);

			//バインド
			ps.setString(1, pro.getProductName());
			ps.setInt(2, pro.getPrice());
			ps.setString(3, pro.getCategory().getCategoryId());
			ps.setString(4, pro.getExplainText());
			ps.setString(5, pro.getImageUrl());

			//SQL実行
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con);
		}
	}

	/**
	 * 商品編集メソッド
	 *
	 * @param pro 商品
	 */
	public static void update(Product pro) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(UPDATE_PRODUCT_SQL);

			//バインド
			ps.setString(1, pro.getProductName());
			ps.setInt(2, pro.getPrice());
			ps.setString(3, pro.getCategory().getCategoryId());
			ps.setString(4, pro.getExplainText());
			ps.setString(5, pro.getImageUrl());
			ps.setInt(6, pro.getProductId());

			//SQL実行
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con);
		}
	}

	/**
	 * 商品削除メソッド
	 *
	 * @param proId 商品ID
	 */
	public static void delete(int proId) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(DELETE_PRODUCT_SQL);

			//バインド
			ps.setInt(1, proId);

			//SQL実行
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con);
		}
	}

	/**
	 * 売れ筋上位5つの商品を検索するメソッド
	 *
	 * @return 商品リスト
	 */
	public static List<Product> findByRankTop5() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> productList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_PRODUCT_TOP_5);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while(rs.next()) {
				Product product = new Product();
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setImageUrl(rs.getString("image_url"));
				productList.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return productList;
	}
}
