package com.curso;

public class Principal {

	public static void main(String[] args) {

		File carpetaDocumentos = new Carpeta("Documentos");
		File archivo1 = new Archivo("Título.docx");
		File archivo2 = new Archivo("CV.pdf");
		carpetaDocumentos.addContenedor(archivo1);
		carpetaDocumentos.addContenedor(archivo2);
		
		File carpetaImagenes = new Carpeta("Imágenes");
		carpetaDocumentos.addContenedor(carpetaImagenes);
		File imagen1 = new Archivo("Mi_foto.jpg");
		File imagen2 = new Archivo("screenshot.jpg");
		carpetaImagenes.addContenedor(imagen1);
		carpetaImagenes.addContenedor(imagen2);
					
		File carpetaProgramas = new Carpeta("Programas");
		carpetaDocumentos.addContenedor(carpetaProgramas);
		File programa1 = new Archivo("sort.py");
		File programa2 = new Archivo("cliente.py");
		carpetaProgramas.addContenedor(programa1);
		carpetaProgramas.addContenedor(programa2);
		
		System.out.println(carpetaDocumentos.getContenido());
		
		
	}

}
