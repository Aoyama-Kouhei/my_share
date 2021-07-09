package jp.co.sss.ecsite.bean;

/**
 * 秘密の質問テーブルの情報を格納するDTO
 */
public class Secret {
	/** 秘密の質問ID */
	private int secretId;
	/** 秘密の質問の回答 */
	private String secretQuestion;

	/**
	 * @return secretId 秘密の質問ID
	 */
	public int getSecretId() {
		return secretId;
	}
	/**
	 * @param secretId 秘密の質問ID
	 */
	public void setSecretId(int secretId) {
		this.secretId = secretId;
	}
	/**
	 * @return secretQuestion 秘密の質問の回答
	 */
	public String getSecretQuestion() {
		return secretQuestion;
	}
	/**
	 * @param secretQuestion 秘密の質問の回答
	 */
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
}
