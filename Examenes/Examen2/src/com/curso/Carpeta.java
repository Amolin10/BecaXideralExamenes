package com.curso;

import java.util.ArrayList;
import java.util.List;

public class Carpeta implements File {
	
	private List<File> contenido = new ArrayList<>();
	private StringBuilder contentInfo = new StringBuilder();
	private int nivel = 0;
	private String nombreCarpeta;
	
	public Carpeta (String nombreCarpeta) {
		this.nombreCarpeta = nombreCarpeta;
	}

	public void addContenedor(File cont) {
		contenido.add(cont);
	}
	
	@Override
	public String getContenido() {
		
		contentInfo.append(nombreCarpeta + "/\n");
		for(File cont: contenido) {
			cont.indicarNivel(nivel + 1);
			contentInfo.append(cont.getContenido());
		}
		return contentInfo.toString();
	}
	
	@Override
	public void indicarNivel(int nivel) {
		this.nivel = nivel;
		for(int i = 0; i < nivel; ++i) {
			contentInfo.append("\t"); 
		}
	}
		
}