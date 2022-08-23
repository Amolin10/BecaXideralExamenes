package com.ClasesAbstractas;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	public static void main(String[] args) {

		List<FiguraGeometrica> figuras = new ArrayList<>();
		figuras.add(new Triangulo(11.5f));
		figuras.add(new Cuadrado(10f));
		figuras.add(new Circulo(4.2f));
		
		calcularAreas(figuras);
	}

	private static void calcularAreas(List<FiguraGeometrica> figuras) {
		for (FiguraGeometrica figura : figuras) {
			System.out.println(figura);
			System.out.println(figura.getArea());
		}
		
	}

	
}
