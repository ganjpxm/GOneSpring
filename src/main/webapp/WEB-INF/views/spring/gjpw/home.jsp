<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>MMP</title>
</head>
<body>
<%
   // New location to be redirected
   String site = new String("http://www.photofuntoos.com");
   response.setStatus(response.SC_MOVED_TEMPORARILY);
   response.setHeader("Location", site); 
%>
</body>
</html>