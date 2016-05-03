<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>


<!DOCTYPE HTML>
<html>
<head>
	<v:head title="Wereldwijnen"/>
</head>

<body>
	
	<!-- HEADER (banner + menu) START -->
	<header class="pageheader">

		<img alt="banner" src="<c:url value="/images/intro.jpg" />">
	
		<nav class="landenmenu">
		<ul class="zonderbolletjes">
		<c:forEach items="${landen}" var="land">
	
			<c:url value="/index.htm" var="wijnenUitLandURL">
				<c:param name="landid" value="${land.id}" />
			</c:url>
			<c:url value="/images/${land.id}.png" var="landAfbeelding"/>
				
			<li>
				<a href="${wijnenUitLandURL}"><img alt="${land.naam}" src="${landAfbeelding}"></a>
			</li>
	
		</c:forEach>
		</ul>
		</nav>
	</header>
	<!-- HEADER END -->
	
	<!-- PAGE CONTENT START -->
	<div class="pagecontent">
		
		<c:if test="${not empty soorten}">
			<h3>Soorten uit ${gevraagdLand}</h3>
			
			<ul>
			<c:forEach items="${soorten}" var="soort">
				<c:url value="/index.htm" var="soortURL">
					<c:param name="soortid" value="${soort.id}"/>
				</c:url>
				<li><a href="${soortURL}">${soort}</a></li>
			</c:forEach>
			</ul>
		</c:if>
		
	</div>

</body>
</html>