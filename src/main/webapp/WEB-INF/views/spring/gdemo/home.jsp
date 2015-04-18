<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>GDemo Website</title>
  <%@ include file="/WEB-INF/views/spring/gdemo/common/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/spring/gdemo/common/menu.jsp" %>
<div id="content">
  <div class="container">
    <div class="panel panel-primary">
	  <div class="panel-heading">
        <h3 class="panel-title"> <i class="fa fa-html5"></i>  HTML5</h3>
      </div>
	  <div class="panel-body" style="min-height:50px;padding:8px;">
	    1. HTML History : 2014 HTML5 W3C (World Wide Web Consortium) Final Recommendation, 2012 HTML5 WHATWG (Web Hypertext Application Technology Working Group) Living Standard, 2008 HTML5 WHATWG First Public Draft,
	       2000 W3C Recommended XHTML 1.0, 1999 W3C Recommended HTML 4.01, 1997 W3C Recommended HTML 3.2      <br/>
		2. Initial WHATWG HTML5 specifiction : HTML5 Markup, Web Messaging, Canvas 2D, Audio, Video, Drag and Drop; Web Sockets, Web Workers; (Micro Data)<br/>
		3. W3C HTML5 specification : Web Storage, HTML + RDFa; HTML 5.1; (Web SQL)<br/>
		4. HTML & related technologies : CSS3, WOFF 1.0, Animation Timing, Touch Events, WAI-ARIA, Geo Location, RDFa, User Timing, Navigation Timing, Selectors L1, SVG, MathML 3.0;
		 Indexed Database, Media Capture; Battery Status, File API; Device Orientation, XmlHTTPRequest 1; Javascript, WebGL
	  </div>
	  <div class="panel-footer" style="font-size:12px;">
	  	Related URL : <a href="http://www.w3.org/TR/html5/" target="_blank">W3C HTML5</a>,  <a href="http://www.w3schools.com/angular/default.asp" target="_blank">W3school HTML5</a>
	  </div>
	</div>
	
	<div class="panel panel-primary">
	  <div class="panel-heading">
        <h3 class="panel-title">AngularJS</h3>
      </div>
	  <div class="panel-body" style="min-height:50px;padding:8px;">
	    1. AngularJS lets you extend HTML vocabulary for your application. The resulting environment is extraordinarily expressive, readable, and quick to develop.
	    <br/>
	    2. AngularJS is a toolset for building the framework most suited to your application development. It is fully extensible and works well with other libraries. Every feature can be modified or replaced to suit your unique development workflow and feature needs. Read on to find out how.
	  </div>
	  <div class="panel-footer" style="font-size:12px;">
	  	Related URL : <a href="https://angularjs.org" target="_blank">AngularJS Official</a>
	  </div>
	</div>
  </div>
</div>
<script> 
$(document).ready(function() {
  
});
</script>
</body>
</html>