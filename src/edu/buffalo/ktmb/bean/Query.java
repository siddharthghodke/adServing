package edu.buffalo.ktmb.bean;

public class Query {

	private int queryId;
	private String query;
	private int queryHits;
	private int adHits;
	private int bidId;
	
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getQueryHits() {
		return queryHits;
	}
	public void setQueryHits(int queryHits) {
		this.queryHits = queryHits;
	}
	public int getAdHits() {
		return adHits;
	}
	public void setAdHits(int adHits) {
		this.adHits = adHits;
	}
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
}
