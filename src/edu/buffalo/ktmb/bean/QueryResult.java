package edu.buffalo.ktmb.bean;
import org.apache.solr.client.solrj.beans.Field;


public class QueryResult {

	@Field
	private String id;
	
	@Field
	private String name;
	
	@Field
	private String manu;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManu() {
		return manu;
	}
	public void setManu(String manu) {
		this.manu = manu;
	}
}
