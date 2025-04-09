package stage1;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import ui.Equipos;

/**
 * Todos los syso deben pasarse a la interfaz con un getText() para que se
 * muestren en el JTextArea e ir añadiendo las referencias a metodos necesarias
 */

public class Menu {
	private final String RESET = "\u001B[0m";
	private final String RED = "\u001B[31m";
	private final String YELLOW = "\u001B[33m";
	private final String PURPLE = "\u001B[35m";

	private final Scanner sc = new Scanner(System.in);
	private String[] equipos = new String[5];

	Equipos[] guardaEquipo = new Equipos[5];

	private int[] vidasRestantes = { 200, 200, 200, 200, 200 };
	private boolean[] turnosCompletados = { false, false, false, false, false };
	private String[] especies = { "perro", "gato", "lobo", "conejo", "pulpo", "ardilla", "cabra", "oso", "ave",
			"mono" };
	private String[] especiesElegidas = new String[5];
	private Animal[] animalesEquipos = new Animal[5];
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
	
	

//	public void iniciarJuego() {
//
//		while (rondaActual <= MAX_RONDAS) {
//			mostrarRonda();
//			for (int i = 0; i < equipos.length; i++) {
//				if (!turnosCompletados[i]) {
//					System.out.println("Turno del equipo: " + equipos[i] + " (" + especiesElegidas[i] + ")");
//					mostrarOpcionesAccion(i);
//					turnosCompletados[i] = true;
//				}
//			}
//			if (todosLosTurnosCompletados()) {
//				reiniciarTurnos();
//				rondaActual++;
//			}
//		}
//		mostrarResultadosFinales();
//	}

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

		// Si por alguna razón la especie es nula o está vacía, se asigna "perro" por
		// defecto.
		if (especie == null || especie.trim().isEmpty()) {
			especie = "Perro";
		}

		// Crear el objeto Equipos con los datos proporcionados.
		Equipos equipo = new Equipos(nombre, especie);
		setEquipo(index, equipo);

