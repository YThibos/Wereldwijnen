<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
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
		<v:menu/>
	</header>
	<!-- HEADER END -->
	
	<!-- PAGE CONTENT START -->
	<section class="pagecontent">
	
		<label><span>${idfout}</span></label>
		
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
		
		<c:if test="${not empty wijnen}">
			<!--  NIET SAFE !!!!!!!!!!!!!!!! UNCHECKED IN SERVLET -->
			<h4>Jaartallen van ${gevraagdeSoort}</h4>
			
			<ul>
			<c:forEach items="${wijnen}" var="wijn">
				<c:url value="/wijnDetail.htm" var="wijnDetailURL">
					<c:param name="id" value="${wijn.id}"/>
				</c:url>
				<li><a href="${wijnDetailURL}">${wijn.jaar}</a>${wijn.sterrenBeoordeling}</li>
			</c:forEach>
			</ul>
		
		</c:if>
		
		<c:if test="${empty soorten and empty wijnen }">
			<h1>Welkom bij Wereldwijnen</h1>
			<p>Klik op bovenstaande vlaggetjes om te bladeren tussen de verschillende landen en wijnen.</p>
		</c:if>
		
	</section>

</body>
</html>