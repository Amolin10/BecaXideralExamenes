package com.curso;

/**
 * Interface File - Composite
 * Básado en los Sistemas Operativos Linux:
 * Los directorios son ficheros, los ficheros son ficheros, y los dispositivos son ficheros.
 * @author AMolin
 * */
public interface File {
	
	String getContenido();
	
	void addContenedor(File cont);
	
	void indicarNivel(int nivel);
	
}