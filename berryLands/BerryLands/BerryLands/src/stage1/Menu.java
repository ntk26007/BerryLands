package stage1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import ui.Equipos;

/**
 * Todos los syso deben pasarse a la interfaz con un getText() para que se
 * muestren en el JTextArea e ir añadiendo las referencias a metodos necesarias
 */

public class Menu {

	private String[] equipos = new String[5];
	Equipos[] guardaEquipo = new Equipos[5];

	private int[] vidasRestantes = { 200, 200, 200, 200, 200 };
	private boolean[] turnosCompletados = { false, false, false, false, false };
	private String[] especies = { "perro", "gato", "lobo", "conejo", "pulpo", "ardilla", "cabra", "oso", "ave",
			"mono" };
	private int rondaActual = 1;
	private final int MAX_RONDAS = 4;
	private boolean preguntandoObjetivo = false;
	private boolean preguntandoFrutas = false;
	private int equipoObjetivo = -1;
	private int frutasUsadas = 0;
	private boolean enProcesoAtaque = false;
	private int equipoAtacanteActual = -1;
	private int objetivoSeleccionado = -1;
	private int pasoAtaque = 0;
	private int pasoDefensa = 0;
	private String herramientaSeleccionada = "";
	private int equipoDefendiendoActual = -1;
	private boolean enAtaque = false;
	private boolean enDefensa = false;

	// para que no salte el error NullPointer para que al meter la cantidad de frutas no salga error de equipo nulo
	 public void setEquipos(Equipos[] equipos) {
	        this.guardaEquipo = equipos;
	        this.vidasRestantes = new int[equipos.length];
	        this.turnosCompletados = new boolean[equipos.length];

	        for (int i = 0; i < equipos.length; i++) {
	            if (equipos[i] != null) {
	                vidasRestantes[i] = 100; // vida inicial
	            }
	        }
	    }

	public void setEquipo(int index, Equipos equipo) {
		if (index >= 0 && index < equipos.length) {
			guardaEquipo[index] = equipo;
		} else {
			throw new IllegalArgumentException("El índice debe estar entre 0 y 4.");
		}
	}

