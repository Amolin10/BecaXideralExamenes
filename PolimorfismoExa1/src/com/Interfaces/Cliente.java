package com.Interfaces;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	public static void main(String[] args) {

		List<FiguraGeometrica> figuras = new ArrayList<>();
		figuras.add(new Triangulo(5.7f));
		figuras.add(new Cuadrado(4.1f));
		figuras.add(new Circulo(2.3f));
		
		calcularAreas(figuras);
	}

	private static void calcularAreas(List<FiguraGeometrica> figuras) {
		for (FiguraGeometrica figura : figuras) {
			System.out.println(figura);
			System.out.println(figura.getArea());
		}
		
	}

	
}
