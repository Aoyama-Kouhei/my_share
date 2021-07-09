package jp.co.sss.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.connection.DBManager;

/**
 * 注文履歴テーブルにアクセスし、操作を行うDAO
 */
public class HistoryDao {
	/** SQL文(注文ID指定全件検索) */
	private static final String FIND_HISTORY_SQL = "SELECT history_id,h.product_id AS product_id, product_name, quantity, price * quantity AS price FROM history h INNER JOIN product p ON p.product_id = h.product_id WHERE order_id = ? AND h.logical_delete_flag = 0";
	/** SQL文(登録) */
	public static final String INSERT_HISTORY_SQL = "INSERT INTO history (history_id, order_id, product_id, quantity) VALUES(seq_history.nextval, seq_order.currval, ?, ?)";
	/** SQL文(更新) */
	private static final String UPDATE_HISTORY_SQL = "UPDATE history SET order_id = ?, product_id = ? , quantity = ? WHERE history_id = ?";
	/** SQL文(削除) */
	private static final String DELETE_HISTORY_SQL = "UPDATE history SET logical_delete_flag = 1 WHERE history_id = ?";

	/**
	 * 指定された注文IDと一致する注文履歴を全件検索
	 *
	 * @param orderId 注文ID
	 * @return 注文履歴リスト
	 */
	public static List<History> findHistoryByOrderId(int orderId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<History> historyList = new ArrayList<>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_HISTORY_SQL);

			//バインド
			ps.setInt(1, orderId);

			//SQL実行
			rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				History hist = new History();
				hist.setHistoryId(rs.getInt("history_id"));
				hist.setProductId(rs.getInt("product_id"));
				hist.setProductName(rs.getString("product_name"));
				hist.setQuantity(rs.getInt("quantity"));
				hist.setPrice(rs.getInt("price"));
				historyList.add(hist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con, rs);
		}
		return historyList;
	}

	/**
	 * 注文履歴登録メソッド
	 *
	 * @param history 注文履歴
	 */
	public static void insert(History history) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(INSERT_HISTORY_SQL);

			//バインド
			ps.setInt(1, history.getProductId());
			ps.setInt(2, history.getQuantity());

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
	 * 注文履歴情報編集メソッド
	 *
	 * @param history 注文履歴
	 */
	public static void update(History history) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();
			//SQL文作成
			ps = con.prepareStatement(UPDATE_HISTORY_SQL);

			History h = new History();
			ps.setInt(1, h.getOrderId());
			ps.setInt(2, h.getProductId());
			ps.setInt(3, h.getQuantity());
			ps.setInt(4, h.getHistoryId());

			//SQL実行
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(ps, con);
		}
	}

	/**
	 * 注文履歴情報削除メソッド
	 *
	 * @param orderId 削除する注文履歴ID
	 */
	public static void delete(int historyId) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(DELETE_HISTORY_SQL);

			//注文IDを検索条件に設定
			ps.setInt(1, historyId);

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
