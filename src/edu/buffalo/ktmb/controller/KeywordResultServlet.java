package edu.buffalo.ktmb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/KeywordResultServlet")
public class KeywordResultServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public KeywordResultServlet()
	{
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
		System.out.println("Get"+keyword);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
		
	//	TestDAO td = new TestDAO();
	//	td.addEmployee(Integer.parseInt(id), name, Integer.parseInt(salary));
//		SearchServer s = new SearchServer();
	//	List<QueryResult> bean = s.getResult(userQuery);
//		System.out.println();
	//	request.setAttribute("result", bean);
		System.out.println(keyword);
		
	//	request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
	}
	

}
