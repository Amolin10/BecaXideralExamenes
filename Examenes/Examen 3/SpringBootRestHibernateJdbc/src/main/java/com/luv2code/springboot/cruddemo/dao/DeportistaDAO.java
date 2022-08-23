package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Deportista;

/**
 * Interface de la capa de Persistencia
 * @author Amolin
 */
public interface DeportistaDAO {

	public List<Deportista> findAll();
	
	
	public Deportista findById(int theId);
	
	
	public void save(Deportista theDeportista);
	
	
	public void deleteById(int theId);

}
