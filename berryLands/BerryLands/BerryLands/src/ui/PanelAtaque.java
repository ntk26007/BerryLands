package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import stage1.Animal;
import stage1.Menu;
import stage1.Main;
import javax.swing.*;

public class PanelAtaque extends JFrame implements ActionListener {

	JButton siguiente;

	PanelAtaque() {
		setTitle("Ataque");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		Background fondo3 = new Background("Assets/fondoAtaque.png"); // subir calidad a la imagen
		setContentPane(fondo3);
		fondo3.setLayout(null);

		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		siguiente.setBounds(1050, 10, 200, 150);
		siguiente.addActionListener(e -> new PanelClasificación());
		fondo3.add(siguiente);
		setVisible(true);
	}

	// Saltar al siguiente panel
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
