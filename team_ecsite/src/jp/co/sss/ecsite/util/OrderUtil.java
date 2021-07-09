package jp.co.sss.ecsite.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.co.sss.ecsite.bean.History;
import jp.co.sss.ecsite.bean.Order;
import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.OrderDao;

public class OrderUtil {
	private static String endExplainStr = "...";

	/**
	 * カート追加商品重複チェック
	 * @param list カート内商品リスト
	 * @param p 追加商品
	 * @return チェック後のカート内商品リスト
	 */
	public static boolean cartInputCheck(List<Product> list, Product p) {
		boolean sameFlag = false;

		for(Product l: list) {
			//リスト内に同じ商品情報がある場合
			if(l.getProductId() == p.getProductId()) {
				sameFlag = true;
				break;
			}
		}
		return sameFlag;
	}

	/**
	 * 説明文省略メソッド（一覧表示用）
	 * @param p 処理対象商品情報
	 * @return 処理後商品情報
	 */
	public static Product OmitExplain(Product p) {
		String explainTxt = p.getExplainText();

		//説明文が10文字以上の場合1
		if(10 < explainTxt.length()) {
			//説明文を10文字へ打ち切り
			explainTxt = explainTxt.substring(0, 10);
			//説明文末尾に"..."をつける
			explainTxt += endExplainStr;
			p.setExplainText(explainTxt);
		}


		return p;
	}
	/**
	 * 商品一覧用商品リスト内説明文一括省略メソッド
	 * @param l 商品一覧リスト
	 * @return 処理後商品一覧リスト
	 */
	public static List<Product> OmitAllExplain(List<Product> l) {

		for(int i = 0; i < l.size(); i++) {
			Product p = new Product();

			p = l.get(i);
			//省略メソッド呼び出し
			p = OmitExplain(p);

			l.set(i, p);

		}

		return l;
	}

	//注文登録用メソッド
	public static void registOrderHistory(int userId, int payment, List<History> list) {
		Order order = new Order();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		//今日の日付をyyyy/MM/ddの形に変更
		String sDate = sdf.format(date);

		//登録に必要な情報をbeanへ追加
		order.setUserId(userId);
		order.setPayment(payment);
		order.setOrderDate(sDate);

		//注文登録用Dao呼び出し
		OrderDao.insert(order, list);


	}


}
