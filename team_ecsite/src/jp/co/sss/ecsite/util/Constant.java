package jp.co.sss.ecsite.util;

/**
 * 定数設定用のクラス
 */
public class Constant {
	/** データベースから日付を取得する際の書式定義 */
	public static final String DATE_FORMAT_GET = "yyyy-MM-dd";
	
	/** 日付の書式定義 */
	public static final String DATE_FORMAT = "yyyy/MM/dd";

	/** パスワードの文字制限 */
	public static final int PASS_MIN = 8;
	public static final int PASS_MAX = 16;

	/** メールアドレスの文字制限 */
	public static final int MEIL_MAX = 256;

	/** ユーザー名の文字制限 */
	public static final int USERNAME_MAX = 30;

	/** 住所の文字制限 */
	public static final int ADDRESS_MAX = 60;

	/** 秘密の質問回答の文字制限 */
	public static final int SECRETANSER_MAX = 60;

	/** 商品名の文字制限 */
	public static final int PRODUCTNAME_MAX = 150;

	/** 商品価格の数値制限 */
	public static final int PRICE_MAX = 99999999;

	/** 商品説明文の文字制限 */
	public static final int EXPLANATION_MAX = 400;

	/** カテゴリIDの文字制限 */
	public static final int CATEGORYID_DIGIT = 4;

	/** カテゴリ名の文字制限 */
	public static final int CATEGORYNAME_MAX = 150;

	/** 商品購入個数の数値制限 */
	public static final int QUANTITY_MIN = 1;
	public static final int QUANTITY_MAX = 150;

	/** クレジットカードの文字制限 */
	public static final int CREDITNUMBER_DIGIT = 16;

	/** セキュリティコードの文字制限 */
	public static final int CREDITSECURITY_MIN = 3;
	public static final int CREDITSECURITY_MAX = 4;

	/** 名義の文字制限 */
	public static final int BANKUSERNAME_MAX = 30;

	/** 銀行名の文字制限 */
	public static final int BANKNAME_MAX = 30;

	/** 支店コードの文字制限 */
	public static final int BANKCODE_DIGIT = 3;


	/** 口座番号の文字制限 */
	public static final int ACCOUNTNUMBER_DIGIT = 7;
}
