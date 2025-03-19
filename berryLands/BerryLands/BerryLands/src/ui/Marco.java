package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import baseDeDatos.DatabaseConnection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import stage1.Animal;
import stage1.Menu;
import stage1.Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Marco extends JFrame implements ActionListener {

	/**
	 * JMenuBar para añadir una pestaña que diga informacion y nos muestre contacto y tal y la reseña
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Marco::new);
	}

	BotonPersonalizado jugar;
	BotonPersonalizado reglas;
	BotonPersonalizado personajes;
	BotonPersonalizado guardar;
	BotonPersonalizado salir;
	
	private JTextField campoTexto;
	private JTextArea areaResultados;
	
	public Marco() {
		setTitle("BerryLands");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// fondo
		Background fondo = new Background("Assets/fondoPrincipal.gif");
		setContentPane(fondo);
		fondo.setLayout(null);

		// Menú con pestaña
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Información adicional y comentarios");
        JMenuItem reseña = new JMenuItem("Reseñas");
        JMenuItem version = new JMenuItem("Versión");
        JMenuItem contacto = new JMenuItem("Contacto");
        JMenuItem creadores = new JMenuItem("Creadores");
        
        ActionListener mostrarVentana = e -> {
            String titulo = ((JMenuItem) e.getSource()).getText(); 
            String mensaje = switch (titulo) {
                case "Versión" -> "Versión 1.0 - Última actualización: Marzo 2025.";
                case "Contacto" -> "Para soporte, escríbenos a: luciaPascBusto@berryLands.com";
                default -> "Información no disponible.";
            };
            mostrarMensaje(titulo, mensaje);
        };
        
        //accion de los botones del menu superior
        reseña.addActionListener(e -> mostrarReseña());
        version.addActionListener(mostrarVentana);
        contacto.addActionListener(mostrarVentana);
        creadores.addActionListener(e -> mostrarPasaporte());
        
        //añadir los botones superiores
        menu1.add(reseña);
        menu1.add(version);
        menu1.add(contacto);
        menu1.add(creadores);
        menuBar.add(menu1);
        setJMenuBar(menuBar);
		
        
        
		// Distribución para botones
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(5, 1, 10, 10));
		menu.setBounds(920, 150, 365, 415);  //posicion mi pc
		//menu.setBounds(750, 110, 365, 415);  //posicion pc capgemnini
		//menu.setBounds(1200, 160, 365, 415); posicion pantalla grande
		menu.setOpaque(false);
		add(menu);


		BotonPersonalizado jugar = new BotonPersonalizado("Assets/1-removebg-preview.png",
				"Assets/1-removebg-preview (1).png");
		BotonPersonalizado reglas = new BotonPersonalizado("Assets/2-removebg-preview.png",
				"Assets/2-removebg-preview (1).png");
		BotonPersonalizado personajes = new BotonPersonalizado("Assets/3-removebg-preview.png",
				"Assets/3-removebg-preview (1).png");
		BotonPersonalizado guardar = new BotonPersonalizado("Assets/4-removebg-preview.png",
				"Assets/4-removebg-preview (1).png");
		BotonPersonalizado salir = new BotonPersonalizado("Assets/5-removebg-preview.png",
				"Assets/5-removebg-preview (1).png");

		jugar.addActionListener(e -> new PanelEquipos());
		reglas.addActionListener(e -> new PanelReglas());
		personajes.addActionListener(e -> new PanelPersonajes());
		salir.addActionListener(e -> System.exit(0));

		menu.add(jugar);
		menu.add(reglas);
		menu.add(personajes);
		menu.add(guardar);
		menu.add(salir);
		fondo.add(menu);

		setVisible(true);

	}

//	private JButton createButton(String text, String icon) {
//		JButton button = new JButton(text, new ImageIcon(icon));
//		// button.setHorizontalAlignment(SwingConstants.LEFT);
//		// button.setBackground(Color.WHITE);
//		button.setFocusPainted(false);
//		return button;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jugar) {
			irEquipos();
		}
		if (e.getSource() == reglas) {
			irReglas();
		}
		if (e.getSource() == personajes) {
			irPersonajes();
		}
		
		if (e.getSource() == salir) {
			System.exit(0);
		}
		if (e.getSource() == guardar) {
            guardarDatos();
        }

	}

	public void irReglas() {
		JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this); // le pasamos el objeto panel con un cast,
																		// queremos que el boton volver funcione
		marco.remove(this); // ponemos this ya q queremos q se borre el panel actual en ese momento
		marco.add(new PanelReglas()); // y justo despues queremos que se añada un panel nuevo
		marco.setVisible(true);
	}
	
	public void irPersonajes() {
		JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this); 
		marco.remove(this); 
		marco.add(new PanelPersonajes()); 
		marco.setVisible(true);
	}
	
	public void irEquipos() {
		JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this); 
		marco.remove(this); 
		marco.add(new PanelEquipos()); 
		marco.setVisible(true);
	}
	
	//muestra los apartados restantes
	private void mostrarMensaje(String titulo, String mensaje) {
	    JDialog dialog = new JDialog(this, titulo, true);
	    dialog.setSize(500, 300);
	    dialog.setLocationRelativeTo(this);

	    JPanel panel = new JPanel();
	    panel.setBackground(new Color(255, 200, 200)); // Rosa claro
	    panel.setLayout(new BorderLayout());

	    JLabel etiqueta = new JLabel("<html><center>" + mensaje + "</center></html>", JLabel.CENTER);
	    etiqueta.setFont(new Font("Arial", Font.PLAIN, 16));

	    JButton cerrarBoton = new JButton("Cerrar");
	    cerrarBoton.addActionListener(e -> dialog.dispose());

	    JPanel panelBoton = new JPanel();
	    panelBoton.setBackground(new Color(255, 200, 200));
	    panelBoton.add(cerrarBoton);

	    panel.add(etiqueta, BorderLayout.CENTER);
	    panel.add(panelBoton, BorderLayout.SOUTH);

	    dialog.add(panel);
	    dialog.setVisible(true);
	}
	
	//metodo para que muestre el apartado de creadores
	  public void mostrarPasaporte() {
	        JFrame datosFrame = new JFrame("Información de creador");
	        datosFrame.setSize(830, 600);
	        datosFrame.setLocationRelativeTo(this);

	        JPanel panelDatos = new JPanel(new GridBagLayout());
	        panelDatos.setBackground(new Color(255, 200, 200));
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(10, 10, 10, 10);
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        

	        // Imagen
	        ImageIcon pasaporte = new ImageIcon("Assets/pasaporte.png");
	        Image imagen = pasaporte.getImage().getScaledInstance(850, -1, Image.SCALE_SMOOTH);
	        JLabel logo = new JLabel(new ImageIcon(imagen));
	        panelDatos.add(logo, gbc);

	        // Etiqueta 
	        gbc.gridy ++;
	        JLabel etiqueta = new JLabel("Tarjeta de identificación de usuario de proyecto", SwingConstants.CENTER);
	        etiqueta.setFont(new Font("Verdana", Font.BOLD, 16));
	        panelDatos.add(etiqueta, gbc);

	        // Volver
	        gbc.gridy++;
	        gbc.anchor = GridBagConstraints.WEST;
	        gbc.gridx = 0;
	        gbc.insets = new Insets(10, 50, 10, 10); // Insets ajusta la posicion
	        JButton volverBoton = new JButton("Volver");
	        volverBoton.setPreferredSize(new Dimension(150, 40)); // Se le agrega la clase dimension para ajustar el ancho del boton
	        volverBoton.addActionListener(e -> datosFrame.dispose()); //al clicar el boton, hace que la ventana se cierre
	        panelDatos.add(volverBoton, gbc);

	        datosFrame.add(panelDatos);
	        datosFrame.setVisible(true);
	    }
	
    //creamos un metodo para que muestre solo la pestaña de reseñas
	private void mostrarReseña() {
	    JFrame datosFrame = new JFrame("Datos adicionales");
	    datosFrame.setSize(800, 700);
	    datosFrame.setLocationRelativeTo(this);

	    JPanel panelDatos = new JPanel(new GridBagLayout());
	    panelDatos.setBackground(new Color(255, 182, 193));

	    //titulo superior (tipo de texto)
	    Font customFont = new Font("Verdana", Font.BOLD, 18);
	    JLabel titulo = new JLabel("Comenta aquí tu opinión sobre el juego y si mejorarías algún aspecto:");
	    titulo.setFont(customFont);

	    //zona para escribir
	    JTextArea reseñaArea = new JTextArea("Escribe tu reseña aquí...", 8, 25);
	    reseñaArea.setLineWrap(true); //  hace que el texto se ajuste automáticamente a la siguiente línea cuando alcanza el borde
	    reseñaArea.setWrapStyleWord(true); // hace que el salto de línea ocurra en palabras completas en lugar de cortar palabras a la mitad

	    //crear boton + funcionalidad
	    JButton publicarBoton = new JButton("Publicar");
	    publicarBoton.setBackground(Color.getHSBColor(0.0f, 0.1f, 1.0f));
	    publicarBoton.addActionListener(e -> {
	        JOptionPane.showMessageDialog(datosFrame, "Tu comentario se ha publicado correctamente", 
	                                      "Comentario publicado", JOptionPane.INFORMATION_MESSAGE);
	    });
	    
	    GridBagConstraints gbc = new GridBagConstraints(); //tipo de layout
	    gbc.insets = new Insets(10, 10, 10, 10); //define los márgenes del objeto (arriba, izq, abajo, derecha)

	    // título en la parte superior
	    gbc.gridx = 0; // coloca el objeto en la primera columna 
	    gbc.gridy = 0; // lo mismo pero en la fila
	    gbc.gridwidth = 2; // cuantas columnas ocupa el objeto
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.anchor = GridBagConstraints.PAGE_START; //la constante indica la altura del texto
	    panelDatos.add(titulo, gbc);

	    // area de reseñas
	    gbc.gridy = 1;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 1;
	    gbc.weighty = 1; // Da más espacio al cuadro de texto
	    panelDatos.add(new JScrollPane(reseñaArea), gbc);

	    // boton publicar
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    gbc.weighty = 0;
	    gbc.anchor = GridBagConstraints.WEST; // Botón alineado a la izquierda
	    panelDatos.add(publicarBoton, gbc);

	    datosFrame.add(panelDatos);
	    datosFrame.setVisible(true);
	}
	
	public void guardarDatos() {
	    // Aquí, obtienes los datos que quieres guardar
	    String texto = campoTexto.getText(); // Si usas un JTextField
	    // String comentario = areaResultados.getText(); // Si usas un JTextArea

	    // Aquí estableces la conexión con la base de datos y ejecutas la consulta
	    try (Connection connection = DatabaseConnection.conectar()) {
	        String sql = "INSERT INTO comentarios (comentario) VALUES (?)"; // Asegúrate de que 'comentarios' sea el nombre de tu tabla
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, texto); // Asignamos el valor del campo de texto al parámetro de la consulta SQL

	        int filasAfectadas = statement.executeUpdate();
	        if (filasAfectadas > 0) {
	            // Si los datos se guardaron correctamente, mostramos un mensaje
	            JOptionPane.showMessageDialog(this, "¡Datos guardados exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Si no se guardaron correctamente, mostramos un mensaje de error
	            JOptionPane.showMessageDialog(this, "Error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        // Si ocurre un error en la conexión a la base de datos, mostramos un mensaje
	        JOptionPane.showMessageDialog(this, "Error de conexión a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
