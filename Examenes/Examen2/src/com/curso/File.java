package com.curso;

public interface File {
	
	String getContenido();
	
	void addContenedor(File cont);
	
	void indicarNivel(int nivel);
	
}