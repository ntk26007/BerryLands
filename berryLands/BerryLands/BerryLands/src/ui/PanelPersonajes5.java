package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelPersonajes5 extends JFrame implements ActionListener{
	BotonPersonalizado volver;
	
	PanelPersonajes5() {
		setTitle("Reglas");
		//setBounds(1300,900, 1300, 750);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);
		
		Background fondo2 = new Background("Assets/page5.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);
		
		//volver
		BotonPersonalizado volver = new BotonPersonalizado("Assets/volver.png", "Assets/volver2.png");
		volver.setBounds(100,40,200,50);
		volver.addActionListener(e -> dispose());
		fondo2.add(volver);
		setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame)SwingUtilities.getWindowAncestor(this); 
			marco.remove(this); 
			marco.add(new PanelPersonajes4()); // regresamos al panel principal
			marco.setVisible(true);
		
		}
	}
}
