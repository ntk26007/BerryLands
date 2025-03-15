package ui;

import java.awt.*;
import javax.swing.*;

public class Background extends JPanel {
	private Image imagenFondo;
	
	Background(String imagen){
		loadImage(imagen);
	}

	private void loadImage(String imagen) {
		ImageIcon imageicon = new ImageIcon(imagen);
		imagenFondo = imageicon.getImage();
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(imagenFondo != null) {
			Graphics2D g2d = (Graphics2D) g;
			int panelAncho = getWidth();
			int panelAlto = getHeight();
			int imagenAncho = imagenFondo.getWidth(this);
			int imagenAlto = imagenFondo.getHeight(this);
			
			//escala
			double scaleX = (double) panelAncho / imagenAncho;
			double scaleY = (double) panelAlto / imagenAlto;
			double scale = Math.max(scaleX, scaleY);
			
			int nuevoAncho = (int) (imagenAncho * scale);
			int nuevoAlto = (int) (imagenAlto * scale);
			
			//centrar imagen
			int x = (panelAncho - nuevoAncho) / 2;
			int y = (panelAlto - nuevoAlto) / 2;
			
			g2d.drawImage(imagenFondo, x, y, nuevoAncho, nuevoAlto,this);
		}
	}
}
