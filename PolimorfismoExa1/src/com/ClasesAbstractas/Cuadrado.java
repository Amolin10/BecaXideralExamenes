package com.ClasesAbstractas;

public class Cuadrado extends FiguraGeometrica {
	
	public Cuadrado(float lado) {
		super(lado);
	}
	
	@Override
	public float getArea() {
		return this.valor * this.valor;
	}

	@Override
	public String toString() {
		return "Área: " + this.getClass().getSimpleName() + ": L*L";
	}

}
