package jp.co.sss.ecsite.bean;

/**
 * 買い物かごの中身テーブルの情報を格納するDTO
 */
public class CartContent {
	/**
	 * 買い物かごの中身ID
	 */
	private int cartContentId;
	/**
	 * 買い物かご情報
	 */
	private Cart cart;
	/**
	 * 商品情報
	 */
	private Product product;
	/**
	 * 個数
	 */
	private int quantity;
	/**
	 * @return cartContentId
	 */
	public int getCartContentId() {
		return cartContentId;
	}
	/**
	 * @param cartContentId セットする cartContentId
	 */
	public void setCartContentId(int cartContentId) {
		this.cartContentId = cartContentId;
	}
	/**
	 * @return cart
	 */
	public Cart getCart() {
		return cart;
	}
	/**
	 * @param cart セットする cart
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	/**
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product セットする product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity セットする quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
