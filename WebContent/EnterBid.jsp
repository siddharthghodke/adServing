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
<form action="SearchServlet" method="post" name="queryForm" id="queryForm" onsubmit="successMsg()">
<table>
<tr>
<td>Keyword:</td>
<td><input type="text" name="queryInput"></input> </td>
</tr>
<tr>
<td>Bid Amount:</td>
<td><input type = "text" name = "bidAmt" /></td>
</tr>
<tr>
<td>Advertisement Link:</td>
<td><input type = "text" name = "advLink" /></td> 
</tr>
</table>
<input type="submit" value="Submit"></input>
</form>
</div>
<div id="resultsDiv">
</div>
<script>
function successMsg() {
    alert("Your Bid is placed! Thank You!");
    document.forms['queryForm'].reset();
}
</script>
</body>
</html>