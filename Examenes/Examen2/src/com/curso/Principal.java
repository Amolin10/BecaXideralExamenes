package com.curso;

public class Principal {

	public static void main(String[] args) {

		File contCaja = new Carpeta("Documentos");
		File contProducto = new Archivo("Título.docx");
		File contProducto2 = new Archivo("CV.pdf");
		contCaja.addContenedor(contProducto);
		contCaja.addContenedor(contProducto2);
		
		File contCaja2 = new Carpeta("Imágenes");
		contCaja.addContenedor(contCaja2);
		File contProducto3 = new Archivo("Mi_foto.jpg");
		File contProducto4 = new Archivo("screenshot.jpg");
		contCaja2.addContenedor(contProducto3);
		contCaja2.addContenedor(contProducto4);
		
		File contCaja3 = new Carpeta("Programas");
		contCaja.addContenedor(contCaja3);
		File contProducto5 = new Archivo("sort.py");
		File contProducto6 = new Archivo("cliente.py");
		contCaja3.addContenedor(contProducto5);
		contCaja3.addContenedor(contProducto6);
		
		
		
		
		System.out.println(contCaja.getContenido());
		
		
	}

}
