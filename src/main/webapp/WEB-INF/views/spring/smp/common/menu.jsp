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
      <c:if test="${user==null}">
        <a class="navbar-brand" href="#">System Manage Platform</a>
      </c:if>
      <c:if test="${user!=null && fn:length(user.subsystemIds)==32}">
        <a class="navbar-brand" href="#">${user.currentSubsystemName}</a>
      </c:if>
      <c:if test="${user!=null && fn:length(user.subsystemIds)>32}">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a class="navbar-brand dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-expanded="false">${user.currentSubsystemName}</a>
            <ul class="dropdown-menu" role="menu">
              <c:forTokens items="${user.subsystemIdNameAndHomeUrls}" delims="," var="subsystemIdNameAndHomeUrl">
                <c:set var="arr" value="${fn:split(subsystemIdNameAndHomeUrl, '_')}" />
                <c:if test="${arr[0]!=user.currentSubsystemId}">
				  <li><a href="<c:url value='${arr[2]}?subsystemId=${arr[0]}&subsystemName=${arr[1]}'/>">${arr[1]}&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
				</c:if>
			  </c:forTokens>
            </ul>
          </li>
        </ul>
      </c:if>
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
        <li class="dropdown <c:if test="${fn:endsWith(pageContext.request.requestURI, '/config.jsp')}">active</c:if>">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
          	<i class="fa fa-cog fa-fw"></i> Setting <span class="caret"></span>
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<c:url value='/spring/smp/config'/>">System Parameter</a></li>
            <li style="padding:10px;color:gray;">Json Data</li>
            <li><a href="<c:url value='/spring/cm/websitePage/1/5'/>" target="_blank">Websites</a></li>
            <li style="padding:10px;color:gray;">Web Page</li>
            <li><a href="<c:url value='/spring/am/subsystem?pageNo=1&pageSize=50'/>" target="_blank">Subsystem</a></li>
            <li><a href="<c:url value='/spring/am/role?pageNo=1&pageSize=50'/>" target="_blank">Role</a></li>
            <li><a href="<c:url value='/spring/am/user?pageNo=1&pageSize=50'/>" target="_blank">User</a></li>
            <li><a href="<c:url value='/spring/bm/config?pageNo=1&pageSize=50'/>" target="_blank">Config</a></li>
            <li><a href="<c:url value='/spring/cm/website?pageNo=1&pageSize=50'/>" target="_blank">Website</a></li>
            <li><a href="<c:url value='/spring/cm/article?pageNo=1&pageSize=50'/>" target="_blank">Article</a></li>
            <li><a href="<c:url value='/spring/cm/image?pageNo=1&pageSize=50'/>" target="_blank">Image</a></li>
            <li><a href="<c:url value='/spring/cm/video?pageNo=1&pageSize=50'/>" target="_blank">Video</a></li>
            <li><a href="<c:url value='/spring/cm/file?pageNo=1&pageSize=50'/>" target="_blank">File</a></li>
          </ul>
        </li>
        <li><a href="<c:url value='/spring/logout'/>"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>