package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PanelPersonajes extends JFrame implements ActionListener {
	
	/**
	 * Dispose hace que al clicar al boton, nos lleve justo a la pagina anterior o inicial. 
	 * En el caso de personajes dispose nos lleva a la anterior, si ponemos new PanelPersonajeX donde el boton volver, nos haria un bucle
	 * 
	 */
	JButton volver;
	BotonPersonalizado siguiente;

	PanelPersonajes() {
		setTitle("Reglas");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false); // no se puede modificar la ventana
		setUndecorated(true);

		Background fondo2 = new Background("Assets/page1.gif");
		setContentPane(fondo2);
		fondo2.setLayout(null);

		// volver
		BotonPersonalizado volver = new BotonPersonalizado("Assets/volver.png", "Assets/volver2.png");
		//volver.setBounds(1150, 40, 200, 50); pc casa
		volver.setBounds(100, 40, 200, 50);
		volver.addActionListener(e -> dispose());
		fondo2.add(volver);
		setVisible(true);
		
		// siguiente
		BotonPersonalizado siguiente = new BotonPersonalizado("Assets/siguiente.png", "Assets/siguiente2.png");
		siguiente.setBounds(1000, 40, 200, 50);
		siguiente.addActionListener(e -> new PanelPersonajes2());
		fondo2.add(siguiente);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == volver) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new Marco()); // regresamos al panel principal
			marco.setVisible(true);

		}
		if (e.getSource() == siguiente) {
			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
			marco.remove(this);
			marco.add(new PanelPersonajes2()); //vamos a la segunda página 
			marco.setVisible(true);

		}
	}
}
