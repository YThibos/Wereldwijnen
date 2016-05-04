<%@ page contentType='text/html' pageEncoding='UTF-8' session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
	<v:head title="Bestelling ${wijn}"/>
</head>

<body>

	<!-- HEADER (banner + menu) START -->
	<header class="pageheader">
		<v:menu/>
	</header>
	<!-- HEADER END -->
	
	<section class="pagecontent">
	
		<h1>Wijn toevoegen aan mandje</h1>
		
		<a href="<c:url value="/index.htm" />" >Terug naar overzicht</a>

		<label><span>${fouten.id}</span></label>
		
		<c:if test="${not empty wijn}">
		<dl>
			<dt>Land</dt>
			<dd>${wijn.soort.land}</dd>
			
			<dt>Soort</dt>
			<dd>${wijn.soort}</dd>
			
			<dt>Jaar</dt>
			<dd>${wijn.jaar}</dd>
			
			<dt>Beoordeling</dt>
			<dd>${wijn.sterrenBeoordeling}</dd>
			
			<dt>Prijs</dt>
			<dd>${wijn.prijs}</dd>
		</dl>
		
		<form action="" method="post" id="toevoegform">
			<label><span>${fouten.input}</span>
			Aantal flessen
			<input name="aantalFlessen" type="number" min="1" required></label>
			<input type="hidden" name="id" value="${wijn.id}">
			
			<input type="submit" value="Toevoegen" id="toevoegknop">
		</form>
		
		</c:if>
	
	</section>

</body>

<script>
	document.getElementById('toevoegform').onsubmit = function() {
		if ( ! navigator.cookieEnabled) {
			alert("Dit werkt enkel als cookies aanstaan"); 
			return false; 
		}
		document.getElementById('toevoegknop').disabled=true;
	};
</script>

</html>