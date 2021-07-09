package jp.co.sss.ecsite.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jp.co.sss.ecsite.bean.Category;
import jp.co.sss.ecsite.bean.Product;
import jp.co.sss.ecsite.dao.CategoryDao;
import jp.co.sss.ecsite.dao.ProductDao;

/**
 * 商品一覧画面に必要なランキング情報を取得するフィルター
 */
@WebFilter("/*")
public class SideRankingFilter implements Filter {
	/**
	 * 実行される処理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 売れ筋上位5つの商品を取得してリクエストスコープに格納する
		List<Product> topRankingList = ProductDao.findByRankTop5();
		request.setAttribute("topRankingList", topRankingList);

		// カテゴリリストを取得してリクエストスコープに格納する
		List<Category> categoryList = CategoryDao.findAll();
		request.setAttribute("categoryList", categoryList);

		// 次の処理へ移行
		chain.doFilter(request, response);
	}

	/** 未実装処理 */
	public void destroy() {}
	/** 未実装処理 */
	public void init(FilterConfig fConfig) throws ServletException {}
}
