package jp.co.sss.ecsite.bean;

public class Category {
	/** カテゴリID */
	private String categoryId;
	/** カテゴリ名 */
	private String categoryName;
	/**
	 * @return categoryId カテゴリID
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId カテゴリID
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return categoryName カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
