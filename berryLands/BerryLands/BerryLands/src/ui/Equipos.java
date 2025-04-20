package ui;

import stage1.Menu;

public class Equipos {
    
    private String nombre;
    private String especie;
    private int vida;
    private int frutas;
    private int defensa;


    public Equipos(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
        this.vida = 100;    // Vida inicial estándar
        this.frutas = 50;   // Frutas iniciales estándar
        this.defensa = 0;
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFrutas() {
        return frutas;
    }

    public void setFrutas(int frutas) {
        this.frutas = frutas;
    }
    
    public int getDefensa() {
        return defensa;  
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;  
    }

    @Override
    public String toString() {
        return "Equipo [Nombre: " + nombre + ", Especie: " + especie + ", Vida: " + vida + ", Frutas: " + frutas + ", Defensa: " + defensa + "]";
    }
}

