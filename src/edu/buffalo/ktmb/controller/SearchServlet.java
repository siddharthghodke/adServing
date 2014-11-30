package edu.buffalo.ktmb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.buffalo.ktmb.bean.QueryResult;
import edu.buffalo.ktmb.server.SearchServer;
import edu.buffalo.ktmb.service.QueryService;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryService queryService = new QueryService();
       
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
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userQuery = request.getParameter("userQuery");
		String postRequest = request.getParameter("postRequest");
		SearchServer s = new SearchServer();
		List<QueryResult> bean = s.getResult(userQuery);
		request.setAttribute("result", bean);
		
		switch(postRequest){
			case "search":
				// retrieve ads list for user query and update query hits 
				List<String> adList = queryService.getAdsForQuery(userQuery);;
				request.setAttribute("adList", adList);
				request.setAttribute("userQuery",userQuery);
				request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
				break;
			case "adUpdate":
				queryService.incrementAdHitsForQuery(userQuery);
				request.setAttribute("userQuery",userQuery);
				request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
				
		}
		
		
	}

}