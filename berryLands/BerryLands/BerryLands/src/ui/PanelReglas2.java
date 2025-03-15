package ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelReglas2 extends JFrame implements ActionListener{

	BotonPersonalizado volver;
	
	PanelReglas2(){
		setTitle("Equipos");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		//fondo
		Background fondo4 = new Background("Assets/fondo.jpg");
		setContentPane(fondo4);
		fondo4.setLayout(null);
		
		JLabel textoReglas = new JLabel("<html><div style = 'text-align:center; font-size:16px;'>" + 
				"1. Cada jugador tiene 100 bayas.<br>" + "2. Hay 5 equipos con una especie cada uno.<br>" + 
				"3. El equipo con más vida gana.</div></html>");
		textoReglas.setBounds(400, 200, 500, 200);
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo4.add(textoReglas);
		
		//volver
		BotonPersonalizado volver = new BotonPersonalizado("Assets/volver.png", "Assets/volver2.png");
		volver.setBounds(200,40,200,50);
		volver.addActionListener(e -> dispose());
		fondo4.add(volver);
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
		
	}

}
