package edu.buffalo.ktmb.service;

import java.util.List;

import edu.buffalo.ktmb.bean.Bid;
import edu.buffalo.ktmb.bean.Query;
import edu.buffalo.ktmb.dao.QueryBidDAO;

public class QueryService {

	QueryBidDAO qbDao = new QueryBidDAO();
	private static final Float MIN_BID = 5.00F;
	private static final Float QUERY_HIT_QUOTIENT = 0.5F;
	private static final Float AD_HIT_QUOTIENT = 0.5F;
	
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
	
	public float getMinBidPrice(String query) {
		Query q = qbDao.getInfoForQuery(query);
		float minBid;
		if(q != null) {
			minBid = ((float) q.getMinBidPrice()) / 100;
		} else {
			minBid = MIN_BID;
		}
		return minBid;
	}
	
	public void updateMinBidPriceForQueries() {
		List<Query> queryList = qbDao.getQueriesToUpdateMinBidPrice();
		int maxHits = qbDao.getMaxQueryHits();
		//int[][] updateInfo = new int[queryList.size()][];
		//int i=0;
		
		for(Query query: queryList) {
			int currentMin = query.getMinBidPrice();
			int newMin = currentMin;
			int queryHits = query.getQueryHits();
			int adHits = query.getAdHits();
			
			float queryPopularity = (float) queryHits / maxHits;
			float adCTR = (float) adHits / queryHits;
			
			float incrementFactor = queryPopularity * QUERY_HIT_QUOTIENT + adCTR * AD_HIT_QUOTIENT;
			
			List<Bid> previousWinningBids = getPreviousBidsForQuery(query.getQuery());
			int sum = 0;
			int count = 0;
			for(Bid bid: previousWinningBids) {
				sum += bid.getBidAmount();
				count++;
			}
			int avg = sum/count;
			
			if(avg > currentMin) {
				int diff = avg - currentMin;
				int inc = (int) (diff * incrementFactor);
				newMin = currentMin + inc;
				qbDao.updateQueryMinBid(query.getQueryId(), newMin);
			}
		/*	updateInfo[i][0] = query.getQueryId();
			updateInfo[i][1] = newMin;
			i++;*/
		}
		
	}
}
