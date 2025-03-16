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

		Background fondo3 = new Background("Assets/fondo.jpg");
		setContentPane(fondo3);
		fondo3.setLayout(null);

		JLabel textoReglas = new JLabel("<html><div style = 'text-align:center; font-size:16px;'>" + " PERSONAJES <br>"
				+ "HOLA<br>" + "pero.</div></html>");
		textoReglas.setBounds(400, 200, 500, 200);
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas);

		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/siguiente.png", "Assets/siguiente2.png");
		siguiente.setBounds(1150, 40, 200, 50);
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
