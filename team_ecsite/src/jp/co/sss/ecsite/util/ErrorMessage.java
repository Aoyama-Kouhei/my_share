package jp.co.sss.ecsite.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import jp.co.sss.ecsite.bean.User;

public class ErrorMessage {

	/**
	 * リストに空文字以外のエラーメッセージがあるか判定
	 *
	 * @param list
	 * @return エラーメッセージがあればfalse、なければtrue
	 */
	public static boolean error(List<String> list) {
		for(String message : list) {
			// 空文字でない（エラーメッセージである）ものがあれば、falseを返す
			if (!message.equals("")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 会員登録用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param mail メールアドレス
	 * @param pass1 パスワード
	 * @param pass2 パスワード(再確認)
	 * @param name 氏名
	 * @param address 住所
	 * @param birthday 誕生日
	 * @param answer 秘密の質問回答
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkUser(String mail, String pass1, String pass2, String name, String address,
			String birthday, String answer) {
		List<String> messageList = new ArrayList<>();

		//メールアドレス
		String messageMail = errorsInvalidMail(mail, "メールアドレス");
		if (messageMail.equals("")) {
			messageMail = errorsMaxLength(mail, "メールアドレス", 256);
		}
		messageList.add(messageMail);

		//パスワード
		String messagePass = errorsRangeLength(pass1, "パスワード", 8, 16);
		if (messagePass.equals("")) {
			messagePass = errorsPassword(pass1, pass2);
		}
		messageList.add(messagePass);

		//氏名
		String messageName = errorsMaxLength(name, "氏名", 30);
		messageList.add(messageName);

		//誕生日
		String messageBirthday = errorsInvalidDate(birthday, "誕生日");
		if (messageBirthday.equals("")) {
			messageBirthday = dayPastFuture(birthday);
		}
		messageList.add(messageBirthday);

		//住所
		String messageAddress = errorsMaxLength(address, "住所", 60);
		messageList.add(messageAddress);

		//秘密の質問回答
		String messageAnswer = errorsMaxLength(answer, "秘密の質問の答え", 60);
		messageList.add(messageAnswer);

		return messageList;
	}

	/**
	 * 会員編集用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param mail メールアドレス
	 * @param pass1 パスワード
	 * @param pass2 パスワード(再確認)
	 * @param name 氏名
	 * @param address 住所
	 * @param birthday 誕生日
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkUserUpdate(String mail, String pass1, String pass2, String name, String address,
			String birthday) {
		List<String> messageList = new ArrayList<>();

		//メールアドレス
		String messageMail = errorsInvalidMail(mail, "メールアドレス");
		if (messageMail.equals("")) {
			messageMail = errorsMaxLength(mail, "メールアドレス", 256);
		}
		messageList.add(messageMail);

		//パスワード
		String messagePass = errorsRangeLength(pass1, "パスワード", 8, 16);
		if (messagePass.equals("")) {
			messagePass = errorsPassword(pass1, pass2);
		}
		messageList.add(messagePass);

		//氏名
		String messageName = errorsMaxLength(name, "氏名", 30);
		messageList.add(messageName);

		//住所
		String messageAddress = errorsMaxLength(address, "住所", 60);
		messageList.add(messageAddress);

		//誕生日
		String messageBirthday = errorsInvalidDate(birthday, "誕生日");
		if (messageBirthday.equals("")) {
			messageBirthday = dayPastFuture(birthday);
		}
		messageList.add(messageBirthday);

		return messageList;
	}

	/**
	 * ログイン用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param mail メールアドレス
	 * @param pass パスワード
	 * @param user 会員情報オブジェクト
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkLogin(String mail, String pass, User user) {
		List<String> messageList = new ArrayList<>();

		//メールアドレス
		String messageMail = errorsInvalidMail(mail, "メールアドレス");
		if (messageMail.equals("")) {
			messageMail = errorsMaxLength(mail, "メールアドレス", 256);
		}
		messageList.add(messageMail);

		//パスワード
		String messagePass = errorsRangeLength(pass, "パスワード", 8, 16);
		messageList.add(messagePass);

		if (error(messageList)) {
			//ユーザー情報
			String messageUser = errorsLogin(user);
			messageList.add(messageUser);
		}
		return messageList;
	}

	/**
	 * 秘密の質問ログイン用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param mail メールアドレス
	 * @param answer 秘密の質問の回答
	 * @param user 会員情報オブジェクト
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkLoginSecret(String mail, String answer, User user) {
		List<String> messageList = new ArrayList<>();

		//メールアドレス
		String messageMail = errorsInvalidMail(mail, "メールアドレス");
		if (messageMail.equals("")) {
			messageMail = errorsMaxLength(mail, "メールアドレス", 256);
		}
		messageList.add(messageMail);

		//秘密の質問回答
		String messageAnswer = errorsMaxLength(answer, "秘密の質問の答え", 60);
		messageList.add(messageAnswer);

		if (error(messageList)) {
			//ユーザー情報
			String messageUser = errorsLoginSecret(user);
			messageList.add(messageUser);
		}
		return messageList;
	}

	/**
	 * 商品検索の価格チェック
	 *
	 * @param price 価格
	 * @return エラーがある場合はエラーメッセージ、なければ空文字""
	 */
	public static String checkPrice(String price) {
		//価格
		String message = checkIsNum(price, "価格");
		if (message.equals("")) {
			message = errorsLength(price, "価格", 99999999);
		}
		return message;
	}


	/**
	 * 買い物かごの個数
	 *
	 * @param quantity 個数
	 * @return エラーがある場合はエラーメッセージ、なければ空文字""
	 */
	public static String checkQuantity(String quantity) {
		//個数
		String message = checkIsNum(quantity, "個数");
		if (message.equals("")) {
			message = errorsRange(quantity, "個数", 1, 100);
		}
		return message;
	}

	/**
	 * 商品登録用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param productName 商品名
	 * @param price 価格
	 * @param categoryId カテゴリID
	 * @param explain 説明文
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */

	public static List<String> checkProductRegist(String productName, String price, String categoryId, String explain) {
		List<String> messageList = new ArrayList<>();

		//商品名
		String messageName = errorsMaxLength(productName, "商品名", 150);
		messageList.add(messageName);

		//価格
		String messagePrice = checkIsNum(price, "価格");
		if (messagePrice.equals("")) {
			messagePrice = errorsRange(price, "価格", 1, 99999999);
		}
		messageList.add(messagePrice);

		//カテゴリID
		String messageCategory = errorsInvalidCategory(categoryId, "カテゴリID");
		if (messageCategory.equals("")) {
			messageCategory = errorsLength(categoryId, "カテゴリID", 4);
		}
		messageList.add(messageCategory);

		//説明文
		String messageExplain = errorsMaxLength(explain, "説明文", 400);
		messageList.add(messageExplain);

		return messageList;
	}

	/**
	 * 商品変更用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param productName 商品名
	 * @param price 価格
	 * @param explain 説明文
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkProductUpdate(String productName, String price, String explain) {
		List<String> messageList = new ArrayList<>();

		//商品名
		String messageName = errorsMaxLength(productName, "商品名", 150);
		messageList.add(messageName);

		//価格
		String messagePrice = checkIsNum(price, "価格");
		if (messagePrice.equals("")) {
			messagePrice = errorsRange(price, "価格", 1, 99999999);
		}
		messageList.add(messagePrice);
		//パスワード
		String messageExplain = errorsMaxLength(explain, "説明文", 400);
		messageList.add(messageExplain);

		return messageList;
	}

	/**
	 * カテゴリ登録用
	 * エラーメッセージをリストに格納
	 * エラーがない場合は、空文字""が格納される
	 *
	 * @param categoryId カテゴリID
	 * @param categoryName カテゴリ名
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkCategoryRegist(String categoryId, String categoryName) {
		List<String> messageList = new ArrayList<>();

		//カテゴリID
		String messageCategory = errorsInvalidCategory(categoryId, "カテゴリID");
		if (messageCategory.equals("")) {
			messageCategory = errorsLength(categoryId, "カテゴリID", 4);
		}
		messageList.add(messageCategory);

		//商品名
		String messageName = errorsMaxLength(categoryName, "カテゴリ名", 150);
		messageList.add(messageName);

		return messageList;
	}

	/**
	 * 支払方法（クレジットカード）用
	 *
	 * @param cregit クレジットカード番号
	 * @param code セキュリティコード
	 * @param name 名義
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkProductyRegist2(String cregit, String code, String name) {
		List<String> messageList = new ArrayList<>();

		//クレジットカード
		String messageCregit = checkIsNum(cregit, "クレジットカード");
		if (messageCregit.equals("")) {
			messageCregit = errorsLength(cregit, "クレジットカード", 16);
		}
		messageList.add(messageCregit);

		//セキュリティコード
		String messageCode = checkIsNum(code, "セキュリティコード");
		if (messageCode.equals("")) {
			messageCode = errorsRangeLength(code, "セキュリティコード", 3, 4);
		}
		messageList.add(messageCode);

		//名義
		String messageName = errorsMaxLength(name, "名義", 30);
		messageList.add(messageName);

		return messageList;

	}

	/**
	 * 支払方法（銀行振り込み）用
	 *
	 * @param bankName 銀行名
	 * @param bankNo 支店コード
	 * @param accountNo 口座番号
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkProductyRegist2sub(String bankName,
			String bankNo, String accountNo) {
		List<String> messageList = new ArrayList<>();
		//銀行名
		String messageBankName = errorsMaxLength(bankName, "銀行名", 30);
		messageList.add(messageBankName);

		//支店コード
		String messageBranchName = checkIsNum(bankNo, "支店コード");
		if (messageBranchName.equals("")) {
			messageBranchName = errorsLength(bankNo, "支店コード", 3);
		}
		messageList.add(messageBranchName);

		//口座番号
		String messageBankNum = checkIsNum(accountNo, "口座番号");
		if (messageBankNum.equals("")) {
			messageBankNum = errorsLength(accountNo, "口座番号", 7);
		}
		messageList.add(messageBankNum);

		return messageList;
	}

	/**
	 * 注文登録用
	 *
	 * @param productId 商品ID
	 * @param quantity 個数
	 * @return 各入力値に対するエラーメッセージを格納したリスト
	 */
	public static List<String> checkProductyRegist3(String productId, String quantity) {
		List<String> messageList = new ArrayList<>();

		//商品名
		String messageName = checkIsNum(productId, "商品Id");
		if (messageName.equals("")) {
			messageName = errorsMaxLength(productId, "商品Id", 9999999);
		}
		messageList.add(messageName);

		//価格
		String messageQuantity = checkIsNum(quantity, "個数");
		if (messageQuantity.equals("")) {
			messageQuantity = errorsRange(quantity, "個数", 1, 100);
		}
		messageList.add(messageQuantity);

		return messageList;
	}


	/**
	 * No.1 errors.login（詳細設計書参照）
	 * ログイン時のエラーメッセージ
	 *
	 * @param user 会員情報オブジェクト
	 * @return エラーメッセージ
	 */
	public static String errorsLogin(User user) {
		String message = "";

		if (user.getUserId() == 0) {
			message = "メールアドレス、もしくはパスワードが間違っています。";
		}
		return message;
	}

	/**
	 * No.1 errors.login
	 * 秘密の質問ログイン時のエラーメッセージ
	 *
	 * @param user 会員情報オブジェクト
	 * @return エラーメッセージ
	 */
	public static String errorsLoginSecret(User user) {
		String message = "";

		if (user.getUserId() == 0) {
			message = "メールアドレス、もしくは秘密の質問が間違っています。";
		}
		return message;
	}

	/**
	 * 形式チェック(日付)
	 * @param date 入力された日付
	 * @return 日付の整合性
	 */
	private static boolean isDate(String date) {
		try {
			// 日付チェック
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			sdf.setLenient(false);//厳密にチェック
			sdf.parse(date);//不一致でParseException,try-catchで囲む

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 形式チェック(メール)
	 * @param input 入力値
	 * @return 形式の整合性
	 */
	public static boolean isMail(String input) {
		boolean result = false;
		if (input != null) {
			Pattern pattern = Pattern
					.compile("^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$");
			result = pattern.matcher(input).find();
			System.out.println(result);
		}
		return result;
	}

	/**
	 * 形式チェック(カテゴリ)
	 * @param input 入力値
	 * @return 形式の整合性
	 */
	public static boolean isCategory(String input) {
		boolean result = false;
		if (input != null) {
			Pattern pattern = Pattern.compile("^([A-Z])+([0-9])+$");
			result = pattern.matcher(input).find();
		}
		return result;
	}

	/**
	 * No.2 + No.3(日付)
	 * @param pass
	 * @return message
	 */
	public static String errorsInvalidDate(String input, String name) {
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else if (!isDate(input)) {
			message = name + "の形式が不正です。";
		}
		return message;
	}

	/**
	 * No.2 + No.3(メール)
	 * @param pass
	 * @return message
	 */
	public static String errorsInvalidMail(String input, String name) {
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else if (!isMail(input)) {
			message = name + "の形式が不正です。";
		}
		return message;
	}

	/**
	 * No.2 + No.3(カテゴリ)
	 * @param pass
	 * @return message
	 */
	public static String errorsInvalidCategory(String input, String name) {
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else if (!isCategory(input)) {
			message = name + "の形式が不正です。";
		}
		return message;
	}

	/**
	 * No.2 + No.4
	 * @param 入力値
	 * @param エラーメッセージに記載する文字列
	 * @param 制限文字数
	 * @return
	 */
	public static String errorsLength(String input, String name, int length) {
		int num = 0;
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else {
			num = input.length();
			if (num > length) {
				message = name + "は" + length + "文字で入力してください。";
			}
		}
		return message;
	}

	/**
	 * No.2 + No.5
	 * @param 入力値
	 * @param エラーメッセージに記載する文字列
	 * @param 制限文字数
	 * @return
	 */
	public static String errorsMaxLength(String input, String name, int length) {
		int num = 0;
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else {
			num = input.length();
			if (num > length) {
				message = name + "は" + length + "文字以下で入力してください。";
			}
		}
		return message;
	}

	/**
	 * No.2 + No.6
	 * @param 入力値
	 * @param エラーメッセージに記載する文字列
	 * @param 制限文字数
	 * @return
	 */
	public static String errorsRangeLength(String input, String name, int length1, int length2) {
		int num = 0;
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else {
			num = input.length();
			if (num > length2 || num < length1) {
				message = name + "は" + length1 + "文字以上" + length2 + "文字以下で入力してください。";
			}
		}
		return message;
	}

	/**
	 * No.7
	 * @param 入力値
	 * @param エラーメッセージに記載する文字列
	 * @param 制限文字数
	 * @return
	 */
	public static String errorsRange(String input, String name, int length1, int length2) {
		String message = "";

		if (input.equals("")) {
			message = name + "は必須です。";
		} else {
			int numInput = Integer.parseInt(input);
			if (numInput > length2 || numInput < length1) {
				message = name + "は" + length1 + "以上" + length2 + "以下で入力してください。";
			}
		}
		return message;
	}

	/**
	 * No.8
	 * @param エラーメッセージに記載する文字列{0}
	 * @return エラーメッセージ
	 */
	public static String registComplete(String name) {
		String message;
		message = name + "登録処理が完了しました。";

		return message;
	}

	/**
	 * No.9
	 * @param エラーメッセージに記載する文字列{0}
	 * @return エラーメッセージ
	 */
	public static String updateComplete(String name) {
		String message;
		message = name + "更新処理が完了しました。";

		return message;
	}

	/**
	 * No.10
	 * @param エラーメッセージに記載する文字列{0}
	 * @return エラーメッセージ
	 */
	public static String deleteComplete(String name) {
		String message;
		message = name + "削除処理が完了しました。";

		return message;
	}

	/**
	 * No.11
	 * @param エラーメッセージに記載する文字列{0}
	 * @return エラーメッセージ
	 */
	public static String orderComplete() {
		String message;
		message = "ご注文ありがとうございました。。";

		return message;
	}

	/**
	 * No.12 + No.13
	 * @param date 入力された日付
	 * @return 日付の整合性
	 */
	public static String dayPastFuture(String date) {
		String message = "";
		// 日付チェック
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);

		Calendar nowCalendar = Calendar.getInstance();

		//本日の日付を取得
		String today = sdf.format(nowCalendar.getTime());
		//過去の日付を格納
		String pastRange = "1900/1/1";
		//入力と比較して未来なら1を返す。
		int future = date.compareTo(today);
		int past = date.compareTo(pastRange);
		if (future == 1 || past == -1) {
			message = "正しい日付を入力してください。";
		}

		return message;
	}

	/**
	 * No.15
	 * @param date 入力された日付
	 * @return 日付の整合性
	 */
	public static String errorsPassword(String password1, String password2) {
		String message = "";
		// 日付チェック
		if (password1.equals(password2)) {
			message = "";
		} else {
			message = "パスワードが一致しません。";
		}

		return message;
	}

	/**
	 * 数字の判定(形式チェックの一種)
	 * @param str 入力値
	 * @return	判定値
	 */
	public static String checkIsNum(String str, String name) {
		String message = "";
		boolean isNum = true;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				isNum = false;
			}
		}
		if (!isNum) {
			message = name + "の形式が不正です。";
		}
		return message;
	}
}
