<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="edu.buffalo.ktmb.bean.QueryResult"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function updateAdClicks(obj) {
		document.getElementById('clickedAdID').value = obj.getAttribute("href");
		document.getElementById('postReq').value = "adUpdate";
		document.MainForm.submit();
		return false;
	}
	
	function searchClick(obj) {
		document.getElementById('postReq').value = "search";
		document.MainForm.submit();
		return false;
	}
</script>
<%
	List<QueryResult> resultSet;
	String userQuery;
	userQuery = (String)request.getAttribute("userQuery");
	resultSet = (List<QueryResult>) request.getAttribute("result");
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
			<% if (userQuery == null) {%>
			<input type="text" id="userQ" name="userQuery"	style="width: 60%; border: 1px solid black;"> 
			<input type = "hidden" id= "postReq" name = "postRequest" value = "search" />
			
			<%}
			 else {%>
			 <input type="text" id="userQ" name="userQuery"	style="width: 60%; border: 1px solid black;" value="<%=userQuery%>">
			 <input type = "hidden" id= "postReq" name = "postRequest" value = "adUpdate" />
			 
		 <%} %> 
			 
			<INPUT TYPE="button" Style="border: 1px solid black;" VALUE="Search" onclick="searchClick(this); return false;">
			<input type="hidden" id="clickedAdID" name="clickedAd" />
		</FORM>

	</div>

	<div
		style="float: left; width: 60%; word-wrap: break-word; overflow: hidden;">
		<ul style="list-style-type: none;">
			<%
				if (resultSet != null) {
					for (int i = 0; i < resultSet.size(); i++) {
			%>
			<li><a href="<%=resultSet.get(i).getUrl()%>"><%=resultSet.get(i).getTitle()%></a></li>
			<li><%=resultSet.get(i).getUrl()%></li>
			<li><%=resultSet.get(i).getLead_paragraph()%></li>
			<li />
			<li />
			<%
				}
				}
			%>
		</ul>
	</div>

	<div style="float: right; width: 35%; word-wrap: break-word;">
		<ul style="list-style-type: none;">
				<%
					List<String> adList = (List<String>) request.getAttribute("adList");
					if (adList != null) {
						for (String ad : adList) {
				%>
				<li><a href="<%=ad%>"
					onclick="updateAdClicks(this); return false;"><%=ad%></a></li>
				<li />
				<%
					}
					}
				%>
				
		</ul>
	</div>





</body>

</html>
