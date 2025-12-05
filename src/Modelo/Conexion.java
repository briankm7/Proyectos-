package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de infraestructura para gestionar la conexión a la Base de Datos.
 * Centraliza la configuración de acceso y utiliza el driver JDBC para MySQL.
 * * NOTA DE SEGURIDAD:
 * Las credenciales aquí mostradas son placeholders. En un entorno de producción,
 * estas deben ser cargadas desde variables de entorno o un archivo de configuración externo (.properties).
 * * @author Brian
 */
public class Conexion {

    // --- PARÁMETROS DE CONFIGURACIÓN ---
    private final String BASE = "tienda";
    // URL configurada con parámetros para evitar errores de SSL y Zona Horaria en drivers modernos
    private final String URL = "jdbc:mysql://localhost:3306/" + BASE + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String USER = "root";
    
    // IMPORTANTE: Cambiar por la contraseña local antes de ejecutar.
    private final String PASSWORD = "TU_CONTRASEÑA_AQUI"; 

    /**
     * Establece y devuelve una conexión activa a la base de datos.
     * @return Objeto Connection si hay éxito, null si falla.
     */
    public Connection getConexion() {
        Connection con = null;
        
        try {
            // Carga dinámica del driver MySQL (cj.jdbc.Driver)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intento de conexión
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error Crítico: Driver JDBC no encontrado. Verifica la librería del proyecto.");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            System.err.println("Error de Conexión: No se pudo acceder a la base de datos.");
            System.err.println("Detalle: " + e.getMessage());
        }
        
        return con;
    }
}