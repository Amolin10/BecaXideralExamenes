package com.luv2code.web.jdbc;

/**
 * Clase Deportista
 * contiene los mismos atributos que en la base de datos
 * @author Amolin
 */
public class Deportista {

	/**
	 * Atributos de la base da datos: id, nombre, apellido, deporte
	 */
	private int id;
	private String nombre;
	private String apellido;
	private String deporte;

	/**
	 * Constructor de la clase sin id
	 * @param nombre lo asigna a su variable de instancia
	 * @param apellido lo asigna a su variable de instancia
	 * @param deporte lo asigna a su variable de instancia
	 */
	public Deportista(String nombre, String apellido, String deporte) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.deporte = deporte;
	}

	/**
	 * Constructor de la clase con id
	 * @param id lo asigna a su variable de instancia
	 * @param nombre lo asigna a su variable de instancia
	 * @param apellido lo asigna a su variable de instancia
	 * @param deporte lo asigna a su variable de instancia
	 */
	public Deportista(int id, String nombre, String apellido, String deporte) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.deporte = deporte;
	}

	/**
	 * Setters and Getters
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	/**
	 * Sobrescribe el método toString
	 */
	@Override
	public String toString() {
		return "Deportista [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", deporte=" + deporte + "]";
	}
	
	
	
}
