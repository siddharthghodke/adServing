<%@page import="edu.buffalo.ktmb.bean.Query"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Engine</title>
</head>
<body background="/WEB-INF/img/background.jpg">
	<div id="formDiv">
		<form action="KeywordResultServlet" method="post" name="keywordForm"
			id="keywordForm">
			<table>
				<tr>
					<td>Keyword:</td>
					<td><input type="text" name="keyword" /></td>
				</tr>
			</table>
			<input type="submit" value="Get Information" />
		</form>
	</div>
	<div id="resultsDiv">
		<%
			String success = (String) request.getAttribute("result");
			Query query = (Query) request.getAttribute("query");
			if ("success".equals(success) && query != null) {
				
		%>
		<br /><br />
		Query Details: <br />
		Query: <%=query.getQuery() %> <br />
		Query Hits: <%=query.getQueryHits() %> <br />
		Ad Hits: <%=query.getAdHits() %> <br />
		<%
			}
		%>
	</div>
</body>
</html>