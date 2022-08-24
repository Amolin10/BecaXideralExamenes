<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<title>Lista de Deportistas</title>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2 class="h2">Administrador de deportistas</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Deportista -->
		
			<input type="button" value="Agregar deportista"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="btn btn-dark btn-lg"
			/>
		
			<!--  add our html table here -->
		
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Deporte</th>
						<th>Accion</th>
					</tr>
				</thead>

				<tbody>
				
				<!-- loop over and print our deportistas -->
				<c:forEach var="tempDeportista" items="${deportistas}">
				
					<!-- construct an "update" link with deportista id -->
					<c:url var="updateLink" value="/deportista/showFormForUpdate">
						<c:param name="deportistaId" value="${tempDeportista.id}" />
					</c:url>					

					<!-- construct an "delete" link with deportista id -->
					<c:url var="deleteLink" value="/deportista/delete">
						<c:param name="deportistaId" value="${tempDeportista.id}" />
					</c:url>					
					

						<tr>
							<td> ${tempDeportista.nombre} </td>
							<td> ${tempDeportista.apellido} </td>
							<td> ${tempDeportista.deporte} </td>
							
							<td>
								<!-- display the update link -->
								<a class="btn btn-primary btn-sm" href="${updateLink}">Actualizar</a>
								|
								<a class="btn btn-primary btn-sm" href="${deleteLink}"
								onclick="if (!(confirm('Seguro que deseas eliminar este deportista?'))) return false">Eliminar</a>
							</td>
							
						</tr>
						
					</c:forEach>
					
				</tbody>
					
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









