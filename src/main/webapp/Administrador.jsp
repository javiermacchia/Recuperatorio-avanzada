<!DOCTYPE html>
<%@page import="controller.servletEdit"%>
<html>
<head>
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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Envios Pepito</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#">Envios Pepito</a>
				<a class="navbar-brand mr-sm-2" href="http://localhost:8080/Parcial_Javier_Mariano_Macchiavello_2021/Administrador.jsp">Home</a>
					<div name="div1" id="div1">
					<input type="radio" name="radio" onclick="agregar()" id="radioAgregar" value="Agregar ">Agregar
					<input type="radio" name="radio" onclick="agregar()" id="radioMod">Modificar 
					<input type="radio" name="radio" onclick="agregar()" id="radioBaja">Eliminar 
					</div>
					<div class="navbar-text" style="margin-left: 60%;"><a class="navbar-brand mr-sm-2"
					href="http://localhost:8080/Parcial_Javier_Mariano_Macchiavello_2021/salir.jsp">Exit</a>
	</div>
	</nav>
<script>
function agregar(){
	var div1 = document.getElementById("div1");
	var radioAgregar = document.getElementById("radioAgregar");
	var radioBaja = document.getElementById("radioBaja");
	var radioMod = document.getElementById("radioMod");
	var divAgregar = document.getElementById("divAgregar");
	var divEditar = document.getElementById("divEditar");
	var divEliminar = document.getElementById("divEliminar");
	var btnEnviar = document.getElementById("btnEnviar");
	if(radioAgregar.checked == true){
		div1.style.display = "none";
		divAgregar.style.display = "block";
		btnEnviar.value= radioAgregar.value;
	}
	if(radioBaja.checked == true){
		div1.style.display = "none";
		divEliminar.style.display = "block";
	}
	if(radioMod.checked == true){
		div1.style.display = "none";
		divEditar.style.display = "block";
	}
	
}

function validarPatente(){ 
	var patenteNueva = new RegExp('[a-zA-Z]{2}\\s[0-9]{3}\\s[a-zA-Z]{2} || [a-zA-Z]{3}\\s[0-9]{3}');
	var btnEnviar = document.getElementById("btnEnviar");
	var dominio = document.getElementById("dominio");
	var divAlert = document.getElementById("divAlert");
	 if (!(dominio.value.match(patenteNueva)) ){
		 dominio.style.borderColor = "red";
		 btnEnviar.style.display = "none";
		 divAlert.style.display = "block";
 	}
	 else{
		 dominio.style.borderColor = "inherit";
		 btnEnviar.style.display = "block";
		 divAlert.style.display = "none";
	 }
}

function mostrarClaves(){
	var checkClaves = document.getElementById("checkClave");
	var claveUsuario1 = document.getElementById("claveUsuario");
	var claveUsuario2 = document.getElementById("claveUsuario2");
    if(checkClaves.checked == true){
    	claveUsuario1.type = "text";
    	claveUsuario2.type = "text";
    }
    else{
    	claveUsuario1.type = "password";
    	claveUsuario2.type = "password";  	
    }
	
}

function verificarClaves(){
	var claveUsuario1 = document.getElementById("claveUsuario");
	var claveUsuario2 = document.getElementById("claveUsuario2");	
	var div2 = document.getElementById("divAlert2");
	var btnEnviar = document.getElementById("btnEnviar");
	if(claveUsuario1.value != claveUsuario2.value){
		div2.style.display = "block";
		btnEnviar.style.display = "none";
    	claveUsuario1.style.borderColor = "red";
    	claveUsuario2.style.borderColor = "red";
	}
	else{
    	claveUsuario1.style.borderColor = "inherit";
    	claveUsuario2.style.borderColor = "inherit";
		div2.style.display = "none";
		btnEnviar.style.display = "block";		
	}
}

