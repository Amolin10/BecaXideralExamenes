package com.curso;

import java.util.ArrayList;
import java.util.List;

public class Caja implements Contenedor {
	
	private List<Contenedor> contenido = new ArrayList<>();
	private StringBuilder contentInfo = new StringBuilder();
	private int nivel = 0;
	
	Caja () {
		
	}

	public void addContenedor(Contenedor cont) {
		contenido.add(cont);
	}
	
	@Override
	public String getContenido() {
		
		contentInfo.append("Caja\n");
		for(Contenedor cont: contenido) {
			cont.indicarNivel(nivel + 1);
			contentInfo.append(cont.getContenido());
		}
		return contentInfo.toString();
	}
	
	@Override
	public void indicarNivel(int nivel) {
		this.nivel = nivel;
		for(int i = 0; i < nivel; ++i) {
			contentInfo.append("*"); 
		}
	}
		
}