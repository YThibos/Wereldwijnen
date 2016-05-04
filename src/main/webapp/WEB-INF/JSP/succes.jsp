<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>

<!DOCTYPE HTML>
<html>

<head>
	<v:head title="Gelukt!"/>
	<meta http-equiv="refresh" content="4;url=<c:url value="/index.htm" />" />
</head>

<body>

	<section class="pagecontent">
		<h1>Succes!</h1>
		<h3>Uw bon werd opgeslagen als bestelbon nummer ${param.id}</h3>
		<p>U wordt binnen 4 seconden doorwezen naar de beginpagina. Bedankt!</p>
	</section>

</body>

</html>