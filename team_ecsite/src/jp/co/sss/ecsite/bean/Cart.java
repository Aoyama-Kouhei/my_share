package jp.co.sss.ecsite.bean;

/**
 * 買い物かごテーブルの情報を格納するDTO
 */
public class Cart {
	/**
	 * カートID
	 */
	private int cartId;
	/**
	 * ユーザー情報
	 */
	private User user;
	/**
	 * @return cartId
	 */
	public int getCartId() {
		return cartId;
	}
	/**
	 * @param cartId セットする cartId
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user セットする user
	 */
	public void setUser(User user) {
		this.user = user;
	}


}
