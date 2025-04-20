package stage1;

import java.util.Random;

public class Animal {
	
	private String especie;
	private String nombre;
	private int puntosVida;
	private int frutasPorRonda;
	private int frutasAcumuladas;
	private Random random;

	
	
	public Animal(String especie, int puntosVida, int frutasPorRonda) {
		this.especie = especie;
		this.puntosVida = puntosVida;
		this.frutasPorRonda = frutasPorRonda;
		
	}

	public Animal(String especie) {
		this.especie = especie.toLowerCase();
		this.random = new Random();
		inicializarAtributos();
	}

	// Inicializa los atributos seg�n la especie
	private void inicializarAtributos() {
		switch (especie) {
		case "perro":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		case "gato":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		case "lobo":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		case "conejo":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		case "oso":
			this.puntosVida = 400;
			this.frutasPorRonda = 10; // Inicia con 10 frutas
			break;
		case "ardilla":
			this.puntosVida = 100;
			this.frutasPorRonda = 70;
			break;
		case "mono":
			this.puntosVida = 100;
			this.frutasPorRonda = 50;
			break;
		case "pulpo":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		case "cabra":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		case "ave":
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		default:
			this.puntosVida = 200;
			this.frutasPorRonda = 50;
			break;
		}
		this.frutasAcumuladas = 0;
	}

	
	public int ataque(String especieObjetivo, int frutas) {
		double multiplicador = calcularMultiplicadorAtaque(especieObjetivo);
		return (int) (frutas * multiplicador);
	}

	public int defensa(int frutasEntrantes) {
		if (especie.equals("ardilla") && especie.equals("mono")) {
			int frutasEvitadas = 0;
			for (int i = 0; i < frutasEntrantes; i++) {
				if (random.nextDouble() < 0.5) { // Probabilidad de esquivar 50%
					frutasEvitadas++;
				}
			}
			return frutasEntrantes - frutasEvitadas;
		}
		return frutasEntrantes; // Sin modificacion para otras especies
	}

	private double calcularMultiplicadorAtaque(String especieObjetivo) {
		especieObjetivo = especieObjetivo.toLowerCase();
		switch (especie) {
		case "gato":
			if (especieObjetivo.equals("conejo"))
				return 2.0;
			if (especieObjetivo.equals("lobo"))
				return 0.5;
			break;
		case "lobo":
			if (especieObjetivo.equals("gato") && especieObjetivo.equals("ave"))
				return 2.0;
			if (especieObjetivo.equals("oso"))
				return 0.5;
			break;
		case "conejo":
			if (especieObjetivo.equals("pulpo"))
				return 2.0;
			if (especieObjetivo.equals("perro"))
				return 0.5;
			break;
		case "oso":
			if (especieObjetivo.equals("cabra") && especieObjetivo.equals("lobo"))
				return 2.0;
			if (especieObjetivo.equals("ave"))
				return 0.5;
		case "mono":
			if (especieObjetivo.equals("pulpo"))
				return 2.0;
		case "cabra":
			if (especieObjetivo.equals("ardilla"))
				return 2.0;
		case "ave":
			if (especieObjetivo.equals("oso"))
				return 2.0;
			if (especieObjetivo.equals("lobo"))
				return 0.5;
		case "pulpo":
			if (especieObjetivo.equals("gato"))
				return 2.0;
			break;
		default:
			break;
		}
		return 1.0; // Multiplicador neutral
	}

	// Gesti�n de vida
	public void recibirAtaque(int danio) {
		this.puntosVida -= danio;
		if (this.puntosVida < 0)
			this.puntosVida = 0;
	}

	// Gesti�n de frutas por ronda
	public void recolectarFrutas() {
		this.frutasAcumuladas += frutasPorRonda;
		if (especie.equals("oso")) {
			frutasPorRonda += 2; // Aumenta 2 frutas cada ronda
		}
	}

	public int usarFrutas(int cantidad) {
		if (cantidad > frutasAcumuladas) {
			cantidad = frutasAcumuladas; // No puede usar m�s de las frutas que tiene
		}
		frutasAcumuladas -= cantidad;
		return cantidad;
	}

	// Getters
	public String getEspecie() {
		return especie;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public int getFrutasAcumuladas() {
		return frutasAcumuladas;
	}

	
	public void setFrutasAcumuladas(int frutasAcumuladas) {
		this.frutasAcumuladas = frutasAcumuladas;
	}

	@Override
	public String toString() {
		return "Especie: " + especie + ", Vida: " + puntosVida + ", Frutas acumuladas: " + frutasAcumuladas;
	}

	public String getNombre() {
		return nombre;
	}
}
