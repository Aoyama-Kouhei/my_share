package jp.co.sss.ecsite.servlet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sss.ecsite.bean.Product;

/**
 * Servlet implementation class OutPutProductListByCsv
 */
@WebServlet("/OutPutProductListByCsv")
public class OutPutProductListByCsv extends HttpServlet {
	// カンマ
	private static final String COMMA = ",";
	// 改行
	private static final String NEW_LINE = "rn";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		FileWriter fileWriter = null;
		
		List<Product> productList = new ArrayList<Product>();
		
		try {
			fileWriter = new FileWriter("product_list");
			for(Product product : productList) {
				fileWriter.append(Integer.parse(product.getProductId());
				out.println(product.getProductId()+","+product.getProductName()+","+product.getPrice()+","+product.getCategory().getCategoryId()+","+product.getCategory().getCategoryName()+","+product.getExplainText());
			}
		}catch(Exception e) {
			
		}


		// 全商品情報の取得
		

		
		out = null;

	}
}
