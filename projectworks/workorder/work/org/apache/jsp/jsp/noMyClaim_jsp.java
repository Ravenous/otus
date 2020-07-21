/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.8
 * Generated at: 2020-07-21 09:31:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.avaya.sce.runtime.*;
import com.avaya.sce.runtime.html.genmodel.*;
import com.avaya.sce.runtimecommon.*;
import java.util.*;

public final class noMyClaim_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


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

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("com.avaya.sce.runtime.html.genmodel");
    _jspx_imports_packages.add("com.avaya.sce.runtimecommon");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.avaya.sce.runtime");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

response.addHeader("X-Frame-Options", "SAMEORIGIN");
SCESession mySession = (SCESession)request.getAttribute("session");

      out.write("\r\n");
      out.write("<!DOCTYPE html> \r\n");
      out.write("<html> \r\n");
      out.write("<head> \r\n");
      out.write("\t<title>OD Web Form</title>\t\r\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \r\n");
      out.write("\t\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/jquery.mobile-1.4.5.css\"/>\r\n");
      out.write("\t\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/avaya.css\"/>\r\n");
      out.write("\t\r\n");
      out.write("\t<script src=\"js/jquery.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<script src=\"js/jquery.mobile-1.4.5.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script src=\"js/avaya.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("\t<script src=\"js/jquery.mobile.avayamsg.js\"></script>\r\n");
      out.write("\t<script src=\"js/jquery.validate.js\"></script>\r\n");
      out.write("\t");
 if (!mySession.getCurrentLanguage().equals("english")) { 
      out.write("\r\n");
      out.write("\t<script src=\"");
      out.print(mySession.getCurrentLanguage());
      out.write("/jquery.validate.messages.js\"></script>\r\n");
      out.write("\t");
 } 
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("</head> \r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div data-role=\"page\" >\r\n");
      out.write("\t<script type='text/javascript'>\r\n");
      out.write("     \t$(document).ready(function() { \r\n");
      out.write("\t\t\t$('#form1').validate();\r\n");
      out.write("\t\t\tif ($(\"#message\")){\r\n");
      out.write("\t\t\t\t$(\"#message\").avayaMessage();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("\t");
	
	String message = mySession.getVariable("ddLastException").getComplexVariable().getField("message").getStringValue();
	String messageName = mySession.getVariable("ddLastException").getComplexVariable().getField("errorcode").getStringValue();
	if (message.length() > 0){
		if (messageName.length() > 0){
			message = message + " (" + messageName + ")";
		}
	
      out.write("\r\n");
      out.write("\t\t<div id=\"message\">");
      out.print(message);
      out.write("</div>\r\n");
      out.write("\t");

	}
	
      out.write("\t\r\n");
      out.write("\t<div data-role=\"content\" >\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t");
//writePrompt
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//		Jsp method to display prompt element dynamically. The way a prompt is displayed on the web
// 		page is all dictated by this method.
////////////////////////////////////////////////////////////////////////////////////////////////////////

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	  //		Generate UI element from the call flow
	  ////////////////////////////////////////////////////////////////////////////////////////////////////////
	
      out.write('\r');
      out.write('\n');
      out.write('	');
 Submit submit = (Submit)request.getAttribute("submit"); 
      out.write('\r');
      out.write('\n');
      out.write('	');
 WebForm webForm = (WebForm)request.getAttribute("webform"); String checked; String[] choiceNames; int i;
      out.write("\r\n");
      out.write("\t<form id=\"form1\" action=\"");
      out.print((submit != null)?submit.getNext():"");
      out.write("\" method=\"POST\" data-ajax=\"false\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t");
 writePrompt(mySession, "noMyClaim", TextDisplay.NameType.CONST, out); 
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t \r\n");
      out.write("\t\t \r\n");
      out.write("\t\t \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t");

			String backBtnLabel = "Back";
			if ("".length() > 0){
				backBtnLabel = webForm.getLabelText(mySession, "").getText();
			}
			String nextBtnLabel = "Next";
			if ("".length() > 0){
				nextBtnLabel = webForm.getLabelText(mySession, "").getText();
			}			
		
      out.write("\r\n");
      out.write("\t\t");
 String lastPage = (String)mySession.getProperty("___OD_parameter_web_page_last"); 
		   if (lastPage != null){
		
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t   <a onclick=\"location.href='");
      out.print(lastPage);
      out.write("'\" data-role=\"button\" data-icon=\"arrow-l\" data-iconpos=\"left\" data-inline=\"true\">");
      out.print(backBtnLabel);
      out.write("</a>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t");
} 
      out.write("\r\n");
      out.write("\t\t");
 if (submit != null) {
      out.write("\r\n");
      out.write("\t\t<button type=\"submit\" data-theme=\"b\" data-icon=\"arrow-r\" data-iconpos=\"right\" data-inline=\"true\">");
      out.print(nextBtnLabel);
      out.write("</button>\r\n");
      out.write("\t\t");
} 
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
