<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function loadResults()
	{
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("resultsDiv").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("POST","KeywordResultServlet",false);
	xmlhttp.send();	
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Engine</title>
</head>
<body background="/WEB-INF/img/background.jpg" >
<div id="formDiv">
<form action="KeywordResultServlet" method="post" name="queryForm" id="queryForm">
<table>
<tr>
<td>Keyword:</td>
<td><input type = "text" name = "keyword" /></td> 
</tr>
</table>
<button type="button" value="Submit" onclick=loadResults() ></button>
</form>
</div>
<div id="resultsDiv">AjaX will change this...

</div>
</body>
</html>