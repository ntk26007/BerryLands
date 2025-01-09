package stage1;

import java.util.Scanner;

public class Menu {
	   private final String RESET = "\u001B[0m";
	    private final String RED = "\u001B[31m";
	    private final String GREEN = "\u001B[32m";
	    private final String YELLOW = "\u001B[33m";
	    private final String PURPLE = "\u001B[35m";

	    private boolean salir = false;
	    private final Scanner sc = new Scanner(System.in);
	    private final String[] especies = {"perros", "gatos", "lobos", "conejos", "pulpos", "ovejas", "cabras", "leones", "aves"};

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
	        System.out.println(YELLOW + " ~ MENÚ ANIMAL CROSSING ~ " + RESET);
	        System.out.println("-------------------------------");
	        System.out.println("1) JUGAR");
	        System.out.println("2) REGLAS DEL JUEGO");
	        System.out.println("3) INFORMACIÓN");
	        System.out.println("4) APARTADO ABIERTO");
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
	                System.out.println("Próximamente");
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
	    	    String[] equipos = new String[5];
	    	    String[] especiesEquipos = new String[5];

	    	    for (int i = 0; i < 5; i++) {
	    	        System.out.print("Introduce el nombre del equipo " + (i + 1) + ": ");
	    	        equipos[i] = sc.next();
	    	        System.out.println("Elige una especie para el equipo (opciones disponibles):");
	    	        for (int j = 0; j < especies.length; j++) {
	    	            System.out.println((j + 1) + ") " + especies[j]);
	    	        }
	    	        int eleccionEspecie = leerEntero("Elige el número de la especie: ") - 1;
	    	        if (eleccionEspecie >= 0 && eleccionEspecie < especies.length) {
	    	            especiesEquipos[i] = especies[eleccionEspecie];
	    	        } else 
	    	            especiesEquipos[i] = "perros";
	    	        
	    	    }

	    	    int[] vidas = {200, 200, 200, 200, 200};

