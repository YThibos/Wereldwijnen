<%@ page contentType='text/html' pageEncoding='UTF-8' session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
	<v:head title="Mandje"/>
</head>

<body>
	
	<!-- HEADER (banner + menu) START -->
	<header class="pageheader">
		<v:menu/>
	</header>
	<!-- HEADER END -->
	
	<section class="pagecontent">
	
		<h1>Mandje</h1>
		
		<a href="<c:url value="/index.htm" />" >Terug naar overzicht</a>
		
		<c:if test="${not empty bestellijnen}">
			<table>
			
				<thead>
					<tr>
						<th>Wijn</th>
						<th>Prijs</th>
						<th>Aantal</th>
						<th>Te betalen</th>
					</tr>
				</thead>
				
				<tbody>
				<c:forEach items="${bestellijnen}" var="bestellijn">
					<tr>
						<td>${bestellijn.wijn.soort.land} ${bestellijn.wijn.soort} ${bestellijn.wijn.jaar}</td>
						<td>${bestellijn.wijn.prijs}</td>
						<td>${bestellijn.aantal}</td>
						<td>${bestellijn.totaalPrijs}</td>
					</tr>
				</c:forEach>
				</tbody>
			
			</table>
		</c:if>
	
	
	</section>

</body>

</html>