package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import stage1.Animal;
import stage1.Menu;
import stage1.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import baseDeDatos.DatabaseConnection;

public class PanelEquipos extends JFrame implements ActionListener {

	JButton siguiente;
	JButton[] asignarBotones = new JButton[5];
	JTextArea[] nombreEquipos = new JTextArea[5];
	JComboBox<String>[] especiesCajas = new JComboBox[5];
	private Menu menuLogic;
	
	PanelEquipos() {
		
		menuLogic = new Menu();
		setTitle("Ataque");
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setUndecorated(true);

		Background fondo3 = new Background("Assets/fondoEquipos.gif");
		setContentPane(fondo3);
		fondo3.setLayout(null);

		// se crean areas de texto, combo boxes y botones para los nombres de los 5 equipos
		String[] especies = { "Perro", "Gato", "Oso", "Mono", "Cabra", "Ave", "Conejo", "Lobo", "Pulpo", "Ardilla" };

		for (int i = 0; i < 5; i++) {
			// cajas de texto
			nombreEquipos[i] = crearAreaTexto();
			JScrollPane scrollPane = new JScrollPane(nombreEquipos[i]);
			scrollPane.setBackground(Color.PINK);
			scrollPane.setOpaque(true);
			// scrollPane.setBounds(150, 250 + (i * 98), 245, 50); //arriba, abajo, ancho,
			// alto y margen, capgemini
			scrollPane.setBounds(250, 280 + (i * 98), 245, 50); // pc casa
			fondo3.add(scrollPane);

			// cajas de escoger
			especiesCajas[i] = new JComboBox<>(especies);
			// especiesCajas[i].setBounds(678, 260 + (i * 100), 200, 30); // pc cap
			especiesCajas[i].setBounds(878, 270 + (i * 100), 200, 30); // pc casa
			fondo3.add(especiesCajas[i]);

			// botones asignar
			asignarBotones[i] = new JButton("Asignar");
			// asignarBotones[i].setBounds(878, 260 + (i * 100), 100, 30); // pc cap
			asignarBotones[i].setBounds(1078, 270 + (i * 100), 100, 30); // pc casa
			asignarBotones[i].setBorderPainted(isFocusable());
			asignarBotones[i].setBackground(Color.PINK);
			asignarBotones[i].addActionListener(this);
			asignarBotones[i].setActionCommand(String.valueOf(i));
			fondo3.add(asignarBotones[i]);
		}

		/*
		 * texto que indica el nº de equipo
		 */
		JLabel textoReglas = new JLabel(
				"<html><div style = 'text-align:center; font-size:15px;color:#b24271; '>" + "EQUIPO 1</div></html>");
		// textoReglas.setBounds(850, 175, 500, 200); // pc cap
		textoReglas.setBounds(1050, 185, 500, 200); // pc casa
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas);

