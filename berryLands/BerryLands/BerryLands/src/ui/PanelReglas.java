package ui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelReglas extends JFrame implements ActionListener {

	BotonPersonalizado volver;
	BotonPersonalizado siguiente;

	PanelReglas() {
		setTitle("Reglas");
		// setBounds(1300,900, 1300, 750);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);

		Background fondo2 = new Background("Assets/fondoReglas1.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);

		// posicion de los botones en conjunto
		JPanel posicion = new JPanel();
		posicion.setLayout(new GridLayout(1, 2, 30, 30));
		posicion.setBounds(120, 15 , 365, 125);
		posicion.setOpaque(false);
		add(posicion);

		/*
		 * volver
		 */
		BotonPersonalizado volver = new BotonPersonalizado("Assets/flecha22.png", "Assets/flechaRosa1.png");
		// volver.setBounds(1150, 40, 200, 50); pc casa
		// volver.setBounds(100, 40, 400, 120);
		volver.addActionListener(e -> dispose());
		posicion.add(volver);
		// setVisible(true);

		/*
		 * siguiente
		 */
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		// siguiente.setBounds(900, 40, 400, 120);
		siguiente.addActionListener(e -> new PanelReglas2());
		posicion.add(siguiente);
		fondo2.add(posicion);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new Marco()); // regresamos al panel principal
			marco.setVisible(true);

		}
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelReglas2()); // vamos a la segunda página de reglas
			marco.setVisible(true);

		}
	}
}
