package com.ClasesAbstractas;

public class Circulo extends FiguraGeometrica {

	public Circulo(float radio) {
		super(radio);
	}
	
	@Override
	public float getArea() {
		return 3.1416f * (this.valor * this.valor);
	}

	@Override
	public String toString() {
		return "Área: " + this.getClass().getSimpleName() + ": PI*(R*R)";
	}
}
