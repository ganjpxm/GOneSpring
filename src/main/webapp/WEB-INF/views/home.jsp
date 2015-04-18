<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="author" content="Gan Jianping">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
  <title>GOne Home</title>
  <link rel="shortcut icon" href="<c:url value='/resources/jp/image/favicon.png'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/jp/jp.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/jp/jp-blue-grey.css'/>">
</head>
<body style="margin:10px 20px;">
  <h1 style="text-align:center;">Welcome to GOne Platform</h1>
  <a href="<c:url value='/spring/login'/>" target="_blank">GOne Login</a><br/>
  <a href="<c:url value='/html/gjpw/home.html'/>" target="_blank">Gan Jianping Personal Website</a><br/>
  <a href="<c:url value='/spring/gdemo'/>" target="_blank">GDemo Website</a><br/>
</body>
</html>