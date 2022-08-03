package com.Interfaces;

public class Triangulo implements FiguraGeometrica {
	
	private float lado;
	
	public Triangulo(float lado) {
		this.lado = lado;
	}

	@Override
	public float getArea() {
		return (lado * lado) / 2;
	}

	@Override
	public String toString() {
		return "Área: " + this.getClass().getSimpleName() + ": (L*L)/2";
	}
	
	

}
