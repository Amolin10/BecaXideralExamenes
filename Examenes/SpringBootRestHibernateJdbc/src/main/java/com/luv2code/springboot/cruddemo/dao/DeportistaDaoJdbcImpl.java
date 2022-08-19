package com.luv2code.springboot.cruddemo.dao;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Deportista;

/**
 * Implementación JDBC de la capa de Persistencia
 * @author Amolin
 */
@Repository
public class DeportistaDaoJdbcImpl implements DeportistaDAO {

	/**
	 * Asignado e inyectado por Spring
	 */
	@Autowired
	DataSource dataSource;

	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	/**
	 * Hace un query para obtener todos los elementos de la tabla deportista
	 * En un ResultSet obtiene el resultado
	 * Obtiene el valor de cada campo
	 * crea un Deportista temporal utilizando los valores obtenidos
	 * Agrega el deporttista a la lista
	 * Repite hasta que no hay más deportistas en el ResultSet
	 * @return una lista de deportistas
	 */
	@Override
	public List<Deportista> findAll() {
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
				
				// create new deportista object
				Deportista tempDeportista = new Deportista(id, nombre, apellido, deporte);
				
				// add it to the list of deportista
				deportistas.add(tempDeportista);				
			}
			
			return deportistas;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deportistas;		
	}
	
	/**
	 * Obtiene un deportista basado en la id.
	 * @param theDeportistaId es el id del deportista que se quiere obtener
	 * @return un deportista que tenga la id indicada
	 */
	@Override
	public Deportista findById(int theId) {
		Deportista theDeportista = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int deportistaId;
		
		try {
			// convert student id to int
			deportistaId = theId;
			
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
		return null;
	}
	
	
	/**
	 * Función para agregar o actualizar un deportista
	 * Sí recibe un deportista con id=0: Lo inserta en la base de datos
	 * Sí recibe un deportista con id != 0: Actualiza el deportista con ese id
	 */
	@Override
	public void save(Deportista theDeportista) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			int idDeportista = theDeportista.getId();
			if (idDeportista == 0) {
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
			} else {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	/**
	 * Elimina un deportista de la base de datos utilizando el id del deportista
	 * Hace un query para eliminar un deportista con id indicado
	 * @param theDeportistaId el id del deportista que se elimina de la base de datos
	 */
	@Override
	public void deleteById(int theId) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int deportistaId = theId;
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// clean up JDBC code
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

	
}

