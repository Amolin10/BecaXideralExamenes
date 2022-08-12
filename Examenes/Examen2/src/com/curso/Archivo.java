package com.curso;

/**
 * Archivo - Leaf
 * Simula un archivo con extensión, no contiene más ficheros
 * @author USER
 * */
public class Archivo implements File {

	/**
	 * Nombre del archivo
	 */
	private String nombreArchivo;
	
	/**
	 * Nivel del archivo dentro de la estructura de archivos
	 */
	private int nivel;
	
	/**
	 * Constructor 
	 * @param nombreArchivo es el nombre de este archivo
	 */
	public Archivo (String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * Regresa el nombre del archivo más un salto de línea para conservar el orden
	 */
	@Override
	public String getContenido() {
		return nombreArchivo + "\n";
	}

	/**
	 * No agrega ficheros dentro de él
	 * cont no se agrega a su contenido, ya que es un archivo de tipo hoja en el patrón composite
	 */
	@Override
	public void addContenedor(File cont) {
		System.out.println("Una archivo hoja (leaf) no puede contener otros ficheros");
		
	}

	/**
	 * Indicar al File su nivel dentro de la estructura de archivos
	 * Cada que entra recursivamente en un elemento le indica su nivel + 1
	 * @param nivel es su lugar dentro de la estructura de archivos
	 */
	@Override
	public void indicarNivel(int nivel) {
		this.nivel = nivel;
		for(int i = 0; i < nivel; ++i) {
			nombreArchivo = "\t" + nombreArchivo; 
		}		
	}

}
