package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelEquipos extends JFrame implements ActionListener {
	JButton volver;
	BotonPersonalizado siguiente;

	PanelEquipos() {
		setTitle("Equipos");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);

		Background fondo2 = new Background("Assets/fondo.jpg");
		setContentPane(fondo2);
		fondo2.setLayout(null);

		
		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/siguiente.png", "Assets/siguiente2.png");
		siguiente.setBounds(1000, 40, 200, 50);
		siguiente.addActionListener(e -> new PanelAtaque());
		fondo2.add(siguiente);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelAtaque()); //vamos a la segunda página 
			marco.setVisible(true);

		}
	}
}
