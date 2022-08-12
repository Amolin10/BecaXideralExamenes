package com.curso;

import java.util.ArrayList;
import java.util.List;

/**
 * Carpeta es un File (Archivo)
 * @author AMolin
 */
public class Carpeta implements File {
	
	/**
	 * Carpeta tiene cero o varios File
	 */
	private List<File> contenido = new ArrayList<>();
	
	/**
	 * contentInfo es la información de los Files que contiene la carpeta en forma de StringBuilder 
	 * para recuperar la cadena
	 */
	private StringBuilder contentInfo = new StringBuilder();
	
	/**
	 * nivel hace referencia al número de carpetas padres que tiene esta carpeta
	 * La primer carpeta tiene el nivel 0, las carpetas y archivos dentro de ella 
	 * van aumentanto ed nivel
	 */
	private int nivel = 0;
	
	/**
	 * Nombre de la carpeta
	 */
	private String nombreCarpeta;
	
	/**
	 * Constructor
	 * @param nombreCarpeta es el nombre que se asigna a la carpeta
	 */
	public Carpeta (String nombreCarpeta) {
		this.nombreCarpeta = nombreCarpeta;
	}

	/**
	 * métoto para agregar un contenedor (File) ya sea Archivo o Carpeta dentro de esta carpeta 
	 * @param cont es el fichero (File) que se agrega a la lista de contenido
	 */
	public void addContenedor(File cont) {
		contenido.add(cont);
	}
	
	/**
	 * Recuperar el contenido en forma de String
	 * Recursivamente concatena el contenido de los demás Files que contiene esta carpeta
	 * hasta recorrer todos los elementos de su lista contenido y las listas de sus elementos
	 * cada que entra recursivamente en un elemento le indica su nivel + 1
	 * 
	 * Al terminar el método, se forma una cadena con estructura de árbol para 
	 * visualizar el contenido
	 */
	@Override
	public String getContenido() {
		
		contentInfo.append(nombreCarpeta + "/\n");
		for(File cont: contenido) {
			cont.indicarNivel(nivel + 1);
			contentInfo.append(cont.getContenido());
		}
		return contentInfo.toString();
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
			contentInfo.append("\t"); 
		}
	}
		
}