package com.itheima.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Book;
import com.itheima.service.impl.BookServiceImpl;

/**
 * Servlet implementation class FindProductByManyConditionServlet
 */
@WebServlet("/FindProductByManyConditionServlet")
public class FindProductByManyConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindProductByManyConditionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// 获取表单数据

		String sql = "select * from book";

		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> newMap = new HashMap<String, String>();
		String[] price = { "", "" };

		// 去空值
		for (String key : map.keySet()) {
			if (!"".equals(map.get(key)[0])) {
				newMap.put(key, map.get(key)[0]);
				sql = "select * from book where ";
			}
		}

		// 把价格保存到price数组里
		if ("minprice".equals("minprice")) {
			price[0] = newMap.remove("minprice");
		}
		if ("maxprice".equals("maxprice")) {
			price[1] = newMap.remove("maxprice");
		}

		// 拼接sql
		int count = 0;
		for (String key : newMap.keySet()) {
			count++;
			if (price[0] != null || price[1] != null) {
				//如果price不为空
				sql += key + "=\'" + newMap.get(key) + "\' and ";
			} else {
				//如果price为空
				if (count != newMap.size()) {
					sql += key + "=\'" + newMap.get(key) + "\' and ";
				} else {
					sql += key + "=\'" + newMap.get(key) + "\'";
				}
			}
		}

		// 拼接price到sql
		if (price[0] != null && price[1] == null) {
			// 大于最小值
			sql += "price > " + price[0];
		}

		if (price[0] == null && price[1] != null) {
			// 小于最大值
			sql += "price < " + price[1];
		}

		if (price[0] != null && price[1] != null) {
			// 介于两者之间
			sql += "price BETWEEN " + price[0] + " and " + price[1];
		}

		System.out.println(sql);
		// 调用业务逻辑
		BookServiceImpl bsi = new BookServiceImpl();
		List<Book> findBookList = null;
		try {
			findBookList = bsi.findProductByManyCondition(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 分发转向
		if (findBookList != null) {
			request.setAttribute("books", findBookList);
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
