<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<title>Guardar Deportista</title>
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>Administrador de deportistas</h2>
		</div>
	</div>

	<div id="container">
		<h3>Deportista</h3>
	
		<form:form action="saveDeportista" modelAttribute="deportista" method="POST">

			<!-- need to associate this data with deportista id -->
			<form:hidden path="id" />
					
			<table class="table table-striped">
				<tbody>
					<tr>
						<td><label>Nombre:</label></td>
						<td><form:input path="nombre" /></td>
					</tr>
				
					<tr>
						<td><label>Apellido:</label></td>
						<td><form:input path="apellido" /></td>
					</tr>

					<tr>
						<td><label>Deporte:</label></td>
						<td><form:input path="deporte" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Guardar" class="btn btn-primary" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a class="btn btn-dark" href="${pageContext.request.contextPath}/deportista/list">Regresar a la lista</a>
		</p>
	
	</div>

</body>

</html>