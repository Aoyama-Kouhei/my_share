package jp.co.sss.ecsite.bean;

/**
 * 注文履歴テーブルの情報を格納するDTO
 */
public class History {
	/** 注文履歴ID */
	private int historyId;
	/** 注文ID */
	private int orderId;
	/** 商品ID */
	private int productId;
	/** 商品名
	 * 注文機能時のみ時利用 */
	private String productName;
	/** 価格
	 * 注文機能時のみ時利用 */
	private int price;
	/** 個数 */
	private int quantity;
	/**
	 * @return historyId 注文履歴ID
	 */
	public int getHistoryId() {
		return historyId;
	}
	/**
	 * @param historyId 注文履歴ID
	 */
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
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
	 * @return productId 商品ID
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId 商品ID
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return productName 商品名
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return price 価格
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price 価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return quantity 個数
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity 個数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
