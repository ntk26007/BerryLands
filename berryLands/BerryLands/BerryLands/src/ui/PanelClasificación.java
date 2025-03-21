package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import stage1.Animal;
import stage1.Menu;
import stage1.Main;
import javax.swing.*;

public class PanelClasificación extends JFrame implements ActionListener {
	
	JButton siguiente;
	
	PanelClasificación() {
		setTitle("Clasificación");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		Background fondo3 = new Background("Assets/fondoClasificacion.gif");
		setContentPane(fondo3);
		fondo3.setLayout(null);

		JLabel textoReglas = new JLabel("<html><div style = 'text-align:center; font-size:16px;'>" + " CLASIFICACION <br>"
				+ "2. Hay 5 equipos con una especie cada uno.<br>" + "3. El equipo con más vida gana.</div></html>");
		textoReglas.setBounds(400, 400, 500, 200);
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas);
		
		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		siguiente.setBounds(1050, 10, 200, 150);
		siguiente.addActionListener(e -> new Marco());
		fondo3.add(siguiente);
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
