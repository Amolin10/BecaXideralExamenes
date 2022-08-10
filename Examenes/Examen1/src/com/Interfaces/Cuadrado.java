package com.Interfaces;

/**
 * Cuadrado implements FiguraGeometrica 
 * @author Amolin
 */
public class Cuadrado implements FiguraGeometrica {
	/**
	 * lado representa la medida de un lado de un cuadrado
	 */
	private float lado;
	
	/**
	 * Constructor de la clase
	 * @param lado valor necesario para crear un cuadrado
	 */
	public Cuadrado(float lado) {
		this.lado = lado;
	}
	
	@Override
	public float getArea() {
		return lado * lado;
	}

	@Override
	public String toString() {
		return "Área: " + this.getClass().getSimpleName() + ": L*L";
	}

}
