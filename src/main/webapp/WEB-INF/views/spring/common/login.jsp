<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
  <meta name="author" content="Gan Jianping"/>
  <title>GOne Login</title>
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
	<form id="login-form" action="<c:url value='/spring/login'/>" method="post">
	  <div class="form-title">GOne</div>
	  <div id="login-error-info" style="color:red;margin-top:10px;margin-bottom:-15px;text-align:center;"></div>
	  <div class="form-group" <c:if test="${userCd!=null}">style='display:none;'</c:if>>
	  <label class="control-label display-label">User ID</label>
	  <div class="input-icon">
		<i class="fa fa-user"></i>
		<input id="user-cd-email-mobile-number" name="userCdOrEmailOrMobileNumber"
		  class="form-control placeholder-no-fix" type="text" placeholder="eg: ganjp or ganjpxm@gmail.com or +65 8888 0000" value="${userCd}"/>
		</div>
	  </div>
	  <div class="form-group">
		<label class="control-label display-label">Password</label>
		<div class="input-icon">
			<i class="fa fa-lock"></i>
			<input id="password" name="password" class="form-control placeholder-no-fix" type="password" placeholder="eg: 1234" />
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
<script type="text/javascript" src="<c:url value="/resources/bootstrap/v3.3.2/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/other/modalLoading.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jp/jp.js" />"></script> 
</body>
</html>
<script type="text/javascript">
function login() {
  if ($('#login-form').validate().form()) {
	  var loading = $(document.body).modalLoading(100, 'Processing...');
	  $("#login-form").ajaxSubmit({dataType:'json', success : function(data) {
		if (data.result=="success") {
		  window.location.href = data.url;	
		} else {
		  loading.remove();
		  $("#login-error-info").html("User ID or password is wrong.");
		}
	  }});  
  }
}
$(document).ready(function(){
  $('#login-form').validate({
	errorElement: 'span', //default input error message container
	errorClass: 'help-block', // default input error message class
	focusInvalid: false, // do not focus the last invalid input
	rules: {
	  userCdOrEmailOrMobileNumber: {required: true},
	  password: {required: true},
	},
	messages: {
	  userCdOrEmailOrMobileNumber: {
	    required: "User ID is required."
	  },
	  password: {
	    required: "Password is required."
	  }
	},
	invalidHandler: function (event, validator) { $('.alert-error', $('.login-form')).show();},
	highlight: function (element) {$(element).closest('.form-group').addClass('has-error');},
    success: function (label) {label.closest('.form-group').removeClass('has-error'); label.remove(); },
    errorPlacement: function (error, element) {error.insertAfter(element.closest('.input-icon'));},
    submitHandler: function (form) {form.submit();}
  });
  $('#login-form input').keypress(function (e) {
	if (e.which == 13) {
	  login();
	}
  });
   
  $("#user-cd-email-mobile-number,#password").focus(function() {
	  $("#login-error-info").html("");
  });
});
</script>
