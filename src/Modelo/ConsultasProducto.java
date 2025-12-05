package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) para la entidad Producto.
 * Esta clase encapsula toda la lógica de interacción con la base de datos,
 * implementando las operaciones CRUD (Create, Read, Update, Delete).
 * * Implementa medidas de seguridad como PreparedStatements para evitar Inyección SQL
 * y gestión eficiente de recursos mediante bloques Try-with-resources.
 * * @author Brian
 */
public class ConsultasProducto extends Conexion {

    /**
     * Inserta un nuevo registro de producto en la base de datos.
     * * @param pro Objeto Producto con la información a persistir.
     * @return true si la inserción fue exitosa, false si ocurrió un error SQL.
     */
    public boolean registrar(Producto pro) {
        String sql = "INSERT INTO producto (codigo, nombre, precio, cantidad) VALUES (?, ?, ?, ?)";
        
        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error crítico al registrar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Actualiza la información de un producto existente.
     * La búsqueda del registro a modificar se realiza mediante su código único.
     * * @param pro Objeto Producto con los datos actualizados.
     * @return true si la actualización se realizó correctamente.
     */
    public boolean modificar(Producto pro) {
        String sql = "UPDATE producto SET nombre=?, precio=?, cantidad=? WHERE codigo=?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pro.getNombre());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getCantidad());
            ps.setString(4, pro.getCodigo());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error crítico al modificar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un producto de la base de datos basándose en su código.
     * * @param pro Objeto Producto que contiene el código del elemento a borrar.
     * @return true si el borrado fue exitoso.
     */
    public boolean eliminar(Producto pro) {
        String sql = "DELETE FROM producto WHERE codigo=?";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pro.getCodigo());
            
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error crítico al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca un producto por su código y mapea los resultados de la base de datos
     * al objeto Producto proporcionado.
     * * @param pro Objeto Producto que contiene el código a buscar. Se rellenará con el resto de datos si se encuentra.
     * @return true si se encontró el registro, false si no existe.
     */
    public boolean buscar(Producto pro) {
        String sql = "SELECT * FROM producto WHERE codigo=?";
        ResultSet rs = null;

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Mapeo Relacional-Objeto (ORM manual)
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));
                pro.setCantidad(rs.getInt("cantidad"));
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error crítico al buscar producto: " + e.getMessage());
            return false;
        } 
    }
}