package edu.buffalo.ktmb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.buffalo.ktmb.bean.QueryResult;
import edu.buffalo.ktmb.dao.TestDAO;
import edu.buffalo.ktmb.server.SearchServer;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userQuery = request.getParameter("queryInput");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String salary = request.getParameter("salary");
		TestDAO td = new TestDAO();
		td.addEmployee(Integer.parseInt(id), name, Integer.parseInt(salary));
		SearchServer s = new SearchServer();
		List<QueryResult> bean = s.getResult(userQuery);
		System.out.println();
		request.setAttribute("result", bean);
		request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
	}

}
