package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Deportista;

/**
 * Implementación de la capa de persistencia
 * @author Amolin
 *
 */
@Repository
public class DeportistaDAOHibernateImpl implements DeportistaDAO {

	/**
	 * El EntityManager pertenece a Hibernate, es inyectado por SpringBoot en el constructor
	 */
	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public DeportistaDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	/**
	 * Regresa una lista de deportistas
	 */
	@Override
	public List<Deportista> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Deportista> theQuery =
				currentSession.createQuery("from Deportista", Deportista.class);
		
		// execute query and get result list
		List<Deportista> deportistas = theQuery.getResultList();
		
		// return the results		
		return deportistas;
	}

	
	/**
	 * Obtiene un deportista de la base de datos, utilizando su id
	 * @param theId es el id del deportista que se retorna
	 */
	@Override
	public Deportista findById(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Deportista theDeportista =
				currentSession.get(Deportista.class, theId);
		
		// return the employee
		return theDeportista;
	}

	
	/**
	 * Agrega o actualiza un deportista en la base de datos
	 * @param es el deportista que se almacena o actualiza
	 */
	@Override
	public void save(Deportista theDeportista) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(theDeportista);
	}

	/**
	 * Eliminar un deportista de la base de datos
	 * @param theId es el id del deportista que se elimina
	 */
	@Override
	public void deleteById(int theId) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery(
						"delete from Deportista where id=:deportistaId");
		theQuery.setParameter("deportistaId", theId);
		
		theQuery.executeUpdate();
	}

}







