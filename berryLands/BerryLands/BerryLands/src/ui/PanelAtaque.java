package ui;

import java.awt.Color;
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

		Background fondo3 = new Background("Assets/fondoAtaque.png"); // subir calidad a la imagen
		setContentPane(fondo3);
		fondo3.setLayout(null);

		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		siguiente.setBounds(1250, 10, 200, 150); // pc casa
		siguiente.addActionListener(e -> new PanelClasificación());
		fondo3.add(siguiente);
		setVisible(true);
		
		//area de texto para mostrar las opciones
		 JTextArea areaTexto = new JTextArea();
		 areaTexto.setLineWrap(true); // Ajustar el texto a la línea
		 areaTexto.setWrapStyleWord(true); // Ajustar el texto por palabras
		 JScrollPane scrollPane = new JScrollPane(areaTexto);
         scrollPane.setBackground(Color.PINK);
         scrollPane.setBounds(250, 280, 245, 50);
         scrollPane.setOpaque(true);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Mostrar siempre la barra de desplazamiento vertical
         fondo3.add(scrollPane);
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
