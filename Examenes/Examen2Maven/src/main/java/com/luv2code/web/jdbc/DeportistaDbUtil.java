package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DeportistaDbUtil {

	private DataSource dataSource;

	public DeportistaDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
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

