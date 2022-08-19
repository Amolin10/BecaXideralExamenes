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

	
	/**
	 * Hace un query para obtener todos los elementos de la tabla deportista
	 * En un ResultSet obtiene el resultado
	 * Obtiene el valor de cada campo
	 * crea un Deportista temporal utilizando los valores obtenidos
	 * Agrega el deportista a la lista
	 * Repite hasta que no hay más deportistas en el ResultSet
	 * @return una lista de deportistas
	 */
	@Override
	public List<Deportista> findAll() {
		List<Deportista> deportistas = new ArrayList<>();
		
		//Try with resources
		try (	Connection myConn = dataSource.getConnection();
				Statement myStmt = myConn.createStatement();
				ResultSet myRs = myStmt.executeQuery("select * from deportista");
			){
			// process result set
			while (myRs.next()) {
				
				//Obtener datos del deportista
				int id = myRs.getInt("id");
				String nombre = myRs.getString("nombre");
				String apellido = myRs.getString("apellido");
				String deporte = myRs.getString("deporte");
				
				//Crear un nuevo deportista 
				Deportista tempDeportista = new Deportista(id, nombre, apellido, deporte);
				
				//Agregarlo a la lista
				deportistas.add(tempDeportista);				
			} //while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //try
		return deportistas;		
	} //method
	
	/**
	 * Obtiene un deportista basado en la id.
	 * @param theDeportistaId es el id del deportista que se quiere obtener
	 * @return un deportista que tenga la id indicada
	 */
	@Override
	public Deportista findById(int theId) {
		Deportista theDeportista = null;	
		int deportistaId;
		
		//try with resources
		try(	Connection myConn = dataSource.getConnection();
				PreparedStatement myStmt = myConn.prepareStatement("select * from deportista where id=?");
			) {
			
			//id del deportista
			deportistaId = theId;
			//Asignar a la sentencia SQL
			myStmt.setInt(1, deportistaId);
			
			//try with resources anidado
			try (ResultSet myRs = myStmt.executeQuery()) {
				//Obtener datos del deportista
				if (myRs.next()) {
					String nombre = myRs.getString("nombre");
					String apellido = myRs.getString("apellido");
					String deporte = myRs.getString("deporte");
					
					//Crear un deportista asignando el id recibido
					theDeportista = new Deportista(deportistaId, nombre, apellido, deporte);
				}
				else {
					throw new SQLException("Could not find deportista id: " + deportistaId);
				}				
				
				//Regresar el deportista encontrado
								
			} //try	anidado		
		} catch (SQLException e) {
			e.printStackTrace();
		} //try
		return theDeportista;
	} //method
	
	
	/**
	 * Función para agregar o actualizar un deportista
	 * Sí recibe un deportista con id=0: Lo inserta en la base de datos
	 * Sí recibe un deportista con id != 0: Actualiza el deportista con ese id
	 */
	@Override
	public void save(Deportista theDeportista) {
		
		int idDeportista = theDeportista.getId();
		//Insert
		if (idDeportista == 0) {
			try (	Connection myConn = dataSource.getConnection();
					PreparedStatement myStmt = myConn.prepareStatement(
								 "insert into deportista "
							   + "(nombre, apellido, deporte) "
							   + "values (?, ?, ?)");) 
			{
				//Dar valores a la sentencia insert SQL
				myStmt.setString(1, theDeportista.getNombre());
				myStmt.setString(2, theDeportista.getApellido());
				myStmt.setString(3, theDeportista.getDeporte());
				
				//Ejecutar sentencia insert
				myStmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		//Update
		} else {
			try (	Connection myConn = dataSource.getConnection();
					PreparedStatement myStmt = myConn.prepareStatement(
									  "update deportista "
									+ "set nombre=?, apellido=?, deporte=? "
									+ "where id=?");) 
			{
				//Dar valores a la sentencia update SQL
				myStmt.setString(1, theDeportista.getNombre());
				myStmt.setString(2, theDeportista.getApellido());
				myStmt.setString(3, theDeportista.getDeporte());
				myStmt.setInt(4, theDeportista.getId());
				
				//Ejecutar sentencia update
				myStmt.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} //try
		} //else
	} //method
	
	
	/**
	 * Elimina un deportista de la base de datos utilizando el id del deportista
	 * Hace un query para eliminar un deportista con id indicado
	 * @param theDeportistaId el id del deportista que se elimina de la base de datos
	 */
	@Override
	public void deleteById(int theId) {
				
		try (	Connection myConn = dataSource.getConnection();
				PreparedStatement myStmt = myConn.prepareStatement("delete from deportista where id=?");)
		{
			//id del deportista
			int deportistaId = theId;
			//Dar valores a la sentencia delete SQL
			myStmt.setInt(1, deportistaId);
			//Ejecutar sentencia delete
			myStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} //try
	} //method
	
} //class

