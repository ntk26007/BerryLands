package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelPersonajes4  extends JFrame implements ActionListener{
	BotonPersonalizado volver;
	BotonPersonalizado siguiente;
	
	PanelPersonajes4() {
		setTitle("Reglas");
		//setBounds(1300,900, 1300, 750);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);
		
		Background fondo2 = new Background("Assets/page4.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);
		
		//volver
		BotonPersonalizado volver = new BotonPersonalizado("Assets/volver.png", "Assets/volver2.png");
		volver.setBounds(100,40,200,50);
		volver.addActionListener(e -> dispose());
		fondo2.add(volver);
		setVisible(true);
		
		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/siguiente.png", "Assets/siguiente2.png");
		siguiente.setBounds(1000, 40, 200, 50);
		siguiente.addActionListener(e -> new PanelPersonajes5());
		fondo2.add(siguiente);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this); 
			marco.remove(this); 
			marco.add(new PanelPersonajes3()); // regresamos al panel principal
			marco.setVisible(true);
		
		}
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPersonajes5()); //vamos a la segunda página de reglas
			marco.setVisible(true);

		}
	}
}
