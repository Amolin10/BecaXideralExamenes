package com.Interfaces;

public class Cuadrado implements FiguraGeometrica {

	private float lado;
	
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
