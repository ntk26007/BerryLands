package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import stage1.Menu;

public class PanelAtaque extends JFrame implements ActionListener {

	private JButton siguiente;
	private JButton atacar;
	private JButton defender;
	private JButton saltarTurno;

	private JTextArea areaRonda;
	private JTextArea areaAcciones;
	private final Menu menu;
	private int equipoActual = 0;
	private int rondaActual = 1;
	private Equipos[] equiposAsignados;

	private JTextField campoEntradaAtaque; // para escribir el número para ataque 
	private JButton botonConfirmar; // para enviar la respuesta

	public PanelAtaque(Equipos[] equiposAsignados) {
		this.equiposAsignados = equiposAsignados;
		this.menu = new Menu(); // Se usa constructor vacío
		this.menu.setEquipos(equiposAsignados); // Se asignan los equipos desde aquí

		setTitle("Ataque");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		Background fondo3 = new Background("Assets/fondoAtaque.png");
		setContentPane(fondo3);
		fondo3.setLayout(null);

		// siguiente
		siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		siguiente.setBounds(1250, 10, 200, 150);
		siguiente.addActionListener(e -> new PanelClasificación(menu));
		fondo3.add(siguiente);

		// Área de texto que muestra ronda y turnos
		areaRonda = new JTextArea();
		areaRonda.setEditable(false);
		areaRonda.setLineWrap(true);
		JScrollPane scrollRonda = new JScrollPane(areaRonda);
		scrollRonda.setBounds(200, 100, 450, 150);
		fondo3.add(scrollRonda);

		// Área de opciones de acción
		areaAcciones = new JTextArea();
		areaAcciones.setEditable(false);
		areaAcciones.setLineWrap(true);
		JScrollPane scrollAcciones = new JScrollPane(areaAcciones);
		scrollAcciones.setBounds(170, 580, 500, 150);
		fondo3.add(scrollAcciones);

		// Área de seleccion
		campoEntradaAtaque = new JTextField();
		campoEntradaAtaque.setBounds(230, 280, 150, 50);
		campoEntradaAtaque.setColumns(10);

		botonConfirmar = new JButton("Enviar Respuesta");
		botonConfirmar.setBounds(480, 280, 150, 50);

		fondo3.add(campoEntradaAtaque);
		fondo3.add(botonConfirmar);

		// Botones de acción
		atacar = new JButton("Atacar");
		atacar.setBounds(1080, 580, 150, 50);
		fondo3.add(atacar);

		defender = new JButton("Defender");
		defender.setBounds(1080, 640, 150, 50);
		fondo3.add(defender);

		saltarTurno = new JButton("Saltar Turno");
		saltarTurno.setBounds(1080, 700, 150, 50);
		fondo3.add(saltarTurno);

		atacar.addActionListener(e -> {
			if (equiposAsignados[equipoActual] != null) {
				if (!menu.estaEnAtaque()) {
					menu.comenzarAtaque(equipoActual, areaAcciones);
				}
			}
		});

		defender.addActionListener(e -> {
		    if (equiposAsignados[equipoActual] != null) {
		        if (!menu.estaEnDefensa() && !menu.estaEnAtaque()) {
		            areaAcciones.setText(""); // Limpia el área completamente
		            menu.comenzarDefensa(equipoActual, areaAcciones);
		        }
		    }
		});

		
		saltarTurno.addActionListener(e -> {
			if (equiposAsignados[equipoActual] != null) {
				menu.saltarTurno(equipoActual);
				avanzarTurno();
				mostrarOpcionesAccion();
			}
		});

		//boton para mandar las respuestas (atacar y defender)
		botonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entrada = campoEntradaAtaque.getText();
				campoEntradaAtaque.setText("");

				menu.procesarEntradaAtaque(equipoActual, areaAcciones, entrada);

				if (!menu.estaEnAtaque() && menu.getTurnosCompletados()[equipoActual]) {
					avanzarTurno();
					mostrarOpcionesAccion();
					
				} else if (menu.estaEnDefensa()) {
                    menu.procesarEntradaDefensa(equipoActual, areaAcciones, entrada);
                    if (!menu.estaEnDefensa() && menu.turnoTerminado(equipoActual)) {
                        avanzarTurno();
                        mostrarOpcionesAccion();
                    }
                }
            }
        });

		mostrarEstadoJuego();
		mostrarOpcionesAccion();

		setVisible(true);
	}
	
	
	/**
	 * Metodos auxiliares
	 */
    //modificado para que no se mezclen frases de ataque y defensa
	private void avanzarTurno() {
	    equipoActual++;
	    if (equipoActual >= 5) {
	        equipoActual = 0;
	        rondaActual++;
	    }

	    // Reinicia el estado interno del flujo de ataque/defensa
	    menu.resetearFlujoAccion();

	    if (rondaActual > 4) {
	        areaRonda.setText("¡Se ha terminado el juego! Pulsa la flecha superior para ver los resultados.");

	        // Desactivar botones cuando se acaban todas las rondas
	        atacar.setEnabled(false);
	        defender.setEnabled(false);
	        saltarTurno.setEnabled(false);
	        botonConfirmar.setEnabled(false);
	        campoEntradaAtaque.setEnabled(false); // desactiva el campo de entrada
	    } else {
	        mostrarEstadoJuego(); // Se muestra la nueva ronda/turno
	    }
	}


	//muestra rondas y turno de X jugador en otro textArea distinto
	private void mostrarEstadoJuego() {
		String texto = "Ronda " + rondaActual + " de 4\n";
		texto += "Turno del equipo " + (equipoActual + 1) + ": ";

		if (equiposAsignados[equipoActual] != null) {
			texto += equiposAsignados[equipoActual].getNombre() + " (" + equiposAsignados[equipoActual].getEspecie()
					+ ")";
		} else {
			texto += "No asignado";
		}
		areaRonda.setText(texto);
	}

	//muestra por uno de los text las opciones al jugador
	private void mostrarOpcionesAccion() {
		if (rondaActual > 4) {
			areaAcciones.setText("Fin del juego. Todas las rondas han finalizado.\n");
			return;
		}

		Equipos equipoActualObj = equiposAsignados[equipoActual];
		if (equipoActualObj == null) {
			areaAcciones.setText("Error: equipo no encontrado.\n");
			return;
		}

		String texto = "RONDA " + rondaActual + "\n";
		texto += "Turno del equipo: " + equipoActualObj.getNombre() + " (" + equipoActualObj.getEspecie() + ")\n";
		texto += "¿Qué acción quieres realizar?\n";
		texto += "1) Atacar\n";
		texto += "2) Defender\n";
		texto += "3) Saltar turno\n";

		areaAcciones.setText(texto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelClasificación(menu));
			marco.setVisible(true);
		}
	}
}
