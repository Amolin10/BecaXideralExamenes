package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.Deportista;

/**
 * Interface de la capa de servicios
 * @author Amolin
 *
 */
public interface DeportistaService {

	public List<Deportista> findAll();
	
	public Deportista findById(int theId);
	
	public void save(Deportista theDeportista);
	
	public void deleteById(int theId);
	
}
