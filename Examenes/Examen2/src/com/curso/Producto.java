package com.curso;

public class Producto implements Contenedor {

	private String nombreProducto;
	private int nivel;
	
	public Producto (String nombreProducto) {
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
	public void addContenedor(Contenedor cont) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void indicarNivel(int nivel) {
		this.nivel = nivel;
		for(int i = 0; i < nivel; ++i) {
			nombreProducto = "*" + nombreProducto; 
		}		
	}

}
