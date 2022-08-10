package com.curso;

public class Archivo implements File {

	private String nombreProducto;
	private int nivel;
	
	public Archivo (String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	@Override
	public String getContenido() {
		return nombreProducto + "\n";
	}

	/**
	 * No utilizo este método
	 */
	@Override
	public void addContenedor(File cont) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void indicarNivel(int nivel) {
		this.nivel = nivel;
		for(int i = 0; i < nivel; ++i) {
			nombreProducto = "\t" + nombreProducto; 
		}		
	}

}
