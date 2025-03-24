package stage1;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String YELLOW = "\u001B[33m";
    private final String PURPLE = "\u001B[35m";

    private final Scanner sc = new Scanner(System.in);
    private String[] equipos = new String[5];
    private int[] vidasRestantes = {200, 200, 200, 200, 200};
    private boolean[] turnosCompletados = {false, false, false, false, false};
    private String[] especies = {"perro", "gato", "lobo", "conejo", "pulpo", "ardilla", "cabra", "oso", "ave", "mono"};
    private String[] especiesElegidas = new String[5];
    private Animal[] animalesEquipos = new Animal[5];
    private int rondaActual = 1;
    private final int MAX_RONDAS = 4;

    public void iniciarJuego() {
        registrarEquipos();
        while (rondaActual <= MAX_RONDAS) {
            mostrarRonda();
            for (int i = 0; i < equipos.length; i++) {
                if (!turnosCompletados[i]) {
                    System.out.println("Turno del equipo: " + equipos[i] + " (" + especiesElegidas[i] + ")");
                    mostrarOpcionesAccion(i);
                    turnosCompletados[i] = true;
                }
            }
            if (todosLosTurnosCompletados()) {
                reiniciarTurnos();
                rondaActual++;
            }
        }
        mostrarResultadosFinales();
    }

    private void registrarEquipos() {
        System.out.println(YELLOW + "REGISTRO DE EQUIPOS" + RESET);
        for (int i = 0; i < equipos.length; i++) {
            System.out.print("Introduce el nombre del equipo " + (i + 1) + ": ");
            equipos[i] = sc.next();
        }
        elegirEspecies();
    }

    private void elegirEspecies() {
    	 for (int i = 0; i < equipos.length; i++) {
             System.out.println("Elige una especie para el equipo " + equipos[i] + ":");
             for (int j = 0; j < especies.length; j++) {
                 System.out.println((j + 1) + ") " + especies[j]);
             }
             int eleccionEspecie = leerEntero("Especie elegida (1-10): ");
             while (eleccionEspecie < 1 || eleccionEspecie > 10) {
                 System.out.println(RED + "Opción no válida." + RESET);
                 eleccionEspecie = leerEntero("Especie elegida (1-10): ");
             }
             especiesElegidas[i] = especies[eleccionEspecie - 1];
             animalesEquipos[i] = crearAnimal(especiesElegidas[i]);
             System.out.println("Equipo " + equipos[i] + " ha elegido la especie: " + especiesElegidas[i] + " con vida: " + animalesEquipos[i].getPuntosVida());
         }
     }
    
    //se crean los animales con sus caracteristicas concretas
    private Animal crearAnimal(String especie) {
        switch (especie.toLowerCase()) {
            case "perro":
                return new Animal("perro", 200, 50);
            case "gato":
                return new Animal("gato", 200, 50);
            case "lobo":
                return new Animal("lobo", 200, 50);
            case "conejo":
                return new Animal("conejo", 200, 50);
            case "oso":
                return new Animal("oso", 400, 10);
            case "ardilla":
                return new Animal("ardilla", 100, 70);
            case "mono":
                return new Animal("mono", 100, 50);
            case "pulpo":
                return new Animal("pulpo", 200, 50);
            case "cabra":
                return new Animal("cabra", 200, 50);
            case "ave":
                return new Animal("ave", 200, 50);
            default:
                return new Animal("perro", 200, 50);
        }
    }

    private void mostrarRonda() {
        System.out.println(PURPLE + "\nRonda " + rondaActual + " de " + MAX_RONDAS + RESET);
    }

    private void mostrarOpcionesAccion(int equipoIndex) {
        System.out.println("Selecciona una acción:");
        System.out.println("1) Atacar");
        System.out.println("2) Defender");
        System.out.println("3) Saltar turno");
        int opcion = leerEntero("Elige una opción: ");

        switch (opcion) {
            case 1 -> atacar(equipoIndex);
            case 2 -> defender(equipoIndex);
            case 3 -> System.out.println("El equipo " + equipos[equipoIndex] + " ha decidido saltar el turno.");
            default -> System.out.println(RED + "Opción no válida." + RESET);
        }
    }
    private void atacar(int equipoIndex) {
        int frutas = leerEntero("Frutas a usar para atacar (0-50): ");
        while (frutas < 0 || frutas > 50) {
            System.out.println(RED + "Número inválido. Debe estar entre 0 y 50." + RESET);
            frutas = leerEntero("Frutas a usar (0-50): ");
        }

        System.out.println("Elige el equipo al que deseas atacar (1-5):");
        int equipoObjetivo = leerEntero("Equipo objetivo: ") - 1;

        // Validar que el equipo objetivo esté dentro del rango y no sea el mismo equipo
        while (equipoObjetivo < 0 || equipoObjetivo >= equipos.length || equipoObjetivo == equipoIndex) {
            System.out.println(RED + "Opción no válida. No puedes atacarte a ti mismo." + RESET);
            equipoObjetivo = leerEntero("Equipo objetivo: ") - 1;
        }

        // Verificar si el equipo objetivo ya ha sido eliminado (vida <= 0)
        if (vidasRestantes[equipoObjetivo] <= 0) {
            System.out.println(RED + "No se puede atacar al equipo " + equipos[equipoObjetivo] + " porque ya ha sido eliminado." + RESET);
            return; // Salir del método sin realizar el ataque
        }

        // Realizar el ataque
        vidasRestantes[equipoObjetivo] -= frutas;

        // Asegurarse de que la vida no sea negativa
        if (vidasRestantes[equipoObjetivo] < 0) {
            vidasRestantes[equipoObjetivo] = 0;
        }

        System.out.println("El equipo " + equipos[equipoIndex] + " ha gastado " + frutas + " frutas y ha atacado al equipo " + equipos[equipoObjetivo] + ".");
        System.out.println("Vida restante del equipo " + equipos[equipoObjetivo] + ": " + vidasRestantes[equipoObjetivo]);

        // Verificar si el equipo objetivo ha sido eliminado después del ataque
        if (vidasRestantes[equipoObjetivo] == 0) {
            System.out.println(YELLOW + "El equipo " + equipos[equipoObjetivo] + " ha sido eliminado." + RESET);
        }
    }
    private void defender(int equipoIndex) {

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
            System.out.println("El equipo " + equipos[equipoIndex] + " ha usado la herramienta y ha mejorado su defensa en " + (defensaExtra + frutas * 2) + " puntos.");
        } else {
            System.out.println(RED + "Opción de herramienta no válida." + RESET);
        }
    }

    private void mostrarResultadosFinales() {
        System.out.println(YELLOW + "\nRESULTADOS FINALES" + RESET);
        for (int i = 0; i < equipos.length; i++) {
            System.out.println("Equipo " + equipos[i] + " (" + especiesElegidas[i] + ") - Vida restante: " + vidasRestantes[i]);
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
            if (!turno) return false;
        }
        return true;
    }

    private void reiniciarTurnos() {
        for (int i = 0; i < turnosCompletados.length; i++) {
            turnosCompletados[i] = false;
        }
    }
	}
