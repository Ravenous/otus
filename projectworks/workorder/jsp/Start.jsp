
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.avaya.sce.runtime.*, com.avaya.sce.runtimecommon.*, java.util.Collection" %>
<% response.addHeader("X-Frame-Options", "SAMEORIGIN"); 
SCESession mySession = (SCESession)request.getAttribute("session");
%>
<!DOCTYPE html> 
<html> 
<head> 
	<title>Start</title> 
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="css/jquery.mobile-1.4.5.css"/>
	
	<link rel="stylesheet" href="css/avaya.css"/>
	
	<script src="js/jquery.js"></script>
	
	
	<script src="js/jquery.mobile-1.4.5.js"></script>
	
	<script src="js/avaya.js"></script>
	
	<script src="js/jquery.mobile.avayamsg.js"></script>
	<script src="js/jquery.validate.js"></script>
	
</head> 
<body>
<div data-role="page" >
	<div data-role="content" >
	
	
	
	
		<% Submit submit = (Submit)request.getAttribute("submit"); %>
		<form action="<%=(submit.getNext())%>" method="get">
			
			<button type="submit" data-theme="b" data-icon="arrow-r" data-iconpos="right" data-inline="true">Next</button>
		</form>
	
	</div>
</div>
</body>
</html>