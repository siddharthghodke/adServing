package edu.buffalo.ktmb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.response.QueryResponse;

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
		System.out.println("Query: "+userQuery);
		SearchServer s = new SearchServer();
		QueryResponse rsp = s.getResult(userQuery);
		List<QueryResult> bean = rsp.getBeans(QueryResult.class);
		request.setAttribute("result", bean);
		
		// highlighting parameters
		Map<String, Map<String, List<String>>> hl = rsp.getHighlighting();
		request.setAttribute("snippetMap", hl);
		
		// retrieve ads list for user query and update query hits 
		List<String> adList = queryService.getAdsForQuery(userQuery);
		request.setAttribute("adList", adList);
		
		request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
	}

}