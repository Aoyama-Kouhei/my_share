package jp.co.sss.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.bean.Order;
import jp.co.sss.ecsite.connection.DBManager;
import jp.co.sss.ecsite.util.Constant;

/**
 * 注文テーブルにアクセスし、操作を行うDAO
 */
public class OrderDao {
	/** SQL文(全件検索) */
	public static final String FIND_ORDER_ALL_SQL = "SELECT * FROM ec_order WHERE logical_delete_flag = 0 ORDER BY order_id ";
	/** SQL文(注文ID指定検索) */
	public static final String FIND_ORDER_BY_ORDERID_SQL = "SELECT * FROM ec_order WHERE order_id = ? AND logical_delete_flag = 0";
	/** SQL文(件数指定検索) */
	public static final String FIND_ORDER_LIMIT_SQL = "SELECT * FROM ( SELECT ROWNUM rownumber, t.* FROM (SELECT * FROM ec_order WHERE logical_delete_flag = 0 ORDER BY order_id) t ) WHERE rownumber BETWEEN ? AND ?";
	/** SQL文(登録) */
	public static final String INSERT_ORDER_SQL = "INSERT INTO ec_order (order_id, user_id, order_date, payment) VALUES(seq_order.nextval, ?, ?, ?)";
	/** SQL文(更新) */
	public static final String UPDATE_ORDER_SQL = "UPDATE ec_order SET user_id = ?, order_id = ? , payment = ?, logical_delete_flag = ? WHERE order_id = ?";
	/** SQL文(削除) */
	public static final String DELETE_ORDER_SQL = "UPDATE ec_order SET logical_delete_flag = 1 WHERE order_id = ?";
	/** SQL文(注文履歴削除) */
	public static final String DELETE_HISTORY_BY_ORDERID_SQL = "UPDATE history SET logical_delete_flag = 1 WHERE order_id = ?";
	public static final String INSERT_HISTORY_SQL = "INSERT INTO history (history_id, order_id, product_id, quantity) VALUES(seq_history.nextval, seq_order.currval, ?, ?)";

	/**
	 * 注文全件検索メソッド
	 *
	 * @return 注文テーブルのBeanのリスト
	 */
	public static List<Order> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		List<Order> orderList = new ArrayList<Order>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_ORDER_ALL_SQL);

			//SQL実行
			ResultSet rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				Order o = new Order();
				o.setOrderId(rs.getInt("order_id"));
				o.setUserId(rs.getInt("user_id"));
				SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
				o.setOrderDate(sdf.format(rs.getDate("order_date")));
				o.setPayment(rs.getInt("payment"));
				orderList.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con);
		}
		return orderList;
	}
	
	/**
	 * ページング表示用注文検索メソッド
	 *
	 * @return ページ番号に対応した注文テーブルのBeanのリスト
	 */
	public static List<Order> findLimit(int start, int end) {
		Connection con = null;
		PreparedStatement ps = null;
		List<Order> orderList = new ArrayList<Order>();

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_ORDER_LIMIT_SQL);
			ps.setInt(1, start);
			ps.setInt(2, end);

			//SQL実行
			ResultSet rs = ps.executeQuery();

			//結果をリスト型のbeanに格納
			while (rs.next()) {
				Order o = new Order();
				o.setOrderId(rs.getInt("order_id"));
				o.setUserId(rs.getInt("user_id"));
				SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
				o.setOrderDate(sdf.format(rs.getDate("order_date")));
				o.setPayment(rs.getInt("payment"));
				orderList.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con);
		}
		return orderList;
	}
	
	/**
	 * 注文ID検索メソッド
	 *
	 * @param orderId 注文ID
	 * @return 注文テーブルのBean、結果が0件の場合にはnull
	 */
	public static Order findById(int orderId) {
		Order order = new Order();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(FIND_ORDER_BY_ORDERID_SQL);

			//商品IDを検索条件に設定
			ps.setInt(1, orderId);

			//SQL実行
			ResultSet rs = ps.executeQuery();

			//結果をbeanに格納
			if (rs.next()) {
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
				order.setOrderDate(sdf.format(rs.getDate("order_date")));
				order.setPayment(rs.getInt("payment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps, con);
		}
		return order;
	}

	/**
	 * 注文登録メソッド
	 *
	 * @param order 注文情報
	 */
	public static void insert(Order order) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			ps = con.prepareStatement(INSERT_ORDER_SQL);

			//バインド
			ps.setInt(1, order.getUserId());
			ps.setString(2, order.getOrderDate());
			ps.setInt(3, order.getPayment());

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
	 * 注文情報と、その履歴の削除メソッド
	 *
	 * @param orderId 削除する注文ID
	 */
	public static void delete(int orderId) {
		Connection con = null;
		PreparedStatement psOrder = null;
		PreparedStatement psHistory = null;

		try {
			//DB接続
			con = DBManager.getConnection();

			//SQL文作成
			psOrder = con.prepareStatement(DELETE_ORDER_SQL);
			psHistory = con.prepareStatement(DELETE_HISTORY_BY_ORDERID_SQL);

			//注文IDを検索条件に設定
			psOrder.setInt(1, orderId);
			psHistory.setInt(1, orderId);

			//SQL実行
			psOrder.executeUpdate();
			psHistory.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(psOrder, con);
			DBManager.close(psHistory, con);
		}
	}

	public static void insert(Order order, List<History> list) {
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;


		try {
			//DB接続
			con = DBManager.getConnection();
			con.setAutoCommit(false);

			//SQL文作成
			ps1 = con.prepareStatement(INSERT_ORDER_SQL);

			//バインド
			ps1.setInt(1, order.getUserId());
			ps1.setString(2, order.getOrderDate());
			ps1.setInt(3, order.getPayment());

			ps1.executeUpdate();

			ps2 = con.prepareStatement(INSERT_HISTORY_SQL);


			int listCnt = list.size();
			int cnt = 0;
			for (History h : list) {
			    ps2.setInt(1, h.getProductId());
			    ps2.setInt(2, h.getQuantity());
			    ps2.addBatch();
			    cnt++;
			    if (cnt == listCnt) {
			        ps2.executeBatch();
			    }
			}

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB切断
			DBManager.close(ps1);
			DBManager.close(ps2, con);
		}
	}
}