function verificacionesFinales(){
	var selectOrigen = document.getElementById("selectOrigen");
	var selectDestino = document.getElementById("selectDestino");
	var pantenteBuscar = document.getElementById("pantenteBuscar");
	var patenteNueva = new RegExp('[a-zA-Z]{2}\\s[0-9]{3}\\s[a-zA-Z]{2} || [a-zA-Z]{3}\\s[0-9]{3}');
	var ErrorFinal = document.getElementById("ErrorFinal");
	var erroresTexto = document.getElementById("divAlert3");
	var contadorErrrores = 0;
	var erroresString = ""
	 if (!(pantenteBuscar.value.match(patenteNueva)) ){
		 pantenteBuscar.style.borderColor = "red";
		 ErrorFinal.style.display = "block";
		 erroresString += "Ingrese una pantente en formato AA 123 AA o AAA 123";
		 contadorErrrores++;
 	}
	 else{
		 pantenteBuscar.style.borderColor = "inherit";
		 ErrorFinal.style.display = "none";
	 }
	
	if(selectDestino.value == selectOrigen.value){
		ErrorFinal.style.display = "block";
		selectOrigen.style.borderColor = "red";
		selectDestino.style.borderColor = "red";
		erroresString += "El destino y el origen coinciden! ";
		contadorErrrores++;
	}
	else{
		ErrorFinal.style.display = "none";
		selectOrigen.style.borderColor = "inherit";
		selectDestino.style.borderColor = "inherit";
		erroresTexto.innerHTML = "";
	}
	if(contadorErrrores > 0){
		erroresTexto.innerHTML = erroresString;
		return false;
	}
	else{
		return true; //Completo todo satisfactoriamente
	}
}


</script>

<div name="divEditar" id="divEditar" style="display:none">

<form action="servletEdit" method="get">
<input type="number" name="campoID" id="campoID" placeholder="ID a editar" required>
<input type="radio" id="Echofer" name="radio"  value="Chofer" required>
                      <label >Editar Chofer</label>
<input type="radio" id="Ecamion" name="radio"  value="Camion" required>
                      <label >Editar Camion</label>
<input type="submit" class="btn btn-success" name="btnEnviarEditar" value="Editar" >
</form>
</div>

<div name="divEliminar" id="divEliminar" style="display:none">
<form action="servletEdit" method="get">
<input type="number" name="campoID" id="campoID" placeholder="ID a eliminar" required>
<input type="radio" id="Echofer" name="radio"  value="Chofer" required>
                      <label >Eliminar Chofer</label>
<input type="radio" id="Ecamion" name="radio"  value="Camion" required>
                      <label >Eliminar Camion</label>
<input type="submit" class="btn btn-success" name="btnEnviarEliminar" value="Eliminar">

</form>
</div>

<div name="divAgregar" id="divAgregar" style="display:none">

<form action="servletAdmin" method="post" id="formAdd" name="formAdd" onsubmit="return verificacionesFinales()">
 <table>

 <tr>
 <td>
<label><b>Marca del camion: </b></label>
 </td>
  <td>
<input type="text" class="form-control" aria-describedby="inputGroup-sizing-sm" name="marca" id="marca" placeholder="Ingrese la marca del camion" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Modelo del camion: </b></label>
 </td>
  <td>
  <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="modelo" id="modelo" placeholder="Ingrese el modelo del camion" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Patente del camion: </b></label>
 </td>
  <td>
  <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="dominio" id="dominio" onblur="validarPatente()" placeholder="Ingrese la patente del camion" required>

<div class="alert alert-danger" style="display:none" id="divAlert" role="alert">
  La patente es incorrecta! Formato valido: AA 123 AA o AAA 123
</div>

 </td>
</tr>

 <tr>
 <td>
<label><b>Capacidad del camion (en kg.): </b></label>
 </td>
  <td>
  <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="capacidadToneladas" id="capacidadToneladas" placeholder="Ingrese la capacidad de toneldas del camion (en kg.)" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Capacidad de nafta (en L): </b></label>
 </td>
  <td>
  <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="cantLitrosNafta" min="0" step="any" id="cantLitrosNafta" placeholder="Ingrese la capacidad de nafta (en litros)" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Capacidad de litros consumido por Km (en km): </b></label>
 </td>
  <td>
  <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="cantLitrosConsumidos" min="0" step="any" id="cantLitrosConsumidos" placeholder="Ingrese la capacidad de litros consumido (en KM)" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Nombre de usuario: </b></label>
 </td>
  <td>
  <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="nombreUsuario" id="nombreUsuario" placeholder="Ingrese el nombre de usuario" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Clave del usuario: </b></label>
 </td>
  <td>
  <input type="password" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="claveUsuario" id="claveUsuario" placeholder="Ingrese la clave del usuario" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Repetir clave del usuario: </b></label>
 </td>
  <td>
  <input type="password" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" onblur="verificarClaves()" name="claveUsuario2" id="claveUsuario2" placeholder="Repita la clave" required>
 <small id="showClave" class="form-text text-muted"><label><input type="checkbox" onclick="mostrarClaves()" id="checkClave" name="checkClave"> <b>Mostrar claves</b></label></small>
 <div class="alert alert-danger" style="display:none" id="divAlert2" role="alert">
  Las claves no coinciden!
