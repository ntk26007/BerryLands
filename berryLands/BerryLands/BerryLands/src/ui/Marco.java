package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import baseDeDatos.DatabaseConnection;

import java.awt.Color;
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
        JMenu menu1 = new JMenu("Archivos");
        JMenuItem menuItem = new JMenuItem("Reseñas");
        JMenuItem menuItem1 = new JMenuItem("Versión");
        JMenuItem menuItem2 = new JMenuItem("Contacto");
        JMenuItem menuItem3 = new JMenuItem("Creadores");
        
        //accion de los botones del menu superior
        menuItem.addActionListener(e -> mostrarDatos());
        
        //añadir los botones superiores
        menu1.add(menuItem);
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menuBar.add(menu1);
        setJMenuBar(menuBar);
		
		// logo berry
//		ImageIcon logoIcon = new ImageIcon("Assets/BerryIcon.png");
//		Image logoImage = logoIcon.getImage();
//
//		int originalWidth = logoImage.getWidth(null); // Obtener las dimensiones originales de la imagen
//		int originalHeight = logoImage.getHeight(null);
//
//		int desiredWidth = 450; // Establecer el nuevo tamaño deseado (cambiar valor para agrender o minimizar)
//		int desiredHeight = -1; // Si no se cambia el alto, se deja -1
//
//		if (desiredWidth > 0 && desiredHeight == -1) { // Calcular el nuevo tamaño manteniendo la proporción
//			desiredHeight = (originalHeight * desiredWidth) / originalWidth;
//		} else if (desiredHeight > 0 && desiredWidth == -1) {
//			desiredWidth = (originalWidth * desiredHeight) / originalHeight;
//		} else if (desiredWidth > 0 && desiredHeight > 0) {
//			// Si ambos valores son proporcionados, se usa el tamaño especificado y no se
//			// mantiene la proporción
//		}
//
//		Image logoScaled = logoImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
//		JLabel logo = new JLabel(new ImageIcon(logoScaled));
//
//		logo.setBounds(150, 300, desiredWidth, desiredHeight); // Ajusta la posición y tamaño del JLabel
//		fondo.add(logo);

		// Distribución para botones
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(5, 1, 10, 10));
		menu.setBounds(755, 130, 365, 415);
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
            buscarDatos();
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
	
	//conexion a base de datos
    private void buscarDatos() {
        String texto = campoTexto.getText();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM tu_tabla WHERE columna LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + texto + "%");
            ResultSet rs = stmt.executeQuery();

            areaResultados.setText("");
            while (rs.next()) {
                areaResultados.append(rs.getString("columna") + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void mostrarDatos() {
        JFrame datosFrame = new JFrame("Datos");
        datosFrame.setSize(500, 400);
        datosFrame.setLocationRelativeTo(this);

        JPanel panelDatos = new JPanel(new GridBagLayout());
        panelDatos.setBackground(Color.PINK);

        Font customFont = new Font("Arial", Font.ITALIC, 12);

        JTextArea reseñaArea = new JTextArea("Escribe tu reseña aquí...", 10, 30);
        reseñaArea.setLineWrap(true);
        reseñaArea.setWrapStyleWord(true);
        reseñaArea.setFont(customFont);

        JButton publicarButton = new JButton("Publicar");
        publicarButton.setFont(customFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        panelDatos.add(new JScrollPane(reseñaArea), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        panelDatos.add(publicarButton, gbc);

        datosFrame.add(panelDatos);
        datosFrame.setVisible(true);
    }

}
