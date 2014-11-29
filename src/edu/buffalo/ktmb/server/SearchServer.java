package edu.buffalo.ktmb.server;


import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import edu.buffalo.ktmb.bean.QueryResult;
import edu.buffalo.ktmb.util.StringConstant;

public class SearchServer {
	
	SolrServer solr;
	public SearchServer(){
		solr = new HttpSolrServer(StringConstant.SOLR_CORE_URL);
	}
	
	public List<QueryResult> getResult(String userQuery) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(userQuery);
		QueryResponse rsp = null;
		try {
			rsp = solr.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<QueryResult> beans = rsp.getBeans(QueryResult.class);
		return beans;
	}
}
