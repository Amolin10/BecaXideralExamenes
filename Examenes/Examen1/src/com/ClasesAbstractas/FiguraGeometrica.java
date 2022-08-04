package com.ClasesAbstractas;

public abstract class FiguraGeometrica {

	float valor;
	
	public FiguraGeometrica(float valor) {
		this.valor = valor;
	}
	
	public abstract float getArea();
	
	@Override
	public String toString() {
		return "Área: " + this.getClass().getSimpleName();
	}
	
}
