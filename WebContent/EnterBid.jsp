<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NOVA Search</title>

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	<script>
		/* $(document).ready(function(){
			//alert("Welcome");
			
		}); */
		function getMinPrice() {
			//alert("Changed!");
			var query = $("#queryInput").val();
			var value = {'postRequest':'getMinPrice', 'queryInput':query};
			$.ajax({
				url:"SearchServlet",
				type:"POST",
				data: value,
				success :function(data) {
					//alert(data);
					$("#minBidPrice").html("   (Minimum Bid Price: $"+data+")");
				}
			});
			
		}
	</script>

	<%
	String success="failure";
	success= (String)request.getAttribute("result");
	if("success".equals(success))
	{
	%>
	<script type="text/javascript">
		alert("Your Bid is placed! Thank You!");
		document.forms['queryForm'].reset();
	</script>
	<% 
	}
	%>
	
</head>

<body background="/WEB-INF/img/background.jpg">
<img src="logo.jpg" style="width: 30%; height: 3%; margin-left: 35%;">
		<br> <br>
	<div id="formDiv" align="center" >
		<form action="BiddingServlet" method="post" name="queryForm"
			id="queryForm">
			<input type="hidden" name="postRequest" value="postBid" />
			<table style="font-family: sans-serif;">
				<tr>
					<td >Keyword:  </td>
					<td><input type="text" name="queryInput" id ="queryInput" onchange="getMinPrice();"/></td>
					 
					<td><div id="minBidPrice" style="font-size: 16px"></div></td>
				</tr>
				<tr>
					<td>Bid Amount:  </td>
					<td><input type="text" name="bidAmt" /></td>
				</tr>
				<tr>
					<td>Advertisement Link:  </td>
					<td><input type="text" name="advLink" /></td>
				</tr>
			</table>
			<br />
			<input type="submit" value="Submit" style="border: 1px solid black; font-family: sans-serif;"></input>
		</form>
	</div>
	<br />
	
	<div id="resultsDiv"></div>
	<!-- <form action="SearchServlet" method="post" name="queryBidPrice" id="queryBidPrice">
		<input type="hidden" name="postRequest" value="getMinPrice" />
	
	</form> -->

</body>
</html>