		// Muestra una ventana emergente confirmando el guardado.
		JOptionPane.showMessageDialog(null, "Equipo " + (index + 1) + " asignado correctamente.", "Registro de Equipos",
				JOptionPane.INFORMATION_MESSAGE);
	}
  

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
	
	public boolean estaEnProcesoAtaque() {
	    return enProcesoAtaque;
	}

	public void atacar(int equipoIndex, JTextArea areaAcciones) {
	    if (pasoAtaque == 0) {
	        comenzarAtaque(equipoIndex, areaAcciones);
	        return;
	    }
	    
	    String input = obtenerUltimaLinea(areaAcciones);
	    
	    if (pasoAtaque == 1) {
	        procesarObjetivo(input, areaAcciones);
	    } else if (pasoAtaque == 2) {
	        procesarFrutas(equipoIndex, input, areaAcciones);
	    }
	}

	private void comenzarAtaque(int equipoIndex, JTextArea areaAcciones) {
	    equipoAtacanteActual = equipoIndex;
	    pasoAtaque = 1;
	    objetivoSeleccionado = -1;
	    frutasUsadas = 0;
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("¿A qué equipo quieres atacar? (Escribe 1-5)\n\n");
	    
	    // Mostrar solo equipos disponibles (1-5)
	    for (int i = 0; i < 5; i++) { // Solo mostrar opciones 1-5
	        if (i != equipoIndex && guardaEquipo[i] != null && vidasRestantes[i] > 0) {
	            sb.append(i+1).append(") ").append(guardaEquipo[i].getNombre())
	              .append(" (Vida: ").append(vidasRestantes[i]).append(")\n");
	        }
	    }
	    
	    areaAcciones.setText(sb.toString());
	    areaAcciones.setEditable(true);
	}

	public void procesarObjetivo(String entradaUsuario, JTextArea areaAcciones) {
	    try {
	        int numEquipo = Integer.parseInt(entradaUsuario.trim());

	        // Validar que esté entre 1 y el número total de equipos
	        if (numEquipo < 1 || numEquipo > guardaEquipo.length) {
	            areaAcciones.append("Número de equipo inválido. Ingresa un número entre 1 y " + guardaEquipo.length + ":\n");
	            return;
	        }

	        objetivoSeleccionado = numEquipo - 1;

	        if (objetivoSeleccionado == equipoAtacanteActual) {
	            areaAcciones.append("No puedes atacarte a ti mismo. Elige otro equipo:\n");
	            return;
	        }
	        
	        //mostrarOpcionesAtaque(numEquipo, areaAcciones, entradaUsuario);

	        // Confirmación y avanzar al siguiente paso
	        areaAcciones.append("Equipo " + (objetivoSeleccionado + 1) + " seleccionado como objetivo.\n");
	        areaAcciones.append("¿Cuántas frutas deseas usar para el ataque? (entre 1 y 50):\n");

	        pasoAtaque = 2;

	    } catch (NumberFormatException e) {
	        areaAcciones.append("Entrada inválida. Por favor, ingresa un número válido para seleccionar el equipo.\n");
	    }
	}
	
	private void mostrarOpcionesAtaque(int equipoIndex, JTextArea areaAcciones, String mensajeError) {
	    StringBuilder sb = new StringBuilder();
	    if (mensajeError != null) {
	        sb.append(mensajeError).append("\n\n");
	    }
	    
	    sb.append("¿A qué equipo quieres atacar? (Escribe 1-5)\n\n");
	    
	    for (int i = 0; i < 5; i++) {
	        if (guardaEquipo[i] != null) {
	            sb.append(i+1).append(") ")
	              .append(guardaEquipo[i].getNombre())
	              .append(" (").append(guardaEquipo[i].getEspecie()).append(")")
	              .append(" - Vida: ").append(vidasRestantes[i])
	              .append("\n");
	        }
	    }
	    
	    areaAcciones.setText(sb.toString());
	    areaAcciones.setEditable(true);
	}

	private void procesarFrutas(int equipoIndex, String input, JTextArea areaAcciones) {
	    try {
	        frutasUsadas = Integer.parseInt(input.trim());
	        
	        if (frutasUsadas < 1 || frutasUsadas > 50) {
	            areaAcciones.setText("Número de frutas no válido. Debe ser entre 1 y 50.\n\n" +
	                               "¿Con cuántas frutas quieres atacar? (1-50)\n");
	            return;
	        }
	        
	        // Realizar ataque
	        vidasRestantes[objetivoSeleccionado] -= frutasUsadas;
	        if (vidasRestantes[objetivoSeleccionado] < 0) {
	            vidasRestantes[objetivoSeleccionado] = 0;
	        }
	        
	        // Mostrar resultado
	        StringBuilder resultado = new StringBuilder();
	        resultado.append("--- RESUMEN DEL ATAQUE ---\n")
	                .append("Atacante: ").append(guardaEquipo[equipoIndex].getNombre()).append("\n")
	                .append("Objetivo: ").append(guardaEquipo[objetivoSeleccionado].getNombre()).append("\n")
	                .append("Frutas usadas: ").append(frutasUsadas).append("\n")
	                .append("Vida restante objetivo: ").append(vidasRestantes[objetivoSeleccionado]).append("\n");
	        
	        if (vidasRestantes[objetivoSeleccionado] == 0) {
	            resultado.append("¡").append(guardaEquipo[objetivoSeleccionado].getNombre())
	                    .append(" ha sido eliminado!\n");
	        }
	        
	        areaAcciones.setText(resultado.toString());
	        areaAcciones.setEditable(false);
	        
	        // Finalizar ataque
	        pasoAtaque = 0;
	        turnosCompletados[equipoIndex] = true;
	        
	    } catch (NumberFormatException e) {
	        areaAcciones.setText("Debes ingresar un número válido (1-50).\n\n" +
	                           "¿Con cuántas frutas quieres atacar? (1-50)\n");
	    }
	}

	private String obtenerUltimaLinea(JTextArea area) {
	    String text = area.getText();
	    String[] lines = text.split("\n");
	    return lines.length > 0 ? lines[lines.length-1].trim() : "";
	}

	public boolean estaEnAtaque() {
	    return pasoAtaque > 0;
	}

	
	
	public void defender(int equipoIndex) {

		System.out.println("Elige una herramienta para defenderte:");
		System.out.println("1) Pala (+10 defensa)");
		System.out.println("2) Hacha (+15 defensa)");
		System.out.println("3) Red (+20 defensa)");
		System.out.println("4) Tirachinas (+25 defensa)");

		int herramienta = leerEntero("Elige una opción (1-4): ");
		int defensaExtra = switch (herramienta) {
		case 1 -> 10;
		case 2 -> 15;
		case 3 -> 20;
		case 4 -> 25;
		default -> 0;
		};

		if (defensaExtra > 0) {
			int frutas = leerEntero("Frutas a gastar para mejorar defensa (0-50): ");
			frutas = Math.min(frutas, 50);

			vidasRestantes[equipoIndex] += defensaExtra + (frutas * 2);
			System.out.println(
					"El equipo " + equipos[equipoIndex] + " ha usado la herramienta y ha mejorado su defensa en "
							+ (defensaExtra + frutas * 2) + " puntos.");
		} else {
			System.out.println(RED + "Opción de herramienta no válida." + RESET);
		}
	}

	public void saltarTurno(int equipoIndex) {
		if (guardaEquipo[equipoIndex] != null) {
			JOptionPane.showMessageDialog(null,
					"El equipo " + guardaEquipo[equipoIndex].getNombre() + " ha decidido saltar el turno.",
					"Turno Saltado", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void mostrarResultadosFinales() {
		System.out.println(YELLOW + "\nRESULTADOS FINALES" + RESET);
		for (int i = 0; i < equipos.length; i++) {
			System.out.println(
					"Equipo " + equipos[i] + " (" + especiesElegidas[i] + ") - Vida restante: " + vidasRestantes[i]);
		}
	}

	private int leerEntero(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println(RED + "Error: Debes introducir un número válido." + RESET);
				sc.nextLine();
			}
		}
	}

	private boolean todosLosTurnosCompletados() {
		for (boolean turno : turnosCompletados) {
			if (!turno)
				return false;
		}
		return true;
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
