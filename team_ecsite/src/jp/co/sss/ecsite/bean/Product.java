package jp.co.sss.ecsite.bean;

/**
 * 商品テーブルの情報を格納するDTO
 */
public class Product {
	/** 商品ID */
	private int productId;
	/** 商品名 */
	private String productName;
	/** 価格 */
	private int price;
	/** カテゴリオブジェクト */
	private Category category;
	/** 説明文 */
	private String explainText;
	/** 画像URL */
	private String imageUrl;

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
	 * @return category カテゴリオブジェクト
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category カテゴリオブジェクト
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return explainText 説明文
	 */
	public String getExplainText() {
		return explainText;
	}
	/**
	 * @param explainText 説明文
	 */
	public void setExplainText(String explainText) {
		this.explainText = explainText;
	}
	/**
	 * @return imageUrl 画像URL
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl 画像URL
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
