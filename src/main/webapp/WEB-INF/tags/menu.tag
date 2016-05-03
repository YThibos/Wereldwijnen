<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<nav class="landenmenu">
<h1>Wereldwijnen</h1>
<ul>
	<c:forEach items="${landen}" var="land">
	
	<c:url value="/index.htm" var="wijnenUitLandURL">
		<c:param name="landid" value="${land.id}" />
	</c:url>
	<c:url value="/images/${land.id}.png" var="landAfbeelding"/>
				
	<li>
		<a href="${wijnenUitLandURL}"><img alt="${land.naam}" src="${landAfbeelding}"></a>
	</li>
	
	</c:forEach>
	
	<c:if test="${not empty mandje}">
		<li><a href="<c:url value="/mandje.htm" />"><img alt="mandje" src="<c:url value="/images/mandje.png" />"></a></li>
	</c:if>
</ul>
</nav>