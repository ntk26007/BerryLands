package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import stage1.Animal;
import stage1.Menu;
import stage1.Main;
import javax.swing.*;

public class PanelClasificación extends JFrame implements ActionListener {
	
	JButton siguiente;
	JTextArea puntuaje;
	Menu menu;
	
	public PanelClasificación(Menu menu) {
		this.menu = menu;

		setTitle("Clasificación");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		Background fondo3 = new Background("Assets/fondoClasificacion.gif");
		setContentPane(fondo3);
		fondo3.setLayout(null);

		// Área de opciones de acción
        puntuaje = new JTextArea();
        puntuaje.setEditable(false);
        puntuaje.setLineWrap(true);
        JScrollPane scrollAcciones = new JScrollPane(puntuaje);
        scrollAcciones.setBounds(370, 390, 700, 330);
        fondo3.add(scrollAcciones);
		
		// siguiente
		siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		siguiente.setBounds(1250, 10, 200, 150); // pc casa
		siguiente.addActionListener(e -> new Marco());
		fondo3.add(siguiente);
		
		// Mostrar resultados automáticamente
		menu.mostrarResultadosFinales(puntuaje);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new Marco());
			marco.setVisible(true);
		}
	}
}
