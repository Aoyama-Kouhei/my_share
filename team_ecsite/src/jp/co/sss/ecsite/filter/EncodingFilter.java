package jp.co.sss.ecsite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 文字コードを設定するフィルター
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
	/**
	 * 実行される処理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 文字コードを設定
		request.setCharacterEncoding("UTF-8");

		// 次の処理へ移行
		chain.doFilter(request, response);
	}

	/** 未実装処理 */
	public void destroy() {}
	/** 未実装処理 */
	public void init(FilterConfig fConfig) throws ServletException {}
}
