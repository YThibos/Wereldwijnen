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
		
		<label><span>${fouten.mandje}</span></label>
		
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
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>Totaal: ${totaalTeBetalen}</td>
					</tr>
				</tbody>
			
			</table>
			
		</c:if>
			
		<form action="" method="post">
			
			<label><span>${fouten.naam}</span>
					Naam
				<input name="naam" type="text" required value="${param.naam}" size="50">
			</label>
			
			<label><span>${fouten.straat}</span>
				Straat
				<input name="straat" type="text" required value="${param.straat}" size="50">
			</label>
				
			<label><span>${fouten.huisnummer}</span>
				Huisnummer
				<input name="huisnummer" type="text" required value="${param.huisnummer}" size="50">
			</label>
				
			<label><span>${fouten.postcode}</span>
				Postcode
				<input name="postcode" type="text" required value="${param.postcode}" size="50">
			</label>
				
			<label><span>${fouten.gemeente}</span>
				Gemeente
				<input name="gemeente" type="text" required value="${param.gemeente}" size="50">
			</label>
		
			<c:forEach items="${bestelwijzen}" var="bestelwijze">
			
				<div>
					<label>
					<span>${fouten.bestelwijze}</span> 
					<input type="radio"	name="bestelwijze" value="${bestelwijze}" ${param.bestelwijze==bestelwijze ? "checked" : ""}>
						${bestelwijze }
					</label>
				</div>
				
			</c:forEach>
			
			<input type="submit" value="Als bestelbon bevestigen">
		
		</form>
		
	</section>
	
</body>

</html>