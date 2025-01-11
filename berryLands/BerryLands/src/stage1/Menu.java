package stage1;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
	   private final String RESET = "\u001B[0m";
	    private final String RED = "\u001B[31m";
	    private final String GREEN = "\u001B[32m";
	    private final String YELLOW = "\u001B[33m";
	    private final String PURPLE = "\u001B[35m";

	    private boolean salir = false;
	    private final Scanner sc = new Scanner(System.in);
	    private final String[] especies = {"perros", "gatos", "lobos", "conejos", "pulpos", "ratón", "cabras", "osos", "aves", "monos"};
	    
	    private Animal crearAnimal(String especie) {
	        switch (especie.toLowerCase()) {
	            case "perro":
	                return new Animal("Perro", 200, 50);
	            case "gato":
	                return new Animal("Gato", 200, 50);
	            case "lobo":
	                return new Animal("Lobo", 200, 50);
	            case "conejo":
	                return new Animal("Conejo", 200, 50);
	            case "elefante":
	                return new Animal("Elefante", 400, 10);
	            case "raton":
	                return new Animal("Ratón", 100, 50); 
	            // Agrega más casos según tus especies
	            default:
	                return new Animal("Perro", 200, 50); // Especie predeterminada
	        }
	    }

	    public void iniciar() {
	        while (!salir) {
	            mostrarMenu();
	            int opcion = leerEntero("Elige la opción deseada: ");
	            procesarOpcion(opcion);
	        }
	        sc.close();
	    }

	    private void mostrarMenu() {
	        System.out.println("");
	        System.out.println(YELLOW + " ~ MENÚ BERRYLANDS ~ " + RESET);
	        System.out.println("-------------------------------");
	        System.out.println("1) JUGAR");
	        System.out.println("2) REGLAS DEL JUEGO");
	        System.out.println("3) INFORMACIÓN");
	        System.out.println("4) GUARDAR PARTIDA");
	        System.out.println("0) SALIR");
	        System.out.println("-------------------------------");
	    }

	    private int leerEntero(String mensaje) {
	        while (true) {
	            try {
	                System.out.print(mensaje);
	                return sc.nextInt();
	            } catch (Exception e) {
	                System.out.println(RED + "Error: Debes introducir un número válido." + RESET);
	                sc.nextLine(); // Limpiar entrada
	            }
	        }
	    }

	    private void procesarOpcion(int opcion) {
	        switch (opcion) {
	            case 1:
	                jugar();
	                break;
	            case 2:
	                mostrarReglas();
	                break;
	            case 3:
	                mostrarInformacion();
	                break;
	            case 4:
	            	//guardarPartida();
	                System.out.println("Guardar partida");
	                break;
	            case 0:
	                salir = true;
	                System.out.println("Saliendo...");
	                break;
	            default:
	                System.out.println("La opción que has elegido no está disponible");
	                break;
	        }
	    }

	    private void jugar() {
	    	System.out.println("Comienza el juego...");
	        Animal[] animalesEquipos = new Animal[5];
	        String[] equipos = new String[5];
	        String[] especiesEquipos = new String[5];
	        
	        

	        for (int i = 0; i < 5; i++) {
	            System.out.print("\nIntroduce el nombre del equipo " + (i + 1) + ": ");
	            equipos[i] = sc.next();
	            
	            System.out.println("\nElige una especie para el equipo (opciones disponibles):");
	            for (int j = 0; j < especies.length; j++) {
	                System.out.println((j + 1) + ") " + especies[j]);
	            }
	            int eleccionEspecie = leerEntero("\nElige el número de la especie: ") - 1;
	            String especieElegida;


	            if (eleccionEspecie >= 0 && eleccionEspecie < especies.length) {
	                animalesEquipos[i] = crearAnimal(especies[eleccionEspecie]);
	                especieElegida = especies[eleccionEspecie];
	                System.out.println("Has elegido: " + especies[eleccionEspecie]);
	            } else {
	                // Si la elección no es válida, asignar valores predeterminados
	                especieElegida = "perro";  // Predeterminado
	               
	            }

	            // Verificar que el animal fue asignado correctamente
	            if (animalesEquipos[i] != null) {
	                System.out.println("Equipo " + equipos[i] + " ha elegido una especie ");
	            } else {
	                System.out.println("Error: El equipo " + equipos[i] + " no tiene un animal asignado. Por defecto se le asignará " + YELLOW +"Perros" + RESET);
	            }

	            // Crear un objeto Animal para el equipo
	            animalesEquipos[i] = new Animal(especieElegida);
	        }
	        
	        // Ciclo de rondas
	        for (int ronda = 0; ronda < 4; ronda++) {
	            System.out.println("\n-----------");
	            System.out.println("| RONDA " + (ronda + 1) + " |");
	            System.out.println("-----------");

	            for (Animal animal : animalesEquipos) {
	                if (animal.getPuntosVida() > 0) {
	                    animal.recolectarFrutas(); // Cada equipo recolecta frutas
	                }
	            }

	            for (int i = 0; i < 5; i++) {
	                animalesEquipos[i].setFrutasAcumuladas(50); // Cada equipo tiene 50 frutas por ronda
	            }

	            // Turnos de los jugadores
	            for (int i = 0; i < 5; i++) {
	                Animal atacante = animalesEquipos[i];
	                if (atacante.getPuntosVida() > 0) {
	                    System.out.println("\nTurno del Equipo " + (i + 1) + " (" + equipos[i] + ", " + atacante.getEspecie() + ")");
	                    
	                    // Mostrar opciones: Atacar, Defender o Saltar turno
	                    System.out.println("\nSelecciona una acción:");
	                    System.out.println("1) Atacar");
	                    System.out.println("2) Defender");
	                    System.out.println("3) Saltar turno");
	                    
	                    int opcion = leerEntero("Elige una opción: ");
	                    
	                    switch (opcion) {
	                        case 1: // Atacar
	                            System.out.println("\nSelecciona un equipo para atacar:");
	                            for (int j = 0; j < 5; j++) {
	                                if (i != j && animalesEquipos[j].getPuntosVida() > 0) {
	                                    System.out.println((j + 1) + ") Equipo " + equipos[j] + " (" + animalesEquipos[j].getEspecie() + ")");
	                                }
	                            }
	                            int equipoAtacado = leerEntero("\nElige el número del equipo a atacar: ") - 1;

	                            if (equipoAtacado >= 0 && equipoAtacado < 5 && equipoAtacado != i && animalesEquipos[equipoAtacado].getPuntosVida() > 0) {
	                                int frutasUsadas = leerEntero("\nIntroduce el número de frutas para atacar (máx. " 
	                                                              + atacante.getFrutasAcumuladas() + "): ");
	                                frutasUsadas = Math.min(frutasUsadas, atacante.usarFrutas(frutasUsadas));
	                                Animal defensor = animalesEquipos[equipoAtacado];

	                               
	                                int daño = atacante.ataque(defensor.getEspecie(), frutasUsadas);
	                                int dañoRecibido = defensor.defensa(daño);
	                                defensor.recibirAtaque(dañoRecibido);

	                                System.out.println("\nEl Equipo " + equipos[i] + " (" + atacante.getEspecie() + ") atacó al Equipo " 
	                                                   + equipos[equipoAtacado] + " (" + defensor.getEspecie() + ")");
	                                System.out.println("\tDaño realizado: " + dañoRecibido);
	                                System.out.println("\tVidas restantes del Equipo " + equipos[equipoAtacado] + ": " 
	                                                   + defensor.getPuntosVida());
	                            } else {
	                                System.out.println("\tAtaque no válido. Turno perdido.");
	                            }
	                            break;

	                        case 2: // Defender
	                            System.out.println("\nEl equipo " + equipos[i] + " ha decidido defenderse.");

	                            // Aquí el equipo no ataca, solo se defenderá. La lógica de defensa es la misma que en "Atacar".
	                            System.out.println("¿Quieres usar una herramienta para defenderte?");
	                            System.out.println("1) Sí");
	                            System.out.println("2) No");
	                            int decisionDefensa2 = leerEntero("Elige una opción: ");

	                            int defensaExtra2 = 0;
	                            if (decisionDefensa2 == 1) {
	                                // Opciones de herramientas de defensa
	                                System.out.println("\nElige una herramienta para defenderte:");
	                                System.out.println("1) Pala (+10 defensa)");
	                                System.out.println("2) Hacha (+15 defensa)");
	                                System.out.println("3) Red (+20 defensa)");
	                                System.out.println("4) Tirachinas (+25 defensa)");

	                                int herramientaElegida2 = leerEntero("\nIntroduce el número de la herramienta: ");
	                                defensaExtra2 = switch (herramientaElegida2) {
	                                    case 1 -> 10;
	                                    case 2 -> 15;
	                                    case 3 -> 20;
	                                    case 4 -> 25;
	                                    default -> 0; // No válida
	                                };

	                                if (defensaExtra2 > 0) {
	                                    // Permitir al jugador gastar frutas para activar la herramienta de defensa
	                                    int frutasGastadas2 = leerEntero("\nIntroduce el número de frutas a gastar para la defensa (máx. 50): ");
	                                    frutasGastadas2 = Math.min(frutasGastadas2, 50); // Limitar a un máximo de 50 frutas

	                                    if (atacante.getFrutasAcumuladas() >= frutasGastadas2) {
	                                        atacante.usarFrutas(frutasGastadas2); // Gasta las frutas para activar la herramienta
	                                        System.out.println("\nDefensa mejorada con " + defensaExtra2 + " puntos, gastando " + frutasGastadas2 + " frutas.");
	                                    } else {
	                                        System.out.println("\nNo tienes suficientes frutas para usar esta defensa.");
	                                    }
	                                }
	                            }

	                            System.out.println("El equipo " + equipos[i] + " ha fortalecido su defensa con " + defensaExtra2 + " puntos.");
	                            break;

	                        case 3: // Saltar turno
	                            System.out.println("\nEl equipo " + equipos[i] + " ha decidido saltar su turno.");
	                            break;

	                        default:
	                            System.out.println("\nOpción no válida. Turno perdido.");
	                            break;
	                    }
	                }
	            }

	       // Resultado final
	          System.out.println("\nEstado final de los equipos:");
	          for (int i = 0; i < 5; i++) {
	          Animal animal = animalesEquipos[i];
	          System.out.println("Equipo " + equipos[i] + " (" + animal.getEspecie() + ") -> " 
	                             + animal.getPuntosVida() + " vidas restantes.");
	          }
	        }
	    }
	    

	    private void mostrarReglas() {
	        System.out.println(PURPLE + "\n========== NORMAS DEL JUEGO ========== " + RESET);
	        System.out.println(YELLOW + "Equipos:" + RESET);
	        System.out.println("\t- Hay 5 equipos participantes.");
	        System.out.println(YELLOW + "\nVidas:" + RESET);
	        System.out.println("\t- Cada equipo comienza con 200 vidas.");
	        System.out.println(YELLOW + "\nFrutas por Ronda:" + RESET);
	        System.out.println("\t- Cada equipo tiene 50 frutas de ataque por ronda.");
	        System.out.println(RED + "\n¡Gana el último equipo que tenga vidas!" + RESET);
	    }

	    private void mostrarInformacion() {
	        System.out.println("Información sobre:");
	        System.out.println("- a. Versión");
	        System.out.println("- b. Contacto");
	        System.out.println("- c. E-mail");
	        System.out.print("Elige una opción (a, b, c): ");
	        String opcion = sc.next();

	        switch (opcion.toLowerCase()) {
	            case "a":
	                System.out.println("Versión 1.0");
	                break;
	            case "b":
	                System.out.println("Contacto: BerryLandCreators@gmail.com");
	                break;
	            case "c":
	                System.out.println("Autores: Berry");
	                break;
	            default:
	                System.out.println("Opción no válida");
	        }
	    }
	}
