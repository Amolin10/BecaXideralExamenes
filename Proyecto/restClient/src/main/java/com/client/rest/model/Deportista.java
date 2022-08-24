package com.client.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Agregar constructores, getters, setters y método toString
 * Utilizando lombok
 * @author Amolin
 */

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class Deportista {
	
	private int id;
	private String nombre;
	private String apellido;
	private String deporte;
	
}
