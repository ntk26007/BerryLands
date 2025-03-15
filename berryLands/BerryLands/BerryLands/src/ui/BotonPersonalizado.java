package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;

public class BotonPersonalizado extends JButton{
	
	private ImageIcon originalIcon;
	private ImageIcon hoverIcon;
	
	BotonPersonalizado(String defaultImage, String hoverImage){
		originalIcon= new ImageIcon(defaultImage);
		hoverIcon = new ImageIcon(hoverImage);
		
		setIcon(originalIcon);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
		

		addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) { //despues de pasar el raton x encima, este volverá a su color original
				setIcon(originalIcon);
			}
			public void mouseClicked(MouseEvent e) {
				//marco.irReglas();
			}
			public void mouseEntered(MouseEvent e) { //cuando pongo el raton encima
				setIcon(hoverIcon);
			}
			public void mousePressed(MouseEvent e) {
				//
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		if(getModel().isArmed()) {
			g.setColor(new Color(255,255,255,100)); //transparencia
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
		}
		super.paintComponent(g);
	}
	
	protected void paintBorder(Graphics g) {
		
	}
}