	public boolean todosLosEquiposRegistrados() {
		// Retorna true si todos los elementos no son nulos
		for (Equipos equipo : guardaEquipo) {
			if (equipo == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * EQUIPOS
	 * 
	 * Asigna un equipo usando los datos suministrados desde la interfaz. Se
	 * verifica que el nombre no esté vacío; si la especie no se ha seleccionado, se
	 * asigna "perro" por defecto. Luego se guarda el objeto en el arreglo interno y
	 * se muestra un mensaje emergente confirmando que se ha guardado. Este método
	 * se invoca en cada pulsación del botón "Asignar" (para cada uno de los 5
	 * equipos).
	 *
	 * @param index   La posición del equipo (0 a 4).
	 * @param nombre  El nombre ingresado para el equipo.
	 * @param especie La especie seleccionada en el JComboBox (si está vacío se
	 *                usará "perro").
	 */
	public void asignarEquipo(int index, String nombre, String especie) {
		if (nombre == null || nombre.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre del equipo " + (index + 1) + " no puede estar vacío.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Si la especie es nula o está vacía, se asigna "perro" por defecto
		if (especie == null || especie.trim().isEmpty()) {
			especie = "Perro";
		}

		// Crear el objeto Equipos con los datos
		Equipos equipo = new Equipos(nombre, especie);
		setEquipo(index, equipo);

		// Muestra una ventana emergente confirmando el guardado.
		JOptionPane.showMessageDialog(null, "Equipo " + (index + 1) + " asignado correctamente.", "Registro de Equipos",
				JOptionPane.INFORMATION_MESSAGE);
	}
  

	/**
	 * ELEGIR OPCIONES
	 * @return
	 */
	public boolean estaPreguntandoAtaque() {
	    return preguntandoObjetivo || preguntandoFrutas;
	}
	
	public void mostrarOpcionesAccion(int equipoIndex, JTextArea areaAcciones) {
	    Equipos equipo = guardaEquipo[equipoIndex];
	    if (equipo == null) return;

	    String nombre = equipo.getNombre();
	    String especie = equipo.getEspecie();
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("Turno del equipo ").append(equipo.getNombre()).append(" (").append(equipo.getEspecie()).append(")\n");
	    sb.append("Selecciona una acción:\n");
	    sb.append("1) Atacar\n");
	    sb.append("2) Defender\n");
	    sb.append("3) Saltar turno\n");

	    areaAcciones.setText(sb.toString());
	}
	
	/**
	 * ATAQUE
	 * @return
	 */
	
	public boolean estaEnProcesoAtaque() {
	    return enProcesoAtaque; //retornara T o F si esta atacando o no
	}

	public void atacar(int equipoIndex, JTextArea areaAcciones) {
	    if (pasoAtaque == 0) {
	        equipoAtacanteActual = equipoIndex;
	        comenzarAtaque(equipoIndex, areaAcciones); // si el numero del equipo que el usuario elige es el mismo que el index, salta al metodo de ataque
	    } else {
	        areaAcciones.append("\nYa estás en medio de un ataque. Escribe tu respuesta y pulsa 'Enviar respuesta'.\n");
	    }
	}

	//hace la primera pregunta
	public void comenzarAtaque(int equipoIndex, JTextArea areaAcciones) {
	    equipoAtacanteActual = equipoIndex;
	    pasoAtaque = 1;
	    objetivoSeleccionado = -1;
	    frutasUsadas = 0;

	    StringBuilder sb = new StringBuilder();
	    sb.append("¿A qué equipo quieres atacar? (Escribe 1-5)\n\n");

	    for (int i = 0; i < 5; i++) {
	        if (i != equipoIndex && guardaEquipo[i] != null && vidasRestantes[i] > 0) { //muestra cada equipo con su vida actual
	            sb.append(i + 1).append(") ").append(guardaEquipo[i].getNombre())
	              .append(" (Vida: ").append(vidasRestantes[i]).append(")\n");
	        }
	    }

	    areaAcciones.setText(sb.toString());
	    areaAcciones.setEditable(true);
	}

	//excepciones + 2º pregunta
	public void procesarObjetivo(String entradaUsuario, JTextArea areaAcciones) {
	    try {
	        int numEquipo = Integer.parseInt(entradaUsuario.trim());

	        if (numEquipo < 1 || numEquipo > guardaEquipo.length) {
	            areaAcciones.append("\nNúmero inválido. Ingresa un número entre 1 y 5:\n");
	            return;
	        }

	        objetivoSeleccionado = numEquipo - 1;

	        if (objetivoSeleccionado == equipoAtacanteActual) {
	            areaAcciones.append("\nNo puedes atacarte a ti mismo. Elige otro equipo:\n");
	            return;
	        }

	        if (vidasRestantes[objetivoSeleccionado] <= 0) {
	            areaAcciones.append("\nEse equipo ya fue eliminado. Elige otro equipo:\n");
	            return;
	        }

	        areaAcciones.append("\nEquipo " + (objetivoSeleccionado + 1) + " seleccionado como objetivo.\n");
	        areaAcciones.append("¿Cuántas frutas deseas usar para el ataque? (1-50):\n");

	        pasoAtaque = 2;

	    } catch (NumberFormatException e) {
	        areaAcciones.append("\nEntrada inválida. Ingresa un número válido.\n");
	    }
	}

	
	//cuando el usuario ingrese el numero de frutas para el ataque 
	private void procesarFrutas(int equipoIndex, String input, JTextArea areaAcciones) {
		if (guardaEquipo[equipoIndex] == null || guardaEquipo[objetivoSeleccionado] == null) {
		    areaAcciones.append("\nError interno: equipo atacante o objetivo es null.\n");
		    return;
		}

		try {
	        frutasUsadas = Integer.parseInt(input.trim());

	        if (frutasUsadas < 1 || frutasUsadas > 50) {
	            areaAcciones.append("\nNúmero de frutas inválido. Debe estar entre 1 y 50.\n");
	            return;
	        }

	        vidasRestantes[objetivoSeleccionado] -= frutasUsadas;
	        if (vidasRestantes[objetivoSeleccionado] < 0) {
	            vidasRestantes[objetivoSeleccionado] = 0;
	        }
	        
	        //deberia salir un resumen despues de haber atacado
	        StringBuilder resultado = new StringBuilder();
	        resultado.append("\n--- RESUMEN DEL ATAQUE ---\n")
	                 .append("Atacante: ").append(guardaEquipo[equipoIndex].getNombre())
	                 .append(" (").append(guardaEquipo[equipoIndex].getEspecie()).append(")\n")
	                 .append("Objetivo: ").append(guardaEquipo[objetivoSeleccionado].getNombre())
	                 .append(" (").append(guardaEquipo[objetivoSeleccionado].getEspecie()).append(")\n")
	                 .append("Frutas usadas: ").append(frutasUsadas).append("\n")
	                 .append("Vida restante objetivo: ").append(vidasRestantes[objetivoSeleccionado]).append("\n");

	        if (vidasRestantes[objetivoSeleccionado] == 0) { // cuando la vida de un equipo sea 0, salta excepcion
	            resultado.append("¡").append(guardaEquipo[objetivoSeleccionado].getNombre())
	                     .append(" ha sido eliminado!\n");
	        }

	        areaAcciones.append(resultado.toString());

	        pasoAtaque = -1;
	        enAtaque = false;
	        turnosCompletados[equipoIndex] = true;

	    } catch (NumberFormatException e) {
	        areaAcciones.append("\nEntrada inválida. Ingresa un número entre 1 y 50 para las frutas.\n");
	    }
	}

	//paso1 es la primera pregunta y paso2 es la segunda
	public void procesarEntradaAtaque(int equipoIndex, JTextArea areaAcciones, String entradaUsuario) {
	    if (pasoAtaque == 1) {
	        procesarObjetivo(entradaUsuario, areaAcciones);
	    } else if (pasoAtaque == 2) {
	        procesarFrutas(equipoIndex, entradaUsuario, areaAcciones);
	    }
	}

	public boolean estaEnAtaque() {
	    return enAtaque;
	}
	
	

	/**
	 * DEFENSA
	 * (casi lo mismo pero con la logica de defensa: hay dos preguntas, cual herramienta eliges y con cuantas frutas quieres atacar)
	 * @return
	 */
	public boolean estaEnDefensa() {
	    return enDefensa;
	}
	
	public void comenzarDefensa(int equipoIndex, JTextArea area) {
	    enDefensa = true;
	    pasoDefensa = 1;
	    area.append("¿Qué herramienta usarás para defenderte? (Pala / Hacha / Red / Tirachinas)\n");
	}
	
	public void procesarEntradaDefensa(int equipoIndex, JTextArea area, String entrada) {
	    Equipos equipo = guardaEquipo[equipoIndex];

	    if (pasoDefensa == 0) {
	        comenzarDefensa(equipoIndex, area); // solo iniciar defensa si aún no ha empezado
	        return;
	    }

	    if (pasoDefensa == 1) {
	        herramientaSeleccionada = entrada.trim().toLowerCase();

	        if (!herramientaSeleccionada.equals("pala") &&
	            !herramientaSeleccionada.equals("hacha") &&
	            !herramientaSeleccionada.equals("red") &&
	            !herramientaSeleccionada.equals("tirachinas")) {
	            area.append("Herramienta inválida. Escribe: Pala, Hacha, Red o Tirachinas.\n");
	            return;
	        }

	        area.append("\nHas elegido la herramienta: " + herramientaSeleccionada + "\n");
	        area.append("¿Con cuántas frutas quieres reforzar tu defensa?\n");
	        pasoDefensa = 2;
	    }

	    else if (pasoDefensa == 2) {
	        try {
	            frutasUsadas = Integer.parseInt(entrada.trim());

	            if (frutasUsadas < 0 || frutasUsadas > equipo.getFrutas()) {
	                area.append("\nCantidad inválida. Tienes " + equipo.getFrutas() + " frutas disponibles.\n");
	                return;
	            }

	            equipo.setFrutas(equipo.getFrutas() - frutasUsadas);
	            int defensaExtra = calcularDefensaExtra(herramientaSeleccionada, frutasUsadas);
	            equipo.setDefensa(equipo.getDefensa() + defensaExtra);

	            area.append("\nDefensa aumentada usando " + frutasUsadas + " frutas y herramienta " + herramientaSeleccionada + ".\n");
	            area.append("Tu defensa actual es: " + equipo.getDefensa() + "\n");
	            area.append("Frutas restantes: " + equipo.getFrutas() + "\n");

	            enDefensa = false;
	            pasoDefensa = 0;
	            turnosCompletados[equipoIndex] = true;

	        } catch (NumberFormatException e) {
	            area.append("Por favor, ingresa un número válido de frutas.\n");
	        }
	    }
	}
	
	//cuanto incrementan de frutas
	private int calcularDefensaExtra(String herramienta, int frutas) {
	    switch (herramienta.toLowerCase()) {
	        case "pala":
	            return frutas + 10;
	        case "hacha":
	            return frutas + 15;
	        case "red":
	            return frutas + 20;
	        case "tirachinas":
	            return frutas + 25;
	        default:
	            return frutas;
	    }
	}



	public boolean turnoTerminado(int equipoIndex) {
	    return turnosCompletados[equipoIndex];
	}

	
	
	/**
	 * SALTA TURNO
	 * @param index
	 * @return
	 */
	public boolean turnoCompletado(int index) {
	    return turnosCompletados[index];
	}

	
	public void saltarTurno(int equipoIndex) {
		if (guardaEquipo[equipoIndex] != null) {
			JOptionPane.showMessageDialog(null,
					"El equipo " + guardaEquipo[equipoIndex].getNombre() + " ha decidido saltar el turno.",
					"Turno Saltado", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	//metido en avanzarTurno para que no se mezclen acciones
	public void resetearFlujoAccion() {
	    pasoAtaque = 0;
	    pasoDefensa = 0;
	    enAtaque = false;
	    enDefensa = false;
	    herramientaSeleccionada = null;
	    objetivoSeleccionado = -1;
	    frutasUsadas = 0;
	}

	
	/**
	 * CLASIFICACION
	 * @param puntuaje
	 */
	public void mostrarResultadosFinales(JTextArea puntuaje) {
	    puntuaje.setText("RESULTADOS FINALES\n\n");

	    // Crear una lista para ordenar por vida
	    List<String> resultados = new ArrayList<>();
	    int vidaMaxima = -1;
	    String equipoGanador = "";

	    for (int i = 0; i < equipos.length; i++) {
	        String resultado = "Equipo " + guardaEquipo[i].getNombre() + " (" + guardaEquipo[i].getEspecie() + ") - Vida restante: " + vidasRestantes[i];
	        resultados.add(resultado);

	        if (vidasRestantes[i] > vidaMaxima) {
	            vidaMaxima = vidasRestantes[i];
	            equipoGanador = "Equipo " + guardaEquipo[i].getNombre() + " (" + guardaEquipo[i].getEspecie() + ")";
	        }
	    }

	    // Ordena resultados por vida descendente 
	    resultados.sort((a, b) -> {
	        int vidaA = Integer.parseInt(a.replaceAll(".*Vida restante: ", ""));
	        int vidaB = Integer.parseInt(b.replaceAll(".*Vida restante: ", ""));
	        return Integer.compare(vidaB, vidaA);
	    });

	    for (String r : resultados) {
	        puntuaje.append(r + "\n");
	    }

	    // Mostrar al ganador ( el que mas vida tenga) 
	    puntuaje.append("\n🏆 GANADOR: " + equipoGanador + " con " + vidaMaxima + " de vida restante.\n");
	}


	private void reiniciarTurnos() {
		for (int i = 0; i < turnosCompletados.length; i++) {
			turnosCompletados[i] = false;
		}
	}

	public Equipos[] getEquipos() {
		return guardaEquipo;
	}

	public int getRondaActual() {
		return rondaActual;
	}

	public int getMaxRondas() {
		return MAX_RONDAS;
	}

	public boolean[] getTurnosCompletados() {
		return turnosCompletados;
	}

	public void marcarTurnoCompletado(int index) {
		turnosCompletados[index] = true;
	}

	public void reiniciarTurnosYAvanzarRonda() {
		reiniciarTurnos();
		rondaActual++;
	}


}
