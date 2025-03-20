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

    PanelEquipos() {
        setTitle("Ataque");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);

        Background fondo3 = new Background("Assets/fondoEquipos.gif"); //CAMBIAR FONDO SE VE MAL
        setContentPane(fondo3);
        fondo3.setLayout(null);

        // se crean areas de texto, combo boxes y botones para los nombres de los 5 equipos
        String[] especies = {"Perro", "Gato", "Oso", "Mono", "Cabra", 
                             "Ave", "Conejo", "Lobo", "Pulpo", "Ardilla"};

        for (int i = 0; i < 5; i++) {
            nombreEquipos[i] = crearAreaTexto();
            JScrollPane scrollPane = new JScrollPane(nombreEquipos[i]);
            scrollPane.setBackground(Color.PINK);
            scrollPane.setOpaque(true);
            scrollPane.setBounds(200, 180 + (i * 110), 245, 50); //arriba, abajo, ancho, alto y margen
            fondo3.add(scrollPane);

            especiesCajas[i] = new JComboBox<>(especies);
            especiesCajas[i].setBounds(620, 180 + (i * 110), 200, 30);
            fondo3.add(especiesCajas[i]);

            asignarBotones[i] = new JButton("Asignar");
            asignarBotones[i].setBounds(840, 180 + (i * 110), 100, 30);
            asignarBotones[i].setBorderPainted(isFocusable());
            asignarBotones[i].setBackground(Color.PINK);
            asignarBotones[i].addActionListener(this);
            fondo3.add(asignarBotones[i]);
        }
        
        //texto que indica el nº de equipo
        JLabel textoReglas = new JLabel("<html><div style = 'text-align:center; font-size:15px;'>"
				+ "EQUIPO 1</div></html>");
		textoReglas.setBounds(0, 500, 500, 200);
		textoReglas.setHorizontalAlignment(SwingConstants.CENTER);
		fondo3.add(textoReglas);

        // Botón siguiente
        siguiente = new BotonPersonalizado("Assets/flecha2.png", "Assets/flechaRosa2.png");
        siguiente.setBounds(1000, 40, 200, 120);
        siguiente.addActionListener(e -> new PanelAtaque());
        fondo3.add(siguiente);

        setVisible(true);
    }

    //metodo que especifica los detalles del area de texto
    private JTextArea crearAreaTexto() {
        JTextArea areaTexto = new JTextArea();
        Border border = new RoundBorder(Color.GRAY, 10);
        areaTexto.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return areaTexto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 5; i++) {
            if (e.getSource() == asignarBotones[i]) {
                String equipo = nombreEquipos[i].getText();
                String especie = (String) especiesCajas[i].getSelectedItem();
                guardarEquipoEnBaseDeDatos(equipo, especie); //cuando pulsamos asignar, se guardan los datos ingresados en la base d datos
            }
        }

        if (e.getSource() == siguiente) {
            JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);
            marco.remove(this);
            marco.add(new PanelAtaque());
            marco.setVisible(true);
        }
    }

    //metodo que almacena los datos de la interfaz a la base
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
}
