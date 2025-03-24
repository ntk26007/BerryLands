package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelPersonajes2 extends JFrame implements ActionListener {
	BotonPersonalizado volver;
	BotonPersonalizado siguiente;

	PanelPersonajes2() {
		setTitle("Reglas");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);

		Background fondo2 = new Background("Assets/page2.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);

		// posicion de los botones en conjunto
		JPanel posicion = new JPanel();
		posicion.setLayout(new GridLayout(1, 2, 10, 10));
	    //posicion.setBounds(920, 115, 365, 125);  // pc capgmini
		posicion.setBounds(1150, 115, 365, 125);  // pc casa
		posicion.setOpaque(false);
		add(posicion);

		/*
		 * volver
		 */
		BotonPersonalizado volver = new BotonPersonalizado("Assets/flecha22.png", "Assets/flechaRosa1.png");
		volver.addActionListener(e -> dispose());
		posicion.add(volver);

		/*
		 * siguiente
		 */
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		// siguiente.setBounds(900, 40, 400, 120);
		siguiente.addActionListener(e -> new PanelPersonajes3());
		posicion.add(siguiente);
		fondo2.add(posicion);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPersonajes()); // regresamos al panel anterior
			marco.setVisible(true);

		}
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPersonajes3()); // sig pagina
			marco.setVisible(true);

		}
	}
}
