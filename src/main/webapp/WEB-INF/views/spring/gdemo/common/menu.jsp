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
        <a class="navbar-brand" href="<c:url value='/spring/gdemo'/>">GDemo</a>
      </c:if>
      <c:if test="${user!=null && fn:length(user.subsystemIds)==32}">
        <a class="navbar-brand" href="<c:url value='/spring/gdemo'/>">${user.currentSubsystemName}</a>
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
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown <c:if test="${fn:endsWith(pageContext.request.requestURI, '/html5/basic.jsp')}">active</c:if>">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-html5"></i> HTML5 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<c:url value='/spring/gdemo/html5/basic'/>">&nbsp;&nbsp;Basic</a></li>    
          </ul>
        </li>
        <li class="dropdown <c:if test="${fn:endsWith(pageContext.request.requestURI, '/angularjs/basic.jsp')}">active</c:if>">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Angular JS <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <!-- <li style="padding:10px;color:gray;"></li> -->
            <li><a href="<c:url value='/spring/gdemo/angularjs/basic'/>">&nbsp;&nbsp;Basic</a></li>    
          </ul>
        </li>
        <c:if test="${user!=null}">
          <li><a href="<c:url value='/spring/logout'/>"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
</div>