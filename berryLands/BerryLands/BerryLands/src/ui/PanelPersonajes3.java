package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelPersonajes3 extends JFrame implements ActionListener {
	BotonPersonalizado volver;
	BotonPersonalizado siguiente;

	PanelPersonajes3() {
		setTitle("Reglas");
		// setBounds(1300,900, 1300, 750);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);

		Background fondo2 = new Background("Assets/page3.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);
		
		// posicion de los botones en conjunto
		JPanel posicion = new JPanel();
		posicion.setLayout(new GridLayout(1, 2, 10, 10)); 
	    posicion.setBounds(920, 115, 365, 125); //pc capgemini
		//posicion.setBounds(1150, 115, 365, 125);  // pc casa
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
		siguiente.addActionListener(e -> new PanelPersonajes4());
		posicion.add(siguiente);
		fondo2.add(posicion);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPersonajes2()); // regresamos al panel anterior
			marco.setVisible(true);

		}
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPersonajes4()); // pagina siguiente
			marco.setVisible(true);

		}
	}
}
