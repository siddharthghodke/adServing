package edu.buffalo.ktmb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.buffalo.ktmb.bean.Bid;
import edu.buffalo.ktmb.bean.Query;
import edu.buffalo.ktmb.db.DBManager;

public class QueryBidDAO {

	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * Retrieves top ads for a query
	 * Also updates the hits for the query
	 * @param query
	 * @return list of top 3 ads for the query
	 */
	public List<String> getAdForQuery(String query) {
		try {
			con = DBManager.getInstance().getConnection();

			String ad1, ad2, ad3;
			ad1 = ad2 = ad3 = "";
			List<String> topAds = new ArrayList<String>();
			
			String sql = "SELECT * FROM query WHERE query = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// query exists
				int queryId = rs.getInt("query_id");
				int queryHits = rs.getInt("query_hits");
				//int bidId = rs.getInt("bid_id");
				
				// update query table for query hits
				String updateQueryTable = "UPDATE query SET query_hits = ? WHERE query_id = ?";
				pstmt = con.prepareStatement(updateQueryTable);
				pstmt.setInt(1, ++queryHits);
				pstmt.setInt(2, queryId);
				pstmt.executeUpdate();
				
				// retrieve the ad for the query
				sql = "SELECT * FROM query_top_bids WHERE query_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, queryId);
				ResultSet adRS = pstmt.executeQuery();
				if(adRS.next()) {
					// ads exist for given query
					int bidId1 = adRS.getInt("bid_id_1");
					int bidId2 = adRS.getInt("bid_id_2");
					int bidId3 = adRS.getInt("bid_id_3");
					
					sql = "SELECT ad FROM bid WHERE bid_id = ?";
					pstmt = con.prepareStatement(sql);
					ResultSet bidRS;
					
					pstmt.setInt(1, bidId1);
					bidRS = pstmt.executeQuery();
					bidRS.first();
					ad1 = bidRS.getString("ad");
					
					if(bidId2 != -1) {
						pstmt.setInt(1, bidId2);
						bidRS = pstmt.executeQuery();
						bidRS.first();
						ad2 = bidRS.getString("ad");
					}
					
					if(bidId3 != -1) {
						pstmt.setInt(1, bidId3);
						bidRS = pstmt.executeQuery();
						bidRS.first();
						ad3 = bidRS.getString("ad");
					}
					bidRS.close();
					
				} else {
					// ads do not exist; do nothing
				}
				adRS.close();
				
			} else {
				// query does not exist
				// create new entry in query table
				String updateQueryTable = "INSERT INTO query(query, query_hits, ad_hits) VALUES (?, ?, ?)";
				pstmt = con.prepareStatement(updateQueryTable);
				pstmt.setString(1, query);
				pstmt.setInt(2, 1);
				pstmt.setInt(3, 0);
				pstmt.executeUpdate();
			}
			
			topAds.add(ad1);
			topAds.add(ad2);
			topAds.add(ad3);
			return topAds;
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
		return null;
	}
	
	/**
	 * Increment ad hits for given query
	 * @param query
	 */
	public void incrementAdHitForQuery(String query) {
		try {
			con = DBManager.getInstance().getConnection();
			String sql = "SELECT * FROM query WHERE query = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int queryId = rs.getInt("query_id");
				int adHits = rs.getInt("ad_hits");
				sql = "UPDATE query SET ad_hits = ? WHERE query_id = ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, ++adHits);
				pstmt.setInt(2, queryId);
				
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
	}
	
	/**
	 * Add a bid for given query
	 * @param bid
	 */
	public void addBid(Bid bid) {
		try {
			
			con = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO bid(bid_amount, ad, query_id, session_id) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bid.getBidAmount());
			pstmt.setString(2, bid.getAdUrl());
			pstmt.setInt(3, bid.getQueryId());
			pstmt.setInt(4, bid.getSessionId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
	}
	
	/**
	 * Retrieve the last winning bid for the given query
	 * @param query
	 * @return
	 */
	public Bid getWinningBidForQuery(String query) {
		try {
			con = DBManager.getInstance().getConnection();
			String sql = "SELECT query_id FROM query WHERE query = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, query);
			rs = pstmt.executeQuery();
			if(rs.first()) {
				int queryId = rs.getInt("query_id");
				sql = "SELECT bid_id_1 FROM query_top_bids WHERE query_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, queryId);
				rs = pstmt.executeQuery();
				rs.first();
				int bidId = rs.getInt("bid_id_1");
				
				sql = "SELECT * FROM bid WHERE bid_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bidId);
				rs = pstmt.executeQuery();
				rs.first();
				Bid bid = new Bid();
				bid.setBidId(rs.getInt("bid_id"));
				bid.setAdUrl(rs.getString("ad"));
				bid.setBidAmount(rs.getInt("bid_amount"));
				bid.setQueryId(rs.getInt("query_id"));
				bid.setSessionId(rs.getInt("session_id"));
				
				return bid;
			} else {
				// query does not exist
				return null;
			}
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
		return null;
	}
	
	/**
	 * Get previous bids for given query
	 * @param query
	 * @return
	 */
	public List<Bid> getPreviousBidsForQuery(String query) {
		try {
			con = DBManager.getInstance().getConnection();
			int queryId = getQueryId(query);
			String sql = "SELECT * FROM bid WHERE query_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, queryId);
			
			List<Bid> bidsForQuery = new ArrayList<Bid>();
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setBidId(rs.getInt("bid_id"));
				bid.setAdUrl(rs.getString("ad"));
				bid.setBidAmount(rs.getInt("bid_amount"));
				bid.setQueryId(rs.getInt("query_id"));
				bid.setSessionId(rs.getInt("session_id"));
				bidsForQuery.add(bid);
			}
			return bidsForQuery;
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
		
		return null;
	}
	
	/**
	 * Update top 3 winning bids for all queries for current session 
	 * @param sessionId
	 */
	public void updateWinningBids(int sessionId) {
		try {
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
		
	}
	
	/**
	 * Get query information for given query
	 * @param query
	 * @return
	 */
	public Query getInfoForQuery(String query) {
		try {
			con = DBManager.getInstance().getConnection();
			String sql = "SELECT * FROM query WHERE query = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Query q = new Query();
				q.setQueryId(rs.getInt("query_id"));
				q.setQuery(query);
				q.setQueryHits(rs.getInt("query_hits"));
				q.setAdHits(rs.getInt("ad_hits"));
				return q;
			} else {
				return null;
			}
		} catch (Exception e) {
			
		} finally {
			close();
		}
		
		return null;
	}
	
	/**
	 * Get query id for given query
	 * @param query
	 * @return
	 */
	public int getQueryId(String query) {
		try {
			con = DBManager.getInstance().getConnection();
			String sql = "SELECT query_id FROM query WHERE query = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, query);
			rs = pstmt.executeQuery();
			rs.first();
			return rs.getInt("query_id");
		} catch (Exception e) {
			
		} finally {
			close();
		}
		
		return -1;
	}
	
	/**
	 * Insert the given query in query table
	 * Used when a bid is accepted for a query 
	 * which is not already present in table
	 * @param query
	 * @return
	 */
	public int insertQuery(String query) {
		try {
			con = DBManager.getInstance().getConnection();
			String sql = "INSERT INTO query(query, query_hits, ad_hits) VALUES (?,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.executeUpdate();
			
			return getQueryId(query);
			
		} catch (Exception e) {
			
		} finally {
			close();
		}
		return -1;
	}
	
	/**
	 * close the resources
	 */
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			
		}
	}
}
