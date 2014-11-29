package edu.buffalo.ktmb.service;

import java.util.List;

import edu.buffalo.ktmb.bean.Bid;
import edu.buffalo.ktmb.bean.Query;
import edu.buffalo.ktmb.dao.QueryBidDAO;

public class QueryService {

	QueryBidDAO qbDao = new QueryBidDAO();
	
	public List<String> getAdsForQuery(String query) {
		return qbDao.getAdsForQuery(query);
	}
	
	public void incrementAdHitsForQuery(String query) {
		qbDao.incrementAdHitsForQuery(query);
	}
	
	public Query getQueryInfo(String query) {
		return qbDao.getInfoForQuery(query);
	}
	
	public List<Bid> getPreviousBidsForQuery(String query) {
		return qbDao.getPreviousBidsForQuery(query);
	}
	
	public Bid getWinningBidForQuery(String query) {
		return qbDao.getWinningBidForQuery(query);
	}
}
