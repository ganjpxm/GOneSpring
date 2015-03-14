<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div style="background-color:white;">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Mobile Manage Platform</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/device.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/mmp/subsystem'/>"><i class="fa fa-mobile fa-fw"></i> Device</a>
        </li>
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/role.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/mmp/role'/>"><i class='fa fa-user-secret fa-fw'></i> Location</a></li>
      </ul>
    </div>
  </div>
</nav>
</div>