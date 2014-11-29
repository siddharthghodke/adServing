<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="edu.buffalo.ktmb.bean.QueryResult"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	List<QueryResult> resultSet;
	resultSet = (List<QueryResult>) request.getAttribute("result");
	Map<String, Map<String, List<String>>> snippetMap = (Map<String, Map<String, List<String>>>) request.getAttribute("snippetMap");
	
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NOVA Search</title>
</head>
<body background="background.jpg">
	<div>
		<img src="logo.jpg" style="width: 30%; height: 3%; margin-left: 35%;">
		<br> <br>

		<FORM NAME="MainForm" METHOD="POST" action="SearchServlet"
			style="width: 100%; margin-left: 15%;">
			<input type="text" name="userQuery"
				style="width: 60%; border: 1px solid black;"> <INPUT
				TYPE="SUBMIT" Style="border: 1px solid black;" VALUE="Search">
		</FORM>

	</div>

	<div
		style="float: left; width: 60%; word-wrap: break-word; overflow: hidden;">
		<ul style="list-style-type: none;">
		<%
			if (resultSet != null) {
				for (int i = 0; i < resultSet.size(); i++) {
		%>
			<li><a href="<%=resultSet.get(i).getUrl() %>"><%=resultSet.get(i).getTitle()%></a></li>
			<li><%=resultSet.get(i).getUrl() %></li>
			<%
					String snippet = "";
					if(snippetMap != null) {
						Map<String, List<String>> fieldSnippet = snippetMap.get(resultSet.get(i).getUrl());
						if(fieldSnippet != null) {
							List<String> snippetList = fieldSnippet.get("full_text");
							if(snippetList != null && snippetList.size() > 0) {
								for(String sn: snippetList) {
									snippet += sn;
								}
								System.out.println(snippet);
								snippet = snippet.replaceAll("<em>", "<b>");
								snippet = snippet.replaceAll("</em>", "</b>");
								System.out.println("NEW:" + snippet + "\n\n");
							}
						}
					}
					if(snippet.isEmpty() || "".equals(snippet)) {
						snippet = resultSet.get(i).getLead_paragraph();
					}
			%>
			<br />
			<li><%=snippet%></li>
			<br />
			<li />
			<%
				}
			}
			%>
		</ul>
	</div>

	<div style="float: right; width: 35%; word-wrap: break-word;">
		<ul style = "list-style-type: none;">
		<%
			List<String> adList = (List<String>) request.getAttribute("adList");
			if(adList != null) {
				for(String ad: adList) {
		%>
			<li><a href = "<%=ad%>"><%=ad%></a></li>
			<li />
		<% 
				}
			}
		%>
		</ul>
	</div>





</body>

</html>
