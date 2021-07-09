package jp.co.sss.ecsite.bean;

/**
 * 注文テーブルの情報を格納するDTO
 */
public class Order {
	/** 注文ID */
	private int orderId;
	/** 会員ID */
	private int userId;
	/** 注文日時 */
	private String orderDate;
	/** 支払方法 */
	private int payment;
	/**
	 * @return orderId 注文ID
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId 注文ID
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return userId 会員ID
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId 会員ID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return orderDate 注文日時
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate 注文日時
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return payment 支払方法
	 */
	public int getPayment() {
		return payment;
	}
	/**
	 * @param payment 支払方法
	 */
	public void setPayment(int payment) {
		this.payment = payment;
	}


}
