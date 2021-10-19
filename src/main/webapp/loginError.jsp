<html lang="en" style="height: 100vh">
<head>
<meta http-equiv="Content-Type" content="text/html" ; charset="utf-8" />
<title>Sign In</title>
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
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#">Envios Pepito</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="nav-item nav-link active"
			href="http://localhost:8080/Parcial_Javier_Mariano_Macchiavello_2021/ventanas/login.jsp">Log
			In (current)</a>
	</nav>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card border-0 shadow rounded-3 my-5">
					<div class="card-body p-4 p-sm-5">
						<h5 class="card-title text-center mb-5 fw-light fs-5">Sign In</h5>
						<form method="post" action="./servletLogin">

							<div class="form-floating mb-3">
								<input type="text" class="form-control" name="usuario"
									placeholder="Usuario" required>
							</div>
							<div class="form-floating mb-3">
								<input type="password" class="form-control" name="password"
									placeholder="Contraseña" required>
							</div>
							<div class="alert alert-danger" role="alert">Usuario o Contraseña Incorrecto</div> 
							<div class="d-grid">
								<button class="btn btn-primary" type="submit">Sign in</button>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</body>
</html>