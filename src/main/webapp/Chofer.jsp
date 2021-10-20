<%@page import="controller.servletLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="controller.servletChofer"%>
<!DOCTYPE html>
<html>
<link rel="Stylesheet" href="Styles.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
	integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
	integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
	crossorigin="anonymous"></script>
<script>
	function clickInicio() {
		var finViaje = document.getElementById("finViaje");
		var inicioViaje = document.getElementById("inicioViaje");
		if (finViaje.checked == true) {
			inicioViaje.checked = true;
		}
	}
</script>

<% servletChofer sc = new servletChofer(); %>
<% servletLogin sl = new servletLogin(); %>
<head>
<meta charset="ISO-8859-1">

<title>Envios Pepito</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#">Envios Pepito</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="nav-item nav-link"
			href="http://localhost:8080/Parcial_Javier_Mariano_Macchiavello_2021/Chofer.jsp">Home</a>
	</nav>
	
	<form method="GET" action="servletChofer">
	<select id="cmbViajes" name="cmbViajes">
	<option selected disabled>seleccione un ID</option>
	<% for (int i=0;i<sl.getCh().getListaViaje().size(); i++ ){ %>
		<option><%= sl.getCh().getListaViaje().get(i).getIdViaje()  %></option>
	
	<%} %>
	</select>
	<button class="btn btn-primary" type="submit" name="btnEnviarId">Buscar Viaje</button>
	</form>
	<% if(request.getAttribute("idViajes")!=null){ %>
	<div style="display:block">
	<%} else { %>
	<div style="display:none">
	<%} %>
	<form method="POST" action="servletChofer">
		<label>Viaje con destino: <%=sc.getViaje().getDestino()%></label> <br>
		<label>Origen: <%=sc.getViaje().getOrigen()%></label> <br> <label>Distancia
			total: <%=sc.getViaje().getDistancia()%></label> <br> <label>Distancia
			en dias: <%=request.getSession().getAttribute("cantDias")%></label> <br>
		<label>Cantidad de tanques necesarios para finalizar el viaje:
			<%=request.getSession().getAttribute("cantTanques")%></label> <br>
		<%
		if (sc.getViaje().isInicio() == true) {
		%>
		<label><input type="checkbox" name="inicioViaje" checked
			onclick="clickInicio()" id="inicioViaje">Viaje iniciado?</label> <br>
		<%
		} else {
		%>
		<label><input type="checkbox" name="inicioViaje"
			onclick="clickInicio()" id="inicioViaje">Viaje iniciado?</label> <br>
		<%
		}
		%>
		<%
		if (sc.getViaje().isFinalizado() == true) {
		%>
		<label><input type="checkbox" name="finViaje" checked
			onclick="clickInicio()" id="finViaje">Viaje finalizado?</label> <br>
		<%
		} else {
		%>
		<label><input type="checkbox" name="finViaje"
			onclick="clickInicio()" id="finViaje">Viaje finalizado?</label> <br>
		<%
		}
		%>
		<button class="btn btn-primary" type="submit" name="btnContinuar">Enviar y salir</button>
	</form>
	</div>
</body>
</html>