<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Engine</title>
</head>
<body background="/WEB-INF/img/background.jpg" >
<div id="formDiv">
<form action="KeywordResultServlet" method="post" name="keywordForm" id="keywordForm">
<table>
<tr>
<td>Keyword:</td>
<td><input type = "text" name = "keyword" /></td> 
</tr>
</table>
<input type="submit" value="Get Information" ></input>
</form>
</div>
<div id="resultsDiv">
<%
String success="failure";
success= (String)request.getAttribute("result");
if("success".equals(success))
{
%>
Query Details: Blah blah blah....
<%
}
%>
</div>
</body>
</html>