	    	    for (int ronda = 0; ronda < 4; ronda++) {
	    	        System.out.println("\n-----------");
	    	        System.out.println("| RONDA " + (ronda + 1) + " |");
	    	        System.out.println("-----------");

	    	        int[] bayasRestantes = {50, 50, 50, 50, 50};
	    	        int[] defensa = new int[5];

	    	        for (int i = 0; i < 5; i++) {
	    	            System.out.println(" · Equipo " + (i + 1) + " -> " + vidas[i] + " vidas");
	    	        }
	    	        System.out.println("");

	    	        if (ronda < 3) {
	    	            for (int i = 0; i < 3; i++) {
	    	                if (vidas[i] > 0) {
	    	                    System.out.println(YELLOW + "TURNO DE ATAQUE DEL EQUIPO = equipo 5 " + RESET);
	    	                    System.out.println("·······························");
	    	                    System.out.println(" 1) Equipo 1 \n 2) Equipo 2 \n 3) Equipo 3 \n 4) Equipo 4 \n 6) Bayas restantes para la defensa \n 0) Saltar turno");
	    	                    System.out.println("·······························");
	    	                    System.out.println("Bayas disponibles por ronda: " + bayasRestantes[i]);

	    	                    int accion = -1;
	    	                    boolean entradaValida = false;
	    	                    while (!entradaValida) {
	    	                        System.out.print("  º Introduzca el número del equipo que desea atacar: ");
	    	                        String entrada = sc.next();
	    	                        if (entrada.matches("\\d")) {
	    	                            accion = Integer.parseInt(entrada);
	    	                            if (accion >= 0 && accion <= 6) {
	    	                                entradaValida = true;
	    	                            } else {
	    	                                System.out.println(RED + "Opción no válida. Por favor, elige un número entre 0 y 6" + RESET);
	    	                            }
	    	                        } else {
	    	                            System.out.println(RED + "Error: Debes introducir un número entre 0 y 6" + RESET);
	    	                        }
	    	                    }

	    	                    if (accion >= 1 && accion <= 4) {
	    	                        int equipoAtaque = accion;

	    	                        if (vidas[equipoAtaque - 1] > 0) {
	    	                            int bayasUsadas = -1;
	    	                            entradaValida = false;

	    	                            while (!entradaValida) {
	    	                                System.out.print("  º Introduce el número de bayas con las que quieres atacar (máx. " + bayasRestantes[i] + "): ");
	    	                                String entradaBayas = sc.next();

	    	                                if (entradaBayas.matches("\\d+")) {
	    	                                    bayasUsadas = Integer.parseInt(entradaBayas);

	    	                                    if (bayasUsadas <= bayasRestantes[i]) {
	    	                                        entradaValida = true;
	    	                                    } else {
	    	                                        System.out.println(RED + "No puedes usar más bayas de las disponibles" + RESET);
	    	                                    }
	    	                                } else {
	    	                                    System.out.println(RED + "Error: Debes introducir un número válido" + RESET);
	    	                                }
	    	                            }

	    	                            bayasRestantes[i] -= bayasUsadas;

	    	                            int dañoRealizado = bayasUsadas - defensa[equipoAtaque - 1];
	    	                            if (dañoRealizado > 0) {
	    	                                vidas[equipoAtaque - 1] -= dañoRealizado;

	    	                                if (vidas[equipoAtaque - 1] < 0) {
	    	                                    vidas[equipoAtaque - 1] = 0;
	    	                                }
	    	                                System.out.println("\n Ataque realizado al Equipo " + GREEN + equipoAtaque + RESET + " \n Vidas restantes del equipo atacado:  ¦" + vidas[equipoAtaque - 1] + "¦");
	    	                            } else {
	    	                                System.out.println("\n El ataque fue bloqueado por la defensa del " + GREEN + "Equipo " + equipoAtaque + RESET);
	    	                            }

	    	                            System.out.println("  \u00abBayas disponibles\u00bb : " + bayasRestantes[i]);
	    	                            System.out.println("");
	    	                        } else {
	    	                            System.out.println(RED + "Equipo ya eliminado o no válido" + RESET);
	    	                            System.out.println("");
	    	                        }

	    	                    } else if (accion == 6) {
	    	                        System.out.println("\n Elige una herramienta para defenderte:");
	    	                        System.out.println("1) Pala");
	    	                        System.out.println("2) Hacha");
	    	                        System.out.println("3) Red");
	    	                        System.out.println("4) Tirachinas");

	    	                        int herramientaDefensa = -1;
	    	                        while (herramientaDefensa < 1 || herramientaDefensa > 4) {
	    	                            System.out.print("  º Introduce el número de la herramienta para defenderte: ");
	    	                            String entradaHerramienta = sc.next();

	    	                            if (entradaHerramienta.matches("\\d+")) {
	    	                                herramientaDefensa = Integer.parseInt(entradaHerramienta);
	    	                                if (herramientaDefensa < 1 || herramientaDefensa > 4) {
	    	                                    System.out.println(RED + "Opción no válida. Elige entre 1 y 4" + RESET);
	    	                                }
	    	                            } else {
	    	                                System.out.println(RED + "Error: Debes introducir un número válido" + RESET);
	    	                            }
	    	                        }

	    	                        defensa[i] += 10; // Se añade un valor fijo a la defensa por usar una herramienta (por ejemplo, 10 de defensa por cada herramienta)
	    	                        bayasRestantes[i] -= 5; // Se gastan 5 bayas para la defensa

	    	                        System.out.println("\n Defensa activada con " + getHerramienta(herramientaDefensa) + " --> Usarás 5 bayas para defenderte.");
	    	                        System.out.println("\t\u00abBayas disponibles\u00bb : " + bayasRestantes[i]);
	    	                        System.out.println("");

	    	                    } else if (accion == 0) {
	    	                        System.out.println(PURPLE + "Has decidido saltar el turno" + RESET);
	    	                        System.out.println("");
	    	                    } else {
	    	                        System.out.println(RED + "Opción no válida" + RESET);
	    	                        System.out.println("");
	    	                    }
	    	                }
	    	            }
	    	        }
	    	    }

	    	    System.out.println("\nEstado final de los equipos:");
	    	    for (int i = 0; i < 5; i++) {
	    	        System.out.println("Equipo " + equipos[i] + " (" + especiesEquipos[i] + ") tiene " + vidas[i] + " vidas restantes");
	    	    }
	    	}

	    	private String getHerramienta(int numHerramienta) {
	    	    switch (numHerramienta) {
	    	        case 1: return "Pala";
	    	        case 2: return "Hacha";
	    	        case 3: return "Red";
	    	        case 4: return "Tirachinas";
	    	        default: return "Desconocida";
	    	    }
	    	}

	    

	    private void mostrarReglas() {
	        System.out.println(PURPLE + "\n========== NORMAS DEL JUEGO ========== " + RESET);
	        System.out.println(YELLOW + "Equipos:" + RESET);
	        System.out.println("\t- Hay 5 equipos participantes.");
	        System.out.println(YELLOW + "\nVidas:" + RESET);
	        System.out.println("\t- Cada equipo comienza con 200 vidas.");
	        System.out.println(YELLOW + "\nMisiles por Ronda:" + RESET);
	        System.out.println("\t- Cada equipo tiene 50 misiles de ataque por ronda.");
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
	                System.out.println("Contacto: creadoresMisiles@gmail.com");
	                break;
	            case "c":
	                System.out.println("Autores: Equipo de Desarrollo Misiles");
	                break;
	            default:
	                System.out.println("Opción no válida");
	        }
	    }
	}