</div>
 </td>
</tr>

 <tr>
 <td>
<label><b>Nombre del chofer: </b></label>
 </td>
  <td>
  <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="nombreChofer" id="nombreChofer" placeholder="Ingrese el nombre del chofer" required>
  </td>
</tr>

 <tr>
 <td>
<label><b>Apellido del chofer: </b></label>
 </td>
  <td>
  <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="apellidoChofer" id="apellidoChofer" placeholder="Ingrese el apellido del chofer" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Documento del chofer: </b></label>
 </td>
  <td>
  <input type="number" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="documentoChofer" id="documentoChofer" placeholder="Ingrese el Documento del chofer" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Fecha nacimiento del chofer: </b></label>
 </td>
  <td>
  <input type="date" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="fechaNacChof" id="fechaNacChof" placeholder="Ingrese la fecha de nacimiento" required>
 </td>
</tr>

 <tr>
 <td>
<label><b>Celular del chofer: </b></label>
 </td>
  <td>
  <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" type="number" name="celularChof" id="celularChof" placeholder="Ingrese el telefono del chofer" required>
 </td>
</tr>

 <tr>
 <td colspan="2">
<label><b>Seleccione la categoria del chofer:</b> </label>
 </td>
</tr>
<tr>
<td colspan="2">
<label><input type="radio" name="radioCategoria" id="C1" value="C1" required> C.1</label>
<small id="c1Help" class="form-text">Camiones sin acoplado o casas rodantes motorizadas hasta 12.000 kg</small>
<label><input type="radio" name="radioCategoria" id="C2" value="C2" required> C.2</label>
<small id="c2Help" class="form-text">Camiones sin acoplado o casas rodantes motorizadas hasta 24.000 kg</small>
<label><input type="radio" name="radioCategoria" id="C3" value="C3" required> C.3</label>
<small id="c3Help" class="form-text">Camiones sin acoplado o casas rodantes motorizadas de más de 24.000 kg</small>
 </td>
</tr>

<%
String origenes[] = {"CABA", "Cordoba", "Corrientes", "Neuquén", "Formosa", "La Rioja", "Mendoza", "La Plata"};
pageContext.setAttribute("arrOrigen", origenes);
%>


 <tr>
 <td>
<label><b>Seleccione uno de los posibles origenes: </b></label>
 </td>
  <td>
<select name="selectOrigen"  id="selectOrigen" required>
<!-- 
<c:forEach var="items" items="${arrOrigen}">
			${items}		
</c:forEach>
-->
<%for(String o: origenes){ %>
	<option><%= o %></option>
<%}%>
</select>
 </td>
</tr>

 <tr id="destinosFila">
 <td>
<label><b>Seleccione uno de los posibles destinos: </b></label>
 </td>
  <td>
<select name="selectDestino" id="selectDestino" required>
<!-- 
<c:forEach var="items" items="${arrOrigen}">
			${items}		
</c:forEach>
-->
<%for(String o: origenes){ %>
	<option><%= o %></option>
<%}%>
</select>
 </td>
</tr>

 <tr>
 <td>
<label><b>Escriba la patente del camion: </b></label>
 </td>
  <td>
  
<input type="text" name="pantenteBuscar" id="pantenteBuscar" required>
<label><%=request.getSession().getAttribute("ErrorCamion")!=null?request.getSession().getAttribute("ErrorCamion"):""%></label>
 </td>
</tr>

 <tr>
 <td>
<label><b>Distancia entre ambos: </b></label>
 </td>
  <td>
  <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" type="number" name="distancia" id="distancia" min="0" placeholder="distancia del trayecto" step="any" required>
 </td>
</tr>
 <tr>
 <td id="ErrorFinal" colspan="2" style="display:none">
<div class="alert alert-danger" id="divAlert3" role="alert">
  <p id="errores"></p>
</div>
  
 </td>
</tr>
 <tr>
  <td colspan="2">
<input type="submit" class="btn btn-success" value="Agregar " name="btnEnviar" id="btnEnviar">
 </td>
</tr>

</table>
</form>

</div>

</body>
</html>