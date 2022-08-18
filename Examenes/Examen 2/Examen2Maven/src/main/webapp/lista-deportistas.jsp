<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Deportistas Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Mejores Deportistas</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Deportista -->
			
			<input type="button" value="Agregar Deportista" 
				   onclick="window.location.href='add-deportista-form.jsp'; return false;"
				   class="add-deportista-button"
			/>
			
			<table>
			
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Deporte</th>
					<th>Acción</th>
				</tr>
				
				<c:forEach var="tempDeportista" items="${LISTA_DEPORTISTAS}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="DeportistaControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="deportistaId" value="${tempDeportista.id}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="DeportistaControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="deportistaId" value="${tempDeportista.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempDeportista.nombre} </td>
						<td> ${tempDeportista.apellido} </td>
						<td> ${tempDeportista.deporte} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Estás seguro de que quieres eliminar este deportista?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>
