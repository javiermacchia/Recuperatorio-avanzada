<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
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
			href="http://localhost:8080/Parcial_Javier_Mariano_Macchiavello_2021/Chofer.jsp">home</a>
	</nav>
<script>

function validarPatente(){ 
	var patenteNueva = new RegExp('[a-zA-Z]{2}\\s[0-9]{3}\\s[a-zA-Z]{2}');
	var btnEditar = document.getElementById("btnEditar");
	var dominio = document.getElementById("dominio");
	var divAlert = document.getElementById("divAlert");
	 if (!(dominio.value.match(patenteNueva)) ){
		 dominio.style.borderColor = "red";
		 btnEnviar.style.display = "none";
		 divAlert.style.display = "block";
 	}
	 else{
		 dominio.style.borderColor = "inherit";
		 btnEditar.style.display = "block";
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
	var ErrorFinal = document.getElementById("ErrorFinal");
	var erroresTexto = document.getElementById("divAlert3");
	var contadorErrrores = 0;
	var erroresString = ""
	
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

function advertencia(){
alert('Esta seguro? Al cambiar este dato todos los viajes de este chofer seran removidos!');
//Esto se hace ya que en el caso de cambiar el chofer y que tenga viajes asignados con camiones
//puede pasar que ya no coincidan. En el enunciado no aclara por ende se pasara a borrar todos los viajes
//asociados del mismo
}

</script>

<form action="servletEdit" method="POST" onsubmit="return verificacionesFinales()">
<table>
<c:choose>
<c:when test = '${dato == "Camion"}'>

 <tr>
 <td>
<label>Marca del camion: </label>
 </td>
  <td>
<input type="text" name="marca" id="marca" placeholder="Ingrese la marca del camion" required>
 </td>
</tr>

 <tr>
 <td>
<label>Modelo del camion: </label>
 </td>
  <td>
<input type="text" name="modelo" id="modelo" placeholder="Ingrese el modelo del camion" required>
 </td>
</tr>

 <tr>
 <td>
<label>Patente del camion: </label>
 </td>
  <td>
<input type="text" name="dominio" id="dominio" onblur="validarPatente()"  placeholder="Ingrese la patente del camion" required>

<div class="alert alert-danger" style="display:none" id="divAlert" role="alert">
  La patente es incorrecta! Formato valido: AA 123 AA
</div>

 </td>
</tr>

 <tr>
 <td>
<label>Capacidad del camion (en kg.): </label>
 </td>
  <td>
<input type="number" name="capacidadToneladas" min="0" step="any" id="capacidadToneladas"  placeholder="Ingrese la capacidad de toneldas del camion (en kg.)" required>
 </td>
</tr>

 <tr>
 <td>
<label>Capacidad de nafta (en L): </label>
 </td>
  <td>
<input type="number" name="cantLitrosNafta" min="0" step="any" id="cantLitrosNafta"  placeholder="Ingrese la capacidad de nafta (en litros)" required>
 </td>
</tr>

 <tr>
 <td>
<label>Capacidad de litros consumido por Km (en km): </label>
 </td>
  <td>
<input type="number" name="cantLitrosConsumidos" min="0" step="any" id="cantLitrosConsumidos"  placeholder="Ingrese la capacidad de litros consumido (en KM)" required>
 </td>
</tr>
</c:when>
<c:when test = '${dato == "Chofer"}'>

<tr>
 <td>
<label>ID usuario (Solo lectura): </label>
 </td>
  <td>
<input type="text" name="nombreUsuario" id="nombreUsuario"  placeholder="Ingrese el nombre de usuario" readonly required>
 </td>
</tr>

<tr>
 <td>
<label>Nombre de usuario: </label>
 </td>
  <td>
<input type="text" name="nombreUsuario" id="nombreUsuario"  placeholder="Ingrese el nombre de usuario" required>
 </td>
</tr>

 <tr>
 <td>
<label>Clave del usuario: </label>
 </td>
  <td>
<input type="password" name="claveUsuario" id="claveUsuario" placeholder="Ingrese la clave del usuario" required>
 </td>
</tr>

 <tr>
 <td>
<label>Repetir clave del usuario: </label>
 </td>
  <td>
<input type="password" onblur="verificarClaves()" name="claveUsuario2" id="claveUsuario2" placeholder="Repita la clave" required>
 <small id="showClave" class="form-text text-muted"><label><input type="checkbox" onclick="mostrarClaves()" id="checkClave" name="checkClave"> Mostrar claves</label></small>
 <div class="alert alert-danger" style="display:none" id="divAlert2" role="alert">
  Las claves no coinciden!
</div>
 </td>
</tr>

 <tr>
 <td>
<label>Nombre del chofer: </label>
 </td>
  <td>
<input type="text" name="nombreChofer"  id="nombreChofer" placeholder="Ingrese el nombre del chofer" required>
 </td>
</tr>

 <tr>
 <td>
<label>Apellido del chofer: </label>
 </td>
  <td>
<input type="text" name="apellidoChofer" id="apellidoChofer"  placeholder="Ingrese el apellido del chofer" required>
 </td>
</tr>

 <tr>
 <td>
<label>Documento del chofer: </label>
 </td>
  <td>
<input type="number" name="documentoChofer" id="documentoChofer"  placeholder="Ingrese el Documento del chofer" required>
 </td>
</tr>

 <tr>
 <td>
<label>Fecha nacimiento del chofer: </label>
 </td>

  <td>
<input type="date" name="fechaNacChof" id="fechaNacChof"  placeholder="Ingrese la fecha de nacimiento" required>
 </td>
</tr>

 <tr>
 <td>
<label>Celular del chofer: </label>
 </td>
  <td>
<input type="number" name="celularChof" id="celularChof"  placeholder="Ingrese el telefono del chofer" required>
 </td>
</tr>

 <tr>
 <td colspan="2">
<label>Seleccione la categoria del chofer: </label>
 </td>
</tr>
<tr>
<td colspan="2">

 <tr>
 <td>
<label>Origen: </label>
 </td>
  <td>
<select name="selectOrigen"  id="selectOrigen" required>


</select>
 </td>
</tr>

 <tr id="destinosFila">
 <td>
<label>Destino: </label>
 </td>
  <td>
<select name="selectDestino" id="selectDestino" required>



</select>
 </td>
</tr>

 <tr>
 <td>
<label>Patente del camion: </label>
 </td>
  <td>
   <select name="selectPatente">
  <option></option>

  </select> 

 </td>
</tr>

 <tr>
 <td>
<label>Chofer del camion: </label>
 </td>
  <td>

   <select name="selectChofer">

  </select> 
 </td>
</tr>

 <tr>
 <td>
<label>Distancia entre ambos: </label>
 </td>
  <td>
  
<input type="number" name="distancia" id="distancia"  min="0" placeholder="distancia del trayecto" step="any" required>
 </td>
</tr>

</c:when>
</c:choose>

 <tr>
 <td id="ErrorFinal" colspan="2" style="display:none">
<div class="alert alert-danger" id="divAlert3" role="alert">
  <p id="errores"></p>
</div>
 </td>
</tr>

<tr>
<td colspan="2">
<input type="submit" class="btn btn-success"  name="btnEditar" id="btnEditar">
</td>
</tr>
</table>
</form>
</body>
</html>