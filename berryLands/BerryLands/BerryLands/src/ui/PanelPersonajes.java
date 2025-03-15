package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class PanelPersonajes extends JFrame implements ActionListener {
	JButton volver;

	PanelPersonajes() {
		setTitle("Reglas");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);

		Background fondo2 = new Background("Assets/fondo.jpg");
		setContentPane(fondo2);
		fondo2.setLayout(null);

		JLabel textoReglas = new JLabel("<html><div style = 'text-align:center; font-size:16px;'>"
				+ " PERSONAJES <br>" + "2. Hay 5 equipos con una especie cada uno.<br>"
				+ "3. El equipo con más vida gana.</div></html>");
		textoReglas.setBounds(400, 200, 500, 200);
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo2.add(textoReglas);

		// volver
		BotonPersonalizado volver = new BotonPersonalizado("Assets/volver.png", "Assets/volver2.png");
		volver.setBounds(1050, 40, 200, 50);
		volver.addActionListener(e -> dispose());
		fondo2.add(volver);
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
	}
}
