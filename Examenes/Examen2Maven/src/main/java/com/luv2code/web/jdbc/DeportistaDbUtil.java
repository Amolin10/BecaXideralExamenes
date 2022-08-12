package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * Utilidades para inyectar y extraer de la base de datos
 * @author AMolin
 */
public class DeportistaDbUtil {

	/**
	 * Tiene un recurso definido en Deployed Resources/webapp/META-INF/context.xml
	 */
	private DataSource dataSource;

	/**
	 * Constructor 
	 * @param theDataSource lo asigna a su variable de instancia
	 */
	public DeportistaDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	/**
	 * Hace un query para obtener todos los elementos de la tabla deportista
	 * En un ResultSet obtiene el resultado
	 * Obtiene el valor de cada campo
	 * crea un Deportista temporal utilizando los valores obtenidos
	 * Agrega el deporttista a la lista
	 * Repite hasta que no hay más deportistas en el ResultSet
	 * @return una lista de deportistas
	 * @throws Exception si no se pude conectar a la base de datos, o no puede ejecutar la sentencia sql
	 */
	public List<Deportista> getDeportistas() throws Exception {
		
		List<Deportista> deportistas = new ArrayList<>();
		
		try (	Connection myConn = dataSource.getConnection();
				Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from deportista");
			){
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String nombre = myRs.getString("nombre");
				String apellido = myRs.getString("apellido");
				String deporte = myRs.getString("deporte");
				
				// create new student object
				Deportista tempDeportista = new Deportista(id, nombre, apellido, deporte);
				
				// add it to the list of students
				deportistas.add(tempDeportista);				
			}
			
			return deportistas;		
		}	
	}

	/**
	 * Inserta un deportista a la base de datos
	 * @param theDeportista es el deportista que agrega a la base de datos
	 * @throws Exception si no se pude conectar a la base de datos, o no puede ejecutar la sentencia sql
	 */
	public void addDeportista(Deportista theDeportista) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into deportista "
					   + "(nombre, apellido, deporte) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the deportista
			myStmt.setString(1, theDeportista.getNombre());
			myStmt.setString(2, theDeportista.getApellido());
			myStmt.setString(3, theDeportista.getDeporte());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}
	
	/**
	 * Cierra conexiones dentro de un bloque try
	 * @param myConn recurso a cerrar
	 * @param myStmt recurso a cerrar
	 * @param myRs recurso a cerrar
	 */
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	/**
	 * Elimina un deportista de la base de datos utilizando el id del deportista
	 * Hace un query para eliminar un deportista con id indicado
	 * @param theDeportistaId el id del deportista que se elimina de la base de datos
	 * @throws Exception si no se pude conectar a la base de datos, o no puede ejecutar la sentencia sql
	 */
	public void deleteDeportista(String theDeportistaId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int deportistaId = Integer.parseInt(theDeportistaId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from deportista where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, deportistaId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}

	/**
	 * Actualiza un deportista tomando sus nuevos valores
	 * @param theDeportista es un Deportista con los nuevos valores a insertar
	 * @throws Exception si no se pude conectar a la base de datos, o no puede ejecutar la sentencia sql
	 */
	public void updateDeportista(Deportista theDeportista) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update deportista "
						+ "set nombre=?, apellido=?, deporte=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theDeportista.getNombre());
			myStmt.setString(2, theDeportista.getApellido());
			myStmt.setString(3, theDeportista.getDeporte());
			myStmt.setInt(4, theDeportista.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	/**
	 * Obtiene un deportista basado en la id.
	 * @param theDeportistaId es el id del deportista que se quiere obtener
	 * @return un deportista que tenga la id indicada
	 * @throws Exception si no se pude conectar a la base de datos, o no puede ejecutar la sentencia sql
	 */
	public Deportista getDeportista(String theDeportistaId) throws Exception {

		Deportista theDeportista = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int deportistaId;
		
		try {
			// convert student id to int
			deportistaId = Integer.parseInt(theDeportistaId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from deportista where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, deportistaId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String nombre = myRs.getString("nombre");
				String apellido = myRs.getString("apellido");
				String deporte = myRs.getString("deporte");
				
				// use the studentId during construction
				theDeportista = new Deportista(deportistaId, nombre, apellido, deporte);
			}
			else {
				throw new Exception("Could not find deportista id: " + deportistaId);
			}				
			
			return theDeportista;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

}

