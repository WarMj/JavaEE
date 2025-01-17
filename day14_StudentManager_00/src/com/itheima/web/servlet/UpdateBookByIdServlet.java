package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Book;
import com.itheima.service.impl.BookServiceImpl;

/**
 * Servlet implementation class UpdateBookById
 */
@WebServlet("/UpdateBookByIdServlet")
public class UpdateBookByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		//获取表单数据
		Book book = new Book();
		
		try {
			BeanUtils.populate(book, request.getParameterMap());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//调用业务逻辑
		BookServiceImpl bsi = new BookServiceImpl();
		try {
			Book b = bsi.updateBookById(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//分发转向
		request.getRequestDispatcher("BookListServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
