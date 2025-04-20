package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Guardar todos los atributos en tabla ->  equipos: id, vida, tipo, nombre, id_partida        (entidad relacion = 1:N)
 * 								  		    partida: id, rondas (fecha de guardado)
 * 
 * Por ejemplo si en una partida hay 4 equipos, se le asociará la id de partida con la id de la tabla de partida
 * 
 * Triggers: compactan varias funciones en un bloque con delimitadores (con ; se acaba cualquier sentencia y si queremos que se ejecute todo y no solo linea a linea
 * de manera independiente, ponemos un delimitador DELIMITER //
 * 												   {BEFORE | AFTER} {INSERT | UPDATE | DELETE} cuando escribamos el trigger elegimos una de cada llave
 * 													... BEGIN
 * 														-----codigo--------
 * 														END;
 * 												   // 
 * 												   DELIMITER ; -> restauramos el ; para el cierre
 */

public class DatabaseConnection {
    
	private static final String URL = "jdbc:mysql://localhost:3306/berrylands"; // Nombre de la base de datos
    private static final String USER = "root";  // Usuario por defecto en XAMPP
    private static final String PASSWORD = "";  // Dejar vacío si no configuraste una contraseña

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }
        return conexion;
    }
}


/*
 * Fonsi
 */

//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBController {
//    private static final String URL = "jdbc:mysql://localhost:3306/eclipse_db";
//    private static final String USER = "root";
//    private static final String PASSWORD = ""; // o "root", según configuración de XAMPP
//
//    private Connection conexion;
//
//    public DBController() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Conexión exitosa a la base de datos");
//        } catch (Exception e) {
//            System.err.println("Error de conexión: " + e.getMessage());
//        }
//    }
//
//    // CREATE
//    public void insertarUsuario(String nombre, String email) { // le paso los equipos y el numero de ronda, se cambia a insertarPartida
//        String sql = "INSERT INTO equipos (nombre, tipo, vidas) VALUES (?, ?)"; -> es lo mismo que (" + nombre + "," + email +")
//	      String sql = "INSERT INTO partida (rondas) VALUES (?, ?)";
//
//        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
//            stmt.setString(1, nombre);
//            stmt.setString(2, email);
//            stmt.executeUpdate();
//            System.out.println("Usuario insertado correctamente.");
//        } catch (SQLException e) {
//            System.err.println("Error al insertar: " + e.getMessage());
//        }
//    }
//
//    // READ
//    public List<String> obtenerUsuarios() {
//        List<String> lista = new ArrayList<>();
//        String sql = "SELECT * FROM usuarios";
//
//        try (Statement stmt = conexion.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nombre = rs.getString("nombre");
//                String email = rs.getString("email");
//                lista.add("ID: " + id + ", Nombre: " + nombre + ", Email: " + email);
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error al obtener usuarios: " + e.getMessage());
//        }
//
//        return lista;
//    }
//
//    // UPDATE
//    public void actualizarUsuario(int id, String nuevoNombre, String nuevoEmail) {
//        String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
//
//        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
//            stmt.setString(1, nuevoNombre);
//            stmt.setString(2, nuevoEmail);
//            stmt.setInt(3, id);
//
//            int filas = stmt.executeUpdate();
//            if (filas > 0) {
//                System.out.println("Usuario actualizado correctamente.");
//            } else {
//                System.out.println("No se encontró el usuario con ID: " + id);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error al actualizar usuario: " + e.getMessage());
//        }
//    }
//
//    // DELETE
//    public void eliminarUsuario(int id) {
//        String sql = "DELETE FROM usuarios WHERE id = ?";
//
//        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//
//            int filas = stmt.executeUpdate();
//            if (filas > 0) {
//                System.out.println("Usuario eliminado correctamente.");
//            } else {
//                System.out.println("No se encontró el usuario con ID: " + id);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error al eliminar usuario: " + e.getMessage());
//        }
//    }
//
//    // CERRAR CONEXIÓN
//    public void cerrarConexion() {
//        try {
//            if (conexion != null && !conexion.isClosed()) {
//                conexion.close();
//                System.out.println("Conexión cerrada.");
//            }
//        } catch (SQLException e) {
//            System.err.println("Error al cerrar conexión: " + e.getMessage());
//        }
//    }
//}
