<!DOCTYPE html>
<html>

<head>
	<title>Actualizar Deportista</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-deportista-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Mejores Deportistas</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Actualizar Deportista</h3>
		
		<form action="DeportistaControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="deportistaId" value="${EL_DEPORTISTA.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Nombre:</label></td>
						<td><input type="text" name="nombre" 
								   value="${EL_DEPORTISTA.nombre}" /></td>
					</tr>

					<tr>
						<td><label>Apellido:</label></td>
						<td><input type="text" name="apellido" 
								   value="${EL_DEPORTISTA.apellido}" /></td>
					</tr>

					<tr>
						<td><label>Deporte:</label></td>
						<td><input type="text" name="deporte" 
								   value="${EL_DEPORTISTA.deporte}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Guardar" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="DeportistaControllerServlet">Regresar a la lista</a>
		</p>
	</div>
</body>

</html>











