package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import stage1.Animal;
import stage1.Menu;
import stage1.Main;

import javax.swing.*;

public class PanelEquipos extends JFrame implements ActionListener {
	
	/**
	 * JComboBox para cuando el usuario tenga que elejir uno de los 10 animales
	 */
	
	JButton siguiente;
	private JTextArea textArea;
	
	PanelEquipos() {
		setTitle("Equipos");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		//fondo
		Background fondo3 = new Background("Assets/fondo.jpg");
		setContentPane(fondo3);
		fondo3.setLayout(null);
		
//		//area de texto con sroll
//		textArea = new JTextArea();
//		textArea.setEditable(false);
//		textArea.setFont(new Font("Monospaced", Font.PLAIN,14));
//		
//		JScrollPane scrollPane = new JScrollPane(textArea);
//		add(scrollPane);
//		setVisible(true);

		JLabel textoReglas = new JLabel("<html><div style = 'text-align:center; font-size:16px;'>" + 
				"1. Cada jugador tiene 100 bayas.<br>" + "2. Hay 5 equipos con una especie cada uno.<br>" + 
				"3. El equipo con más vida gana.</div></html>");
		textoReglas.setBounds(400, 200, 500, 200);
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas);
	
		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/siguiente.png", "Assets/siguiente2.png");
		siguiente.setBounds(1050, 40, 200, 50);
		siguiente.addActionListener(e -> new PanelAtaque());
		fondo3.add(siguiente);
		setVisible(true);

	}
	
	public void agregarTexto(String texto) {
		textArea.append(texto + "\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	// Saltar al siguiente panel
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelAtaque()); 
			marco.setVisible(true);

		}
	}
}


