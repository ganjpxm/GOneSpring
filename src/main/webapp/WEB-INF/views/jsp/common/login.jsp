<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
  <meta name="author" content="Gan Jianping"/>
  <title>Login</title>
  <link rel="shortcut icon" href="<c:url value='/resources/jp/image/favicon.png'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/bootstrap/v3.3.2/css/bootstrap.min.css'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/font-awesome/v4.3.0/css/font-awesome.min.css'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/other/style-metronic.css'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/jp/jp.css'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/jp/jp-login.css'/>"/>
</head>
<body>
<div class="login">
  <div class="content">
	<form id="login-form" action="<c:url value='/${orgCd}/driver/login'/>" method="post">
	  <div class="form-title">GOne</div>
	  <div id="login-error-info" style="color:red;margin-top:10px;margin-bottom:-15px;text-align:center;"></div>
	  <div class="form-group" <c:if test="${userCd!=null}">style='display:none;'</c:if>>
	  <label class="control-label display-label">User ID or Email</label>
	  <div class="input-icon">
		<i class="fa fa-user"></i>
		<input id="userCdOrEmail" name="userCdOrEmail"
		  class="form-control placeholder-no-fix" type="tel" autocomplete="off" placeholder="eg: ganjp or ganjpxm@gmail.com" value="${userCd}"/>
		</div>
	  </div>
	  <div class="form-group">
		<label class="control-label display-label">Password</label>
		<div class="input-icon">
			<i class="fa fa-lock"></i>
			<input id="userPassword" name="userPassword" class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="eg: 1234" />
		</div>
	  </div>
	  <div class="form-groups" style="padding-bottom:30px;margin-top:30px;text-align:center;">
	    <button id="loginBtn" type="button" class="btn teal" style="height:40px;width:100%;" onclick="login();">Login</button>            
	  </div>
	</form>
  </div>
  <div id="forgot_password" style="color:white;text-align:center;">
 	  <a href="javascript:showForgetPasswordFrame();" class="navbar-link" style="color:white;display:none;">Forgot Password?</a>
  </div>
</div>
<div class="copyright">© 2015 Gan Jianping. All rights reserved.</div>
  
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/jquery.form-3.51.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryvalidation/1.13.1/jquery.validate.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/other/modalLoading.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jp/jp.js" />"></script> 
</body>
</html>
<script type="text/javascript">
function login() {
  if ($('#loginForm').validate().form()) {
	  var loading = $(document.body).modalLoading(100, 'Processing...');
	  $("#loginForm").ajaxSubmit({dataType:'json', success : function(data) {
		if (data.result=="success") {
		  //var userCdOrEmail = $("#userCdOrEmail").val();
		  var userPassword = $("#userPassword").val();
		  /* if (userCd==userCdOrEmail) {
			  setCookie("ltUserCdP", userCdOrEmail + userPassword, 7);
		  } */
		  // setCookie("ltUserP", userPassword, 7);
		  localStorage.setItem("ltUserP", userPassword);
		  window.location.href = "<c:url value='/${orgCd}/driver/home'/>";	
		} else {
		  loading.remove();
		  $("#loginBtn").removeAttr("disabled");	
		  $("#login-error-info").html("Password is wrong.");
		}
	  }});  
  }
}
$(document).ready(function(){
  $('#loginForm').validate({
	errorElement: 'span', //default input error message container
	errorClass: 'help-block', // default input error message class
	focusInvalid: false, // do not focus the last invalid input
	rules: {
	  userCdOrEmail: {required: true},
	  userPassword: {required: true},
	},
	messages: {
	  username: {
	    required: "Username is required."
	  },
	  userPassword: {
	    required: "Password is required."
	  }
	},
	invalidHandler: function (event, validator) { $('.alert-error', $('.login-form')).show();},
	highlight: function (element) {$(element).closest('.form-group').addClass('has-error');},
    success: function (label) {label.closest('.form-group').removeClass('has-error'); label.remove(); },
    errorPlacement: function (error, element) {error.insertAfter(element.closest('.input-icon'));},
    submitHandler: function (form) {form.submit();}
  });
  $('#loginForm input').keypress(function (e) {
	if (e.which == 13) {
	  login();
	}
  });
   
  $("#userCdOrEmail,#userPassword").focus(function() {
	  $("#login-error-info").html("");
  });
  
  if (userCd!="") {
	$("#userPassword").focus();
	
	var userPassword = localStorage.getItem("ltUserP");
	if (userPassword) {
		$("#userPassword").val(userPassword);
		login();
	}
  }
});
</script>
