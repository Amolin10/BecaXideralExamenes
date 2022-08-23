package com.examen3;

import java.util.*;
import java.util.function.*;

/**
 * Clase para realizar operaciones utilizando lambdas 
 * @author Amolin
 */
public class Calculadora {

	/*
	 * Método main
	 */
	public static void main(String[] args) {
		
		/**
		 * Lambdas para definir los bloques de código de cada operación
		 * BinaryOperator toma 2 parámetros del mismo tipo y el resultado que retorna también es del mismo tipo
		 * También se pudo utilizar de una manera más corta:
		 * DoubleBinaryOperator
		 */
		BinaryOperator<Double> suma = (x,y) -> x+y;
		BinaryOperator<Double> resta = (x,y) -> x-y;
		BinaryOperator<Double> multi = (x,y) -> x*y;
		BinaryOperator<Double> div = (x,y) -> x/y;
		BinaryOperator<Double> potencia = (x,y) -> Math.pow(x, y);
		BinaryOperator<Double> raiz = (x,y) -> Math.pow(x, 1 / y);
		BinaryOperator<Double> max = (x,y) -> Math.max(x, y);
		BinaryOperator<Double> min = (x,y) -> Math.min(x, y);
		
		/**
		 * Lista donde se colocan las lambdas de operaciones
		 * de tipo BinaryOperator
		 */
		List<BinaryOperator<Double>> listOperaciones = new ArrayList<>();
		
		/**
		 * Se colocan las operaciones en la lista
		 */
		listOperaciones.add(suma);
		listOperaciones.add(resta);
		listOperaciones.add(multi);
		listOperaciones.add(div);
		listOperaciones.add(potencia);
		listOperaciones.add(raiz);
		listOperaciones.add(max);
		listOperaciones.add(min);

		/**
		 * Se ejecuta el método static 
		 */
		ejecutaOperaciones(listOperaciones, 4.0, 2.0);
	}
	
	/**
	 * Recorre la lista de operaciones, en este caso, lamdas. 
	 * Ejecuta el método apply a cada una de las operaciones
	 * @param listOperaciones es una lista de lambas de tipo <BinaryOperator<Double>
	 * @param a es el primer valor para realizar la operación
	 * @param b es el segundo valor para realizar la operación
	 */
	static void ejecutaOperaciones(List<BinaryOperator<Double>> listOperaciones, double a, double b) {
		for (BinaryOperator<Double> ope:listOperaciones) { 
			
			/**
			 * Si ocurre una operación no válida, se muestra un mensaje y se continua con las demás operaciones
			 */
			try {
				System.out.println(ope.apply(a, b));
			}catch(ArithmeticException e) {
				System.out.println("La división no se pudo realizar");
			}
		}
	}
	
}
