package ui;

import stage1.Menu;

public class Equipos extends Menu {
	
	Equipos[] equipos = new Equipos[5]; // Array de 5 elementos de tipo Equipo
	String nombre;
	String especie;
	
	public Equipos(String nombre, String especie) {
		this.nombre = nombre;
		this.especie = especie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "Equipos [nombre=" + nombre + ", especie=" + especie + "]";
	}


}
