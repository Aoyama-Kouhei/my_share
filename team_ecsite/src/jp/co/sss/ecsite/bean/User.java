package jp.co.sss.ecsite.bean;

/**
 * 会員テーブルの情報を格納するDTO
 */
public class User {
	/** 会員ID */
	private int userId;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 会員名 */
	private String userName;
	/** 住所 */
	private String address;
	/** 誕生日 */
	private String birthday;
	/** 秘密の質問 */
	private Secret secret;
	/** 秘密の質問の回答 */
	private String secretAnswer;
	/** 権限 */
	private int authority;

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
	 * @return email メールアドレス
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return userName 会員名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName 会員名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return address 住所
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return birthday 誕生日
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday 誕生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return secret 秘密の質問オブジェクト
	 */
	public Secret getSecret() {
		return secret;
	}
	/**
	 * @param secret 秘密の質問オブジェクト
	 */
	public void setSecret(Secret secret) {
		this.secret = secret;
	}
	/**
	 * @return secretAnswer 秘密の質問の回答
	 */
	public String getSecretAnswer() {
		return secretAnswer;
	}
	/**
	 * @param secretAnswer 秘密の質問の回答
	 */
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}
	/**
	 * @return authority 権限
	 */
	public int getAuthority() {
		return authority;
	}
	/**
	 * @param authority 権限
	 */
	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
