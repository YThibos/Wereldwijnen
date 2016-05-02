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
	</header>
	
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

</body>
</html>