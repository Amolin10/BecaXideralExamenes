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
	//Hibernate ORM
	private EntityManager entityManager;
		
	//recurso inyectado por Spring
	@Autowired
	public DeportistaDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	/**
	 * Regresa una lista de deportistas
	 */
	@Override
	public List<Deportista> findAll() {

		//Iniciar la sesión de Hibernate
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Sentencia SQL con Hibernate
		Query<Deportista> theQuery =
				currentSession.createQuery("from Deportista", Deportista.class);
		
		// Ejecutar sentencia
		List<Deportista> deportistas = theQuery.getResultList();
		
		// regresar la lista de deportistas	
		return deportistas;
	}

	
	/**
	 * Obtiene un deportista de la base de datos, utilizando su id
	 * @param theId es el id del deportista que se retorna
	 */
	@Override
	public Deportista findById(int theId) {

		//Iniciar la sesión de Hibernate
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Obteber el deportista con id = theId
		Deportista theDeportista =
				currentSession.get(Deportista.class, theId);
		
		//regresar el empleado
		return theDeportista;
	}

	
	/**
	 * Agrega o actualiza un deportista en la base de datos
	 * @param es el deportista que se almacena o actualiza
	 */
	@Override
	public void save(Deportista theDeportista) {

		//Iniciar la sesión de Hibernate
		Session currentSession = entityManager.unwrap(Session.class);
		
		//Guardar el deportista
		currentSession.saveOrUpdate(theDeportista);
	}

	/**
	 * Eliminar un deportista de la base de datos
	 * @param theId es el id del deportista que se elimina
	 */
	@Override
	public void deleteById(int theId) {
		
		//Iniciar la sesión de Hibernate
		Session currentSession = entityManager.unwrap(Session.class);
				
		//Eliminar el deportista con id = theId
		Query theQuery = 
				currentSession.createQuery(
						"delete from Deportista where id=:deportistaId");
		theQuery.setParameter("deportistaId", theId);
		
		theQuery.executeUpdate();
	}

}







