package com.curso;

public class Principal {

	public static void main(String[] args) {

		Contenedor contProducto = new Producto("Iphone");
		Contenedor contProducto2 = new Producto("SmartPhone");
		Contenedor contCaja = new Caja();
		contCaja.addContenedor(contProducto);
		contCaja.addContenedor(contProducto2);
		
		Contenedor contProducto3 = new Producto("Laptop");
		Contenedor contProducto4 = new Producto("PCEscritorio");
		Contenedor contCaja2 = new Caja();
		contCaja2.addContenedor(contProducto3);
		contCaja2.addContenedor(contProducto4);
		contCaja.addContenedor(contCaja2);
		
		Contenedor contProducto5 = new Producto("Mouse");
		Contenedor contProducto6 = new Producto("Teclado");
		Contenedor contCaja3 = new Caja();
		contCaja3.addContenedor(contProducto5);
		contCaja3.addContenedor(contProducto6);
		contCaja.addContenedor(contCaja3);
		
		
		System.out.println(contCaja.getContenido());
		
		
	}

}