		// texto 2
		JLabel textoReglas2 = new JLabel(
				"<html><div style = 'text-align:center; font-size:15px; color:#b24271;'>" + "EQUIPO 2</div></html>");
		// textoReglas2.setBounds(850, 275, 500, 200); // pc cap
		textoReglas2.setBounds(1050, 285, 500, 200); // pc casa
		textoReglas2.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas2);

		// texto 3
		JLabel textoReglas3 = new JLabel(
				"<html><div style = 'text-align:center; font-size:15px; color:#b24271;'>" + "EQUIPO 3</div></html>");
		// textoReglas3.setBounds(850, 375, 500, 200); // pc cap
		textoReglas3.setBounds(1050, 385, 500, 200); // pc casa
		textoReglas3.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas3);

		// texto 4
		JLabel textoReglas4 = new JLabel(
				"<html><div style = 'text-align:center; font-size:15px; color:#b24271;'>" + "EQUIPO 4</div></html>");
		// textoReglas4.setBounds(850, 475, 500, 200); // pc cap
		textoReglas4.setBounds(1050, 485, 500, 200); // pc casa
		textoReglas4.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas4);

		// texto 5
		JLabel textoReglas5 = new JLabel(
				"<html><div style = 'text-align:center; font-size:15px; color:#b24271;'>" + "EQUIPO 5</div></html>");
		// textoReglas5.setBounds(850, 575, 500, 200); // pc cap
		textoReglas5.setBounds(1050, 585, 500, 200); // pc casa
		textoReglas5.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas5);

		// Botón siguiente
		siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
		siguiente.setBounds(1200, 40, 200, 120); // pc casa
		siguiente.addActionListener(e ->  {
		    if (menuLogic.todosLosEquiposRegistrados()) {
		        Equipos[] equiposAsignados = menuLogic.getEquipos();
		        // obtener los equipos creados
		        new PanelAtaque(equiposAsignados); //se le pasa como param los equipos de este panel al siguiente
		    } else {
		        JOptionPane.showMessageDialog(this,
		                "Debes asignar los 5 equipos antes de continuar.",
		                "Atención", JOptionPane.WARNING_MESSAGE);
		    }
		});
		fondo3.add(siguiente);

		setVisible(true);
	}

	// metodo que especifica los detalles del area de texto
	private JTextArea crearAreaTexto() {
		JTextArea areaTexto = new JTextArea();
		Border border = new RoundBorder(Color.GRAY, 10);
		areaTexto.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		return areaTexto;
	}

//    @Override
//    public void actionPerformed(ActionEvent e) {
//    	int i = Integer.parseInt(e.getActionCommand());
//    	String especie = (String) especiesCajas[i].getSelectedItem();
//    	
//       // guardarEquipoEnBaseDeDatos(equipo, especie); //cuando pulsamos asignar, se guardan los datos ingresados en la base d datos
//  
//    }

	/**
	 * Al pulsar alguno de los botones "Asignar", se extrae el nombre y la especie
	 * seleccionada del equipo correspondiente, se asignan al modelo a través de los
	 * setters de Equipo, y se invocan los métodos de la lógica (registrarEquipos y
	 * elegirEspecie).
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = Integer.parseInt(e.getActionCommand());

		String nombre = nombreEquipos[i].getText().trim();
		String especie = (String) especiesCajas[i].getSelectedItem();

		// Llamamos al método que se encarga de validar y guardar correctamente
		menuLogic.asignarEquipo(i, nombre, especie);

		// Verificamos si todos los equipos ya fueron registrados para pasar a la
		// siguiente fase
		if (menuLogic.todosLosEquiposRegistrados()) {
			// Puedes aquí pasar a la siguiente pantalla, guardar en base de datos o
			// preparar el juego
			// new PanelAtaque(); // si quieres ir automáticamente al panel de ataque
		}
	}

//	public void siguientePulsado(ActionEvent e) {
//		JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
//		marco.remove(this);
//		marco.add(new PanelAtaque(null));
//		marco.setVisible(true);
//	}

	// metodo que almacena los datos de la interfaz a la base
	private void guardarEquipoEnBaseDeDatos(String equipo, String especie) {
		if (equipo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre del equipo no puede estar vacío.");
			return; // No guarda equipos vacíos
		}
		try (Connection conexion = DatabaseConnection.conectar()) {
			String sql = "INSERT INTO equipos (nombre, especie) VALUES (?, ?)";
			try (PreparedStatement statement = conexion.prepareStatement(sql)) {
				statement.setString(1, equipo);
				statement.setString(2, especie);
				statement.executeUpdate();
				JOptionPane.showMessageDialog(this, "Equipo asignado correctamente.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al asignar el equipo.");
		}
	}

	// Clase para crear bordes curvos a las areas de texto
	class RoundBorder implements Border {
		private Color color;
		private int radius;

		public RoundBorder(Color color, int radius) {
			this.color = color;
			this.radius = radius;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.setColor(color);
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(5, 5, 5, 5);
		}

		@Override
		public boolean isBorderOpaque() {
			return true;
		}
	}

	public int getVida() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setVida(int i) {
		// TODO Auto-generated method stub

	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

}
