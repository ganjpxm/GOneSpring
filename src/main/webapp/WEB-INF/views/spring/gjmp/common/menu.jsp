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
        <a class="navbar-brand" href="#">GOne Manage Platform</a>
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
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-right">
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/website.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/gjmp/website'/>"><i class="fa fa-globe fa-fw"></i> Websites</a>
        </li>
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/article.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/gjmp/article'/>"><i class="fa fa-book fa-fw"></i> Articles</a>
        </li>
        <li <c:if test="${fn:endsWith(pageContext.request.requestURI, '/image.jsp')}">class="active"</c:if>>
        	<a href="<c:url value='/spring/gjmp/image'/>"><i class="fa fa-picture-o fa-fw"></i> Images</a>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">More <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
		    <li><a href="<c:url value='/spring/cm/website'/>" target="_blank"> Websites</a></li>
		    <li><a href="<c:url value='/spring/cm/article'/>" target="_blank"> Articles</a></li>
		    <li><a href="<c:url value='/spring/cm/image'/>" target="_blank"> Images</a></li>
          </ul>
        </li>
        <li><a href="<c:url value='/spring/logout'/>"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>	
      </ul>
    </div>
  </div>
</nav>
</div>