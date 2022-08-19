package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.DeportistaDAO;
import com.luv2code.springboot.cruddemo.entity.Deportista;

/**
 * Implementación de la capa de servicios
 * @author Amolin
 *
 */
@Service
public class DeportistaServiceImpl implements DeportistaService {

	private DeportistaDAO deportistaDAO;
	
	/**
	 * Interface de persistencia
	 * Indicar cuál será la implementación a utilizar, Hibernate o JDBC
	 * @param theDeportistaDAO es inyectada por Spring
	 * Hibernate : 	"deportistaDAOHibernateImpl"
	 * JDBC: 		"deportistaDaoJdbcImpl"
	 */
	@Autowired
	public DeportistaServiceImpl(@Qualifier("deportistaDaoJdbcImpl") DeportistaDAO theDeportistaDAO) {
		deportistaDAO = theDeportistaDAO;
	}
	
	/**
	 * Obtener una lista de deportistas
	 */
	@Override
	@Transactional
	public List<Deportista> findAll() {
		return deportistaDAO.findAll();
	}
	
	/**
	 * Obtener un deportista por su id
	 * @param theId es el id del deportista
	 */
	@Override
	@Transactional
	public Deportista findById(int theId) {
		return deportistaDAO.findById(theId);
	}

	/**
	 * Agregar deportista a la base de datos
	 * @param theDeportista es el deportista que se inserta en la base de datos
	 */
	@Override
	@Transactional
	public void save(Deportista theDeportista) {
		deportistaDAO.save(theDeportista);
	}

	/**
	 * Eliminar un deportista de la base de datos
	 * @param theId es el id del deportista que se elimina
	 */
	@Override
	@Transactional
	public void deleteById(int theId) {
		deportistaDAO.deleteById(theId);
	}
	
}
