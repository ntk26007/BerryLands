package ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelReglas extends JFrame implements ActionListener{
	
	BotonPersonalizado volver;
	BotonPersonalizado siguiente;
	
	PanelReglas() {
		setTitle("Reglas");
		setBounds(1300,900, 1300, 750);
		setLocationRelativeTo(null);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);
		
		Background fondo2 = new Background("Assets/fondoReglas1.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);
		
		//volver
		BotonPersonalizado volver = new BotonPersonalizado("Assets/volver.png", "Assets/volver2.png");
		volver.setBounds(130,40,200,50);
		volver.addActionListener(e -> dispose());
		fondo2.add(volver);
		setVisible(true);
		
		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/siguiente.png", "Assets/siguiente2.png");
		siguiente.setBounds(330, 40, 200, 50);
		siguiente.addActionListener(e -> new PanelReglas2());
		fondo2.add(siguiente);
		setVisible(true);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this); 
			marco.remove(this); 
			marco.add(new Marco()); // regresamos al panel principal
			marco.setVisible(true);
		
		}
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelReglas2()); //vamos a la segunda página de reglas
			marco.setVisible(true);

		}
	}
}
