package ui;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class PanelReglas2 extends JFrame implements ActionListener{

	 BotonPersonalizado volver;

	    PanelReglas2() {
	        setTitle("Reglas del juego");
	        setLocationRelativeTo(null);
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	        setResizable(false);
	        setUndecorated(true);

	        // fondo
	        Background fondo4 = new Background("Assets/fondoReglas2.gif");
	        setContentPane(fondo4);
	        fondo4.setLayout(null);
	        
	        //posicion boton
	        JPanel posicion = new JPanel();
			posicion.setLayout(new GridLayout(1, 2, 10, 10));
			posicion.setBounds(20, 660, 365, 125); //pc capgmini
			//posicion.setBounds(30, 710, 365, 125); //pc casa
			posicion.setOpaque(false);
			add(posicion);

			/*
			 * crear volver
			 */
			BotonPersonalizado volver = new BotonPersonalizado("Assets/flecha22.png", "Assets/flechaRosa1.png");
			volver.addActionListener(e -> dispose());
			posicion.add(volver);

			setVisible(true);
			
			/*
			 * Para insertar las 4 imagenes con sus 4 textos se podria optimizar refactorizando o herencia pero de momento asi funciona
			 * Aqui abajo se crean las 4 imagenes para los JLabel
			 * (para que no queden cortes en la imagen y se pueda modificar el tamaño proporcional)
			 */
			
			 // imagen pala
			 ImageIcon pala = new ImageIcon("Assets/pala.png");
		        Image logoImage = pala.getImage();

		        int originalWidth = logoImage.getWidth(null); // Obtener las dimensiones originales de la imagen
		        int originalHeight = logoImage.getHeight(null);

		        int desiredWidth = 170; // Establecer el nuevo tamaño deseado (más pequeño)
		        int desiredHeight = -1; // Si no se cambia el alto, se deja -1

		        if (desiredWidth > 0 && desiredHeight == -1) { // Calcular el nuevo tamaño manteniendo la proporción
		            desiredHeight = (originalHeight * desiredWidth) / originalWidth;
		        } else if (desiredHeight > 0 && desiredWidth == -1) {
		            desiredWidth = (originalWidth * desiredHeight) / originalHeight;
		        } else if (desiredWidth > 0 && desiredHeight > 0) {
		            // Si ambos valores son proporcionados, se usa el tamaño especificado y no se mantiene la proporción
		        }

		        Image logoScaled = logoImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
		        JLabel logo = new JLabel(new ImageIcon(logoScaled));

		        logo.setBounds(0, 250, desiredWidth, desiredHeight); // Ajusta la posición y tamaño del JLabel (pc capgemini)
		        //logo.setBounds(70, 270, desiredWidth, desiredHeight); // pc casa
		        fondo4.add(logo);
		        
		        

		        //imagen hacha
		        ImageIcon hacha = new ImageIcon("Assets/hacha.png");
		        Image imagen2 = hacha.getImage();

		        int ancho2 = imagen2.getWidth(null); 
		        int alto2 = imagen2.getHeight(null);

		        int anchoFinal2 = 170; 
		        int altoFinal2 = -1; 

		        if (anchoFinal2 > 0 && altoFinal2 == -1) { 
		            altoFinal2 = (alto2 * anchoFinal2) / ancho2;
		        } else if (altoFinal2 > 0 && anchoFinal2 == -1) {
		            anchoFinal2 = (ancho2 * altoFinal2) / alto2;
		        } else if (anchoFinal2 > 0 && altoFinal2 > 0) {
		            
		        }

		        Image imagenFinal2 = imagen2.getScaledInstance(anchoFinal2, altoFinal2, Image.SCALE_SMOOTH);
		        JLabel logo2 = new JLabel(new ImageIcon(imagenFinal2));

		        logo2.setBounds(670, 250, anchoFinal2, altoFinal2); //pc capgemini
		        //logo2.setBounds(800, 270, desiredWidth, desiredHeight); // pc casa
		        fondo4.add(logo2);
		        
		        
		        
		        //imagen red
		        ImageIcon red = new ImageIcon("Assets/red.png");
		        Image imagen3 = red.getImage();

		        int ancho3 = imagen3.getWidth(null); 
		        int alto3 = imagen3.getHeight(null);

		        int anchoFinal3 = 160; 
		        int altoFinal3 = -1; 

		        if (anchoFinal3 > 0 && altoFinal3 == -1) { 
		            altoFinal3 = (alto3 * anchoFinal3) / ancho3;
		        } else if (altoFinal3 > 0 && anchoFinal3 == -1) {
		            anchoFinal3 = (ancho3 * altoFinal3) / alto3;
		        } else if (anchoFinal3 > 0 && altoFinal3 > 0) {
		            
		        }

		        Image imagenFinal3 = imagen3.getScaledInstance(anchoFinal3, altoFinal3, Image.SCALE_SMOOTH);
		        JLabel logo3 = new JLabel(new ImageIcon(imagenFinal3));

		        logo3.setBounds(0, 470, anchoFinal3, altoFinal3); //pc capgemini
		        //logo3.setBounds(80, 510, desiredWidth, desiredHeight); //pc casa
		        fondo4.add(logo3);
		        
		        
		        
		      //imagen tirachinas
		        ImageIcon tirachinas = new ImageIcon("Assets/tirachinas.png");
		        Image imagen4 = tirachinas.getImage();

		        int ancho4 = imagen4.getWidth(null); 
		        int alto4 = imagen4.getHeight(null);

		        int anchoFinal4 = 170; 
		        int altoFinal4 = -1; 

		        if (anchoFinal4 > 0 && altoFinal4 == -1) { 
		            altoFinal4 = (alto4 * anchoFinal4) / ancho4;
		        } else if (altoFinal4 > 0 && anchoFinal4 == -1) {
		            anchoFinal4 = (ancho4 * altoFinal4) / alto4;
		        } else if (anchoFinal4 > 0 && altoFinal4 > 0) {
		            
		        }

		        Image imagenFinal4 = imagen4.getScaledInstance(anchoFinal4, altoFinal4, Image.SCALE_SMOOTH);
		        JLabel logo4 = new JLabel(new ImageIcon(imagenFinal4));

		        logo4.setBounds(670, 485, anchoFinal4, altoFinal4); //pc capgemini
		        //logo4.setBounds(800, 520, desiredWidth, desiredHeight); // pc casa
		        fondo4.add(logo4);
		        
		        
		    /*
		     * Etiquetas de cada una de las imagenes  
		     * Para el texto se puede usar HTML  
		     */
		        
		    //etiqueta1   arriba izq 
	        JLabel etiqueta = new JLabel("<html><div style = 'text-align:center; font-size:12px;'> · Pala: esta herramienta te permite defenderte de los ataques cavando trampas para los enemigos. <br>"
	        		+ "Proporciona +10 puntos de defensa al usarla </div></html>");
	        etiqueta.setOpaque(true);
	        etiqueta.setFont(new Font("Verdana", Font.PLAIN, 14));
	        etiqueta.setBackground(Color.PINK);
	        etiqueta.setForeground(Color.BLACK);
	        etiqueta.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
	        etiqueta.setVerticalAlignment(SwingConstants.CENTER); // Centrar verticalmente
	        etiqueta.setVisible(false); // Inicialmente oculta
	        etiqueta.setBounds(230, 268, 350, 130); //pc capgemini
	        //etiqueta.setBounds(330, 290, 350, 130);  //  pc casa
	        fondo4.add(etiqueta);
	        
	      //etiqueta2    arriba derecha
	        JLabel etiqueta2 = new JLabel("<html><div style = 'text-align:center; font-size:12px;'> · Hacha: esta herramienta te permite defenderte talando árboles y usarlos como cobertura. <br>"
	        		+ "Proporciona +15 puntos de defensa al usarlo </div></html>");
	        etiqueta2.setOpaque(true);
	        etiqueta2.setFont(new Font("Verdana", Font.PLAIN, 14));
	        etiqueta2.setBackground(Color.PINK);
	        etiqueta2.setForeground(Color.BLACK);
	        etiqueta2.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
	        etiqueta2.setVerticalAlignment(SwingConstants.CENTER); // Centrar verticalmente
	        etiqueta2.setVisible(false); // Inicialmente oculta
	        etiqueta2.setBounds(910, 268, 350, 130); // pc capgemini
	        //etiqueta2.setBounds(1070, 290, 350, 130);  //  pc casa
	        fondo4.add(etiqueta2);
	        
	      //etiqueta3   abajo izq 
	        JLabel etiqueta3 = new JLabel("<html><div style = 'text-align:center; font-size:12px;'> · Red: esta herramienta te permite defenderte cazando insectos venenosos para repeler cualquier ataque. <br>"
	        		+ "Proporciona +20 puntos de defensa al usarla </div></html>");
	        etiqueta3.setOpaque(true);
	        etiqueta3.setFont(new Font("Verdana", Font.PLAIN, 14));
	        etiqueta3.setBackground(Color.PINK);
	        etiqueta3.setForeground(Color.BLACK);
	        etiqueta3.setHorizontalAlignment(SwingConstants.CENTER); 
	        etiqueta3.setVerticalAlignment(SwingConstants.CENTER); 
	        etiqueta3.setVisible(false); // Inicialmente oculta
	        etiqueta3.setBounds(227, 505, 350, 130); // pc capgemini
	        //etiqueta3.setBounds(330, 550, 350, 130);  //  pc casa 
	        fondo4.add(etiqueta3);
	        
	        
	      //etiqueta4    abajo derecha
	        JLabel etiqueta4 = new JLabel("<html><div style = 'text-align:center; font-size:12px;'> · Tirachinas: esta herramienta te permite defenderte disparando bombas a la cobertura del enemigo. <br>"
	        		+ "Proporciona +25 puntos de defensa al usarlo </div></html>");
	        etiqueta4.setOpaque(true);
	        etiqueta4.setFont(new Font("Verdana", Font.PLAIN, 14));
	        etiqueta4.setBackground(Color.PINK);
	        etiqueta4.setForeground(Color.BLACK);
	        etiqueta4.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
	        etiqueta4.setVerticalAlignment(SwingConstants.CENTER); // Centrar verticalmente
	        etiqueta4.setVisible(false); // Inicialmente oculta
	        etiqueta4.setBounds(908, 502, 350, 130); //pc capgemini
	        //etiqueta4.setBounds(1075, 550, 350, 130);  //  pc casa
	        fondo4.add(etiqueta4);

	        
	        /*
	         * Funcionalidad de aparicion de la etiqueta al pasar sobre la imagen especifica
	         */
	        logo.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                etiqueta.setVisible(true); // Mostrar etiqueta cuando el ratón pasa por encima
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                etiqueta.setVisible(false); // Ocultar etiqueta cuando el ratón se aleja
	            }
	        });

	        setVisible(true);
	        
	        logo2.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                etiqueta2.setVisible(true);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                etiqueta2.setVisible(false);
	            }
	        });

	        setVisible(true);
	        
	        logo3.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                etiqueta3.setVisible(true);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                etiqueta3.setVisible(false);
	            }
	        });

	        setVisible(true);
	        
	        logo4.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                etiqueta4.setVisible(true);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                etiqueta4.setVisible(false);
	            }
	        });

	        setVisible(true);
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == volver) {
	            JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
	            marco.remove(this);
	            marco.add(new PanelReglas()); 
	            marco.setVisible(true);
	        }
	    }


}
