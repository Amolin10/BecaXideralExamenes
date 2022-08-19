package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Deportista;
import com.luv2code.springboot.cruddemo.service.DeportistaService;

/**
 * Controller Rest, recibe las peticiones GET, POST, PUT y DELETE
 * Capa REST
 * @author Amolin
 *
 */
@RestController
@RequestMapping("/api")
public class DeportistaRestController {
	
	/**
	 * Asingado e inyectado por SpringBoot
	 */
	private DeportistaService deportistaService;
	
	@Autowired
	public DeportistaRestController(DeportistaService theDeportistaService) {
		deportistaService = theDeportistaService;
	}
	
	/**
	 * Método GET
	 * @return una lista de deportistas
	 */
	@GetMapping("/deportistas")
	public List<Deportista> findAll() {
		return deportistaService.findAll();
	}

	
	/**
	 * 	Método GET
	 * @param deportistaId es el id del deportista que se desea obtener
	 * @return un deportista
	 */
	@GetMapping("/deportistas/{deportistaId}")
	public Deportista getDeportista(@PathVariable int deportistaId) {
		
		Deportista theDeportista = deportistaService.findById(deportistaId);
		
		if (theDeportista == null) {
			throw new RuntimeException("Deportista id not found - " + deportistaId);
		}
		
		return theDeportista;
	}
	
	
	/**
	 * Método POST	
	 * @param theDeportista es el deportista que se inserta en la base de datos
	 * @return el deportista insertado
	 */
	@PostMapping("/deportistas")
	public Deportista addDeportista(@RequestBody Deportista theDeportista) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theDeportista.setId(0);
		
		deportistaService.save(theDeportista);
		
		return theDeportista;
	}
	
	/** 
	 * Método PUT
	 * @param theDeportista que se actualiza
	 * @return el deportista actualizado
	 */
	@PutMapping("/deportistas")
	public Deportista updateDeportista(@RequestBody Deportista theDeportista) {
		
		deportistaService.save(theDeportista);
		
		return theDeportista;
	}
	
	/**
	 * Método DELETE
	 * @param deportistaId es el id del deportista que se elimna
	 * @return un mensaje con el id del deportista eliminado
	 */
	@DeleteMapping("/deportistas/{deportistaId}")
	public String deleteEmployee(@PathVariable int deportistaId) {
		
		Deportista tempDeportista = deportistaService.findById(deportistaId);
		
		// throw exception if null
		
		if (tempDeportista == null) {
			throw new RuntimeException("Employee id not found - " + deportistaId);
		}
		
		deportistaService.deleteById(deportistaId);
		
		return "Deleted deportista id - " + deportistaId;
	}
	
}










