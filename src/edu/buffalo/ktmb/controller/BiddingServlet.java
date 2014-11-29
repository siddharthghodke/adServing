/**
 * 
 */
package edu.buffalo.ktmb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author shashank
 *
 */
@WebServlet("/BiddingServlet")
public class BiddingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BiddingServlet() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userQuery = request.getParameter("queryInput");
		String bidAmt = request.getParameter("bidAmt");
		String advLink = request.getParameter("advLink");
		System.out.println(userQuery +bidAmt+ advLink);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userQuery = request.getParameter("queryInput");
		String bidAmt = request.getParameter("bidAmt");
		String advLink = request.getParameter("advLink");
		System.out.println(userQuery +bidAmt+ advLink);
		
		if(userQuery!=null && bidAmt!=null && advLink!=null)
		request.setAttribute("result", "success");
		request.getRequestDispatcher("/EnterBid.jsp").forward(request, response);
	}


}
