package com.curso;

public interface Contenedor {
	
	String getContenido();
	
	void addContenedor(Contenedor cont);
	
	void indicarNivel(int nivel);
	
}