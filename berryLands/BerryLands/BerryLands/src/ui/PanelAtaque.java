package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import stage1.Menu;


public class PanelAtaque extends JFrame implements ActionListener {

    private JButton siguiente;
    private JTextArea areaRonda;
    private JTextArea areaAcciones;
    private final Menu menu;
    private int equipoActual = 0;
    private int rondaActual = 1;
    private Equipos[] equiposAsignados;

    public PanelAtaque(Equipos[] equiposAsignados) {
        this.equiposAsignados = equiposAsignados;
        this.menu = new Menu();

        setTitle("Ataque");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);

        Background fondo3 = new Background("Assets/fondoAtaque.png");
        setContentPane(fondo3);
        fondo3.setLayout(null);

        //siguiente
        siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
        siguiente.setBounds(1250, 10, 200, 150);
        siguiente.addActionListener(e -> new PanelClasificación());
        fondo3.add(siguiente);

        // Área de texto que muestra ronda y turnos
        areaRonda = new JTextArea();
        areaRonda.setEditable(false);
        areaRonda.setLineWrap(true);
        JScrollPane scrollRonda = new JScrollPane(areaRonda);
        scrollRonda.setBounds(170, 100, 500, 150);
        fondo3.add(scrollRonda);

        // Área de opciones de acción
        areaAcciones = new JTextArea();
        areaAcciones.setEditable(false);
        areaAcciones.setLineWrap(true);
        JScrollPane scrollAcciones = new JScrollPane(areaAcciones);
        scrollAcciones.setBounds(170, 580, 500, 150);
        fondo3.add(scrollAcciones);

        
        // Botones de acción
        JButton atacar = new JButton("Atacar");
        atacar.setBounds(1080, 580, 150, 50);
        fondo3.add(atacar);

        JButton defender = new JButton("Defender");
        defender.setBounds(1080, 640, 150, 50);
        fondo3.add(defender);

        JButton saltarTurno = new JButton("Saltar Turno");
        saltarTurno.setBounds(1080, 700, 150, 50);
        fondo3.add(saltarTurno);

        /*
         * Acciones de los botones
         */
        atacar.addActionListener(e -> {
            if (equiposAsignados[equipoActual] != null) {
                menu.atacar(equipoActual, areaAcciones);
                
                // Si terminó el ataque, avanzar turno
                if (!menu.estaEnAtaque() && menu.getTurnosCompletados()[equipoActual]) {
                    avanzarTurno();
                    mostrarOpcionesAccion();
                }
            }
        });
        
        defender.addActionListener(e -> {
            if (equiposAsignados[equipoActual] != null) {
            	avanzarTurno();
            	mostrarOpcionesAccion();
            }
        });

        saltarTurno.addActionListener(e -> {
            if (equiposAsignados[equipoActual] != null) {
                menu.saltarTurno(equipoActual);
                avanzarTurno();
                mostrarOpcionesAccion();
            }
        });

        mostrarEstadoJuego();
        mostrarOpcionesAccion();
   
        setVisible(true);
    }

    
    /**
     * Metodos aparte
     */

    private void avanzarTurno() {
        equipoActual++;
        if (equipoActual >= 5) {
            equipoActual = 0;
            rondaActual++;
        }
        if (rondaActual > 4) {
            areaRonda.setText("\u00a1Se ha terminado el juego! Pulsa la flecha superior para ver los resultados.");
        } else {
            mostrarEstadoJuego();
        }
    }

    private void mostrarEstadoJuego() {
        String texto = "Ronda " + rondaActual + " de 4\n";
        texto += "Turno del equipo " + (equipoActual + 1) + ": ";

        if (equiposAsignados[equipoActual] != null) {
            texto += equiposAsignados[equipoActual].getNombre() + " (" + equiposAsignados[equipoActual].getEspecie() + ")";
        } else {
            texto += "No asignado";
        }
        areaRonda.setText(texto);
    }

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
        texto += "\u00bfQué acción quieres realizar?\n";
        texto += "1) Atacar\n";
        texto += "2) Defender\n";
        texto += "3) Saltar turno\n";

        areaAcciones.setText(texto);
    }

    //no es necesario pero hay q indicar el metodo abstracto
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == siguiente) {
            JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
            marco.remove(this);
            marco.add(new PanelClasificación());
            marco.setVisible(true);
        }
    }
}
