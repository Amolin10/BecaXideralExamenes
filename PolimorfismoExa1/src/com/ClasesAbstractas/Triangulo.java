package com.ClasesAbstractas;

public class Triangulo extends FiguraGeometrica {
	
	public Triangulo(float lado) {
		super(lado);
	}

	@Override
	public float getArea() {
		return (this.valor * this.valor) / 2;
	}

	@Override
	public String toString() {
		return "Área: " + this.getClass().getSimpleName() + ": (L*L)/2";
	}
	
	

}
