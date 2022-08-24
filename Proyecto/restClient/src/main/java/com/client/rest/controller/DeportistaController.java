package com.client.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.rest.model.Deportista;
import com.client.rest.service.DeportistaService;

/**
 * Controller
 * Recibe las peticiones REST y llama a la implementación del servicio para responder en la vista JSP
 * @author Amolin
 */
@Controller
@RequestMapping("/deportista")
public class DeportistaController {

	//Inyectada por Spring
	@Autowired
	private DeportistaService deportistaService;
	
	/**
	 * Método GET
	 * @param theModel es la vista del servicio
	 * @return una lista con los deportistas de la base de datos
	 */
	@GetMapping("/list")
	public String listDeportistas(Model theModel) {
		
		//Obtiene los deportistas del servicio
		List<Deportista> theDeportistas = deportistaService.getDeportistas();
				
		//Agrega la lista de deportistas a la vista, indicando el nombre del atributo y el valor
		theModel.addAttribute("deportistas", theDeportistas);
		
		//Llama al JSP list-deportistas
		return "list-deportistas";
	}
	
	/**
	 * Método GET
	 * @param theModel es la vista
	 * @return
	 */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//Crear un deportista
		Deportista theDeportista = new Deportista();
		
		//Agrega el deportista indicando el nombre y el valor del atributo
		theModel.addAttribute("deportista", theDeportista);
		
		//JSP
		return "deportista-form";
	}
	
	/**
	 * Método POST
	 * @param theDeportista es el deportista que se agrega a la base de datos
	 * @return el JSP con la lista de deportistas
	 */
	@PostMapping("/saveDeportista")
	public String saveDeportista(@ModelAttribute("deportista") Deportista theDeportista) {
		
		//Guardar el deportista utilizando el servicio
		deportistaService.saveDeportista(theDeportista);	
		
		//Redirigir a la lista de deportistas
		return "redirect:/deportista/list";
	}
	
	/**
	 * Método GET
	 * @param theId es el id del deportista que se muesta para editar
	 * @param theModel es la capa de la vista
	 * @return el JSP para editar al deportista
	 */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("deportistaId") int theId,
									Model theModel) {
		
		//Obtiene el deportista utilizando el servicio
		Deportista theDeportista = deportistaService.getDeportista(theId);	
		
		//Agrega el deportista a la JSP indicando el nombre y el valor del atributo
		theModel.addAttribute("deportista", theDeportista);
		
		//Salir del formulario JSP		
		return "deportista-form";
	}
	
	/**
	 * Método GET
	 * @param theId del deportista que se elimina de la base de datos
	 * @return regreso al JSP de la lista de deportistas
	 */
	@GetMapping("/delete")
	public String deleteDeportista(@RequestParam("deportistaId") int theId) {
		
		//Eliminar el deportista utilizando el servicio
		deportistaService.deleteDeportista(theId);
		
		return "redirect:/deportista/list";
	}
}
