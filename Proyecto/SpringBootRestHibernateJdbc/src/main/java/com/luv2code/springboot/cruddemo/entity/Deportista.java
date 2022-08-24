package com.luv2code.springboot.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase deportista, tiene los mismos atributos que la tabla deportista en la base de datos
 * @author Amolin
 */
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name="deportista")
public class Deportista {

	// define fields
	
	/**
	 * Mapeo de los campos SQL -> Java
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="deporte")
	private String deporte;
		
	public Deportista(String nombre, String apellido, String deporte) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.deporte = deporte;
	}
		
}
