package com.pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.curso.*;

class TestComposite {

	@DisplayName("Asignar nombre a un archivo")
	@Test
	void testArchivo() {
		String nombreArchivo = "fichero.txt\n";
		File archivo = new Archivo("fichero.txt");
		assertTrue(nombreArchivo.equals(archivo.getContenido()));
	}
	
	@DisplayName("Carpeta vacía")
	@Test
	void testCarpetaVacia() {
		String contenido = "Videos/\n";
		File carpeta = new Carpeta("Videos");
		assertTrue(carpeta.getContenido().startsWith(contenido));
	}
		

	@DisplayName("Carpeta + archivo")
	@ParameterizedTest
	@ValueSource(strings = {"Videos", "fichero.txt"})
	void testCarpetaConArchivo(String contenido) {
		File carpeta = new Carpeta("Videos");
		File archivo = new Archivo("fichero.txt");
		carpeta.addContenedor(archivo);
		assertTrue(carpeta.getContenido().contains(contenido));
	}
	
	@DisplayName("Carpeta + carpeta + archivo")
	@ParameterizedTest
	@ValueSource(strings = {"Documentos", "Videos", "fichero.txt"})
	void testCarpetaConCarpeta(String contenido) {
		File carpeta1 = new Carpeta("Documentos");
		File carpeta2 = new Carpeta("Videos");
		File archivo = new Archivo("fichero.txt");
		carpeta1.addContenedor(carpeta2);
		carpeta2.addContenedor(archivo);
		assertTrue(carpeta1.getContenido().contains(contenido));
	}
	
	@Nested
	@DisplayName("Carpeta Compuesta")
	class CarpetaCompuesta {
		
		@DisplayName("Carpeta Imágenes")
		@ParameterizedTest
		@ValueSource(strings = {"Imágenes", "Mi_foto.jpg", "screenshot.jpg"})
		void testCarpetaImagenes(String contenidoImagenes) {
			
			File carpetaImagenes = new Carpeta("Imágenes");
			File imagen1 = new Archivo("Mi_foto.jpg");
			File imagen2 = new Archivo("screenshot.jpg");
			carpetaImagenes.addContenedor(imagen1);
			carpetaImagenes.addContenedor(imagen2);
			
			assertTrue(carpetaImagenes.getContenido().contains(contenidoImagenes));
		}
		
		@DisplayName("Carpeta Programas")
		@ParameterizedTest
		@ValueSource(strings = {"Programas", "sort.py", "cliente.py"})
		void testCarpetaProgramas(String contenidoProgramas) {
			
			File carpetaProgramas = new Carpeta("Programas");
			File programa1 = new Archivo("sort.py");
			File programa2 = new Archivo("cliente.py");
			carpetaProgramas.addContenedor(programa1);
			carpetaProgramas.addContenedor(programa2);
			
			assertTrue(carpetaProgramas.getContenido().contains(contenidoProgramas));
		}
		
		
		@DisplayName("Carpeta Compuesta")
		@ParameterizedTest
		@ValueSource(strings = {"Documentos", "Título.docx", "CV.pdf", "Imágenes", "Mi_foto.jpg", "screenshot.jpg", "Programas", "sort.py", "cliente.py"})
		void testCarpetaCompuesta(String contenido) {
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
			
			assertTrue(carpetaDocumentos.getContenido().contains(contenido));
		}
		
	
	}	
			
}
