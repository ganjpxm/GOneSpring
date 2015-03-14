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
      <a class="navbar-brand" href="#">System Manage Platform</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/subsystem.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/smp/subsystem'/>"><i class="fa fa-life-ring fa-fw"></i> Subsystem</a>
        </li>
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/role.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/smp/role'/>"><i class='fa fa-user-secret fa-fw'></i> Role</a></li>
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/user.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/smp/user'/>"><i class='fa fa-user fa-fw'></i> User</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-cog fa-fw"></i> Setting <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">System Config</a></li>
            <li><a href="#">Param</a></li>
          </ul>
        </li>
        <li><a href="<c:url value='/spring/free/logout'/>"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>