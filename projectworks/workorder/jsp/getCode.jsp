<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.avaya.sce.runtime.*, com.avaya.sce.runtime.html.genmodel.*, com.avaya.sce.runtimecommon.*, java.util.*" %>
<%
response.addHeader("X-Frame-Options", "SAMEORIGIN");
SCESession mySession = (SCESession)request.getAttribute("session");
%>
<!DOCTYPE html> 
<html> 
<head> 
	<title>OD Web Form</title>	
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	
	<link rel="stylesheet" href="css/jquery.mobile-1.4.5.css"/>
	
	<link rel="stylesheet" href="css/avaya.css"/>
	
	<script src="js/jquery.js"></script>
	
	
	<script src="js/jquery.mobile-1.4.5.js"></script>
	
	<script src="js/avaya.js"></script>
	
	<script src="js/jquery.mobile.avayamsg.js"></script>
	<script src="js/jquery.validate.js"></script>
	<% if (!mySession.getCurrentLanguage().equals("english")) { %>
	<script src="<%=mySession.getCurrentLanguage()%>/jquery.validate.messages.js"></script>
	<% } %>
	
</head> 
<body>

<div data-role="page" >
	<script type='text/javascript'>
     	$(document).ready(function() { 
			$('#form1').validate();
			if ($("#message")){
				$("#message").avayaMessage();
			}
		});
	</script>
	<%	
	String message = mySession.getVariable("ddLastException").getComplexVariable().getField("message").getStringValue();
	String messageName = mySession.getVariable("ddLastException").getComplexVariable().getField("errorcode").getStringValue();
	if (message.length() > 0){
		if (messageName.length() > 0){
			message = message + " (" + messageName + ")";
		}
	%>
		<div id="message"><%=message%></div>
	<%
	}
	%>	
	<div data-role="content" >
	
	
	
	
	
	
	<%//writePrompt
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//		Jsp method to display prompt element dynamically. The way a prompt is displayed on the web
// 		page is all dictated by this method.
////////////////////////////////////////////////////////////////////////////////////////////////////////
%>
<%!
	private void writeElement(SCESession mySession, PromptElement element, JspWriter out) throws Exception{
		if (element.getType() == PromptElement.WEB_LOOP_VAR_COLL){
			PromptLoopElement loopElement = (PromptLoopElement)element;
			IVariable variable = mySession.getVariable(loopElement.getValue());
			ICollection collection = variable.getCollection();
			while(collection.hasMore()){
				for (PromptElement promptElem : loopElement.getChildren()){
					writeElement(mySession, promptElem, out);
				}
				collection.next();
			}
			for (PromptElement promptElem : loopElement.getChildren()){
				writeElement(mySession, promptElem, out);
			}
			collection.reset();
			collection.next();
		}
		if (element.getType() == PromptElement.HTMLTEXTELEMENT){
			out.println(element.getValue());
		}
		if (element.getType() == PromptElement.WEBELEMENT){
			String value = mySession.getVariableFieldValue(element.getValue());		
    		out.println("<label>" + element.getLabel() + "</label>");
   			if (element.getWebElemType().equals("Picture")){
   				value = element.getFileURLValue();
   				int width = element.getWidth();		   				
   				int height = element.getHeight();
   				if (width == 0 || height == 0){
   	    			out.println("<div><img src=\"" + value + "\"/></div>");
   	    		}else{
   	    			out.println("<div><img src=\"" + value + "\" width=\"" + width + "\" height=\"" + height + "\"/></div>");
   				}
   	    	} else if (element.getWebElemType().equals("Video")){
   	    		value = element.getFileURLValue();
   	    		int width = element.getWidth();		   				
   				int height = element.getHeight();
   				String controls = element.getAVControls();
   				if (width == 0 || height == 0){
   					out.println("<div><video " + controls + " src=\"" + value + "\"/></div>");
   				}else{
   					out.println("<div><video " + controls + " src=\"" + value + "\" width=\"" + width + "\" height=\"" + height + "\"/></div>");
   				}
   	    	} else if (element.getWebElemType().equals("Voice")){
   	    		value = element.getFileURLValue();
   	    		String controls = element.getAVControls();
   	    		out.println("<div><audio " + controls + " src=\"" + value + "\"/></div>");
   	    	} else if (element.getWebElemType().equals("Map") || element.getWebElemType().indexOf("Map") == 0){
   	    		String protocol = "http";
   	    		if (mySession.getRequest().isSecure()){
   	    			protocol = "https";
   	    		}
   	    		int width = element.getWidth();		   				
   				int height = element.getHeight();
   				if (width == 0) width = 400;
   				if (height == 0) height = 300;
   				String mapAPIKey = mySession.getParameter("mapAPIKey");
   				if (element.getWebElemType().contains("BAIDU")){   					
   					if (mapAPIKey != null && mapAPIKey.length() > 0){
   						mapAPIKey = "&ak=" + mapAPIKey;
   					}else{
   						mapAPIKey = "";
   					}
   					out.println("<div><img src=\"" + protocol + "://api.map.baidu.com/staticimage/v2?center=" + value + mapAPIKey + "&width=" + width + "&height=" + height + "&zoom=16&markers=" + value + "&markerStyles=-1," + protocol + "://api.map.baidu.com/images/marker_red.png\"/></div>");
   				}else if(element.getWebElemType().contains("OTHER")){
   					//For other map type, customers would have to customize the following code to make sure the static map url is generated according to 3rd party API docs.
   					if (mapAPIKey != null && mapAPIKey.length() > 0){
   						mapAPIKey = "&key=" + mapAPIKey;
   					}else{
   						mapAPIKey = "";
   					}
   					out.println("<div><img src=\"" + protocol + "://<domain>/<map url>?center=" + value + mapAPIKey + "&zoom=14&size=" + width + "x" + height + "&markers=color:red%7C" + value + "\"/></div>");   					
   				}else{
   					if (mapAPIKey != null && mapAPIKey.length() > 0){
   						mapAPIKey = "&key=" + mapAPIKey;
   					}else{
   						mapAPIKey = "";
   					}
   					out.println("<div><img src=\"" + protocol + "://maps.googleapis.com/maps/api/staticmap?center=" + value + mapAPIKey + "&zoom=14&size=" + width + "x" + height + "&markers=color:red%7C" + value + "\"/></div>");
   				}
   			} else if (element.getWebElemType().equals("Text")){
   	   			out.println("<input type=\"text\" value=\"" + value + "\" readonly/>");
   			} else if (element.getWebElemType().equals("TextArea")){
   				int width = element.getWidth();		   				
   				int height = element.getHeight();
   				out.println("<textarea rows=\"" + height + "\" cols=\"" + width + "\" readonly/>");
   				out.println(value);
   				out.println("</textarea>");
   			} 
		}
		if (element.getType() == PromptElement.VARIABLE_TEXT){
			String value = mySession.getVariableFieldValue(element.getValue());
			out.println(value);
		}
	}

	public void writePrompt(SCESession mySession, String promptName, TextDisplay.NameType type, JspWriter out) throws Exception{
		//Display prompt elements to show the input value dynamically
		List<PromptElement> elems = TextDisplay.getPromptElements(mySession, promptName, type);			
		for (PromptElement element : elems){
			writeElement(mySession, element, out);
   		}
	}
%>
	
	<%/////////////////////////////////////////////////////////////////////////////////////////////////////////
	  //		Generate UI element from the call flow
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////
	%>
	<% Submit submit = (Submit)request.getAttribute("submit"); %>
	<% WebForm webForm = (WebForm)request.getAttribute("webform"); String checked; String[] choiceNames; int i;%>
	<form id="form1" action="<%=(submit != null)?submit.getNext():""%>" method="POST" data-ajax="false" enctype="multipart/form-data">
					
			<div data-role="fieldcontain">
	        	<label for="code"><%=webForm.getL18nLabel("code", mySession, "mainMenu:code") %></label>
	        	<input type="text" name="code" id="code" required minlength="4" value="<%=mySession.getVariableFieldValue("code")%>"  />
			</div>			
			
		
		 
		 
		 
		
					
				
		<%
			String backBtnLabel = "Back";
			if ("".length() > 0){
				backBtnLabel = webForm.getLabelText(mySession, "").getText();
			}
			String nextBtnLabel = "Next";
			if ("".length() > 0){
				nextBtnLabel = webForm.getLabelText(mySession, "").getText();
			}			
		%>
		<% String lastPage = (String)mySession.getProperty("___OD_parameter_web_page_last"); 
		   if (lastPage != null){
		%>
		
		
			   <a onclick="location.href='<%=lastPage%>'" data-role="button" data-icon="arrow-l" data-iconpos="left" data-inline="true"><%=backBtnLabel%></a>
		
		<%} %>
		<% if (submit != null) {%>
		<button type="submit" data-theme="b" data-icon="arrow-r" data-iconpos="right" data-inline="true"><%=nextBtnLabel%></button>
		<%} %>
	</form>
	</div>
</div>
</body>
</html>