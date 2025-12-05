package Modelo;

/**
 * Clase POJO (Plain Old Java Object) que representa la entidad Producto.
 * Mapea directamente la estructura de la tabla 'producto' de la base de datos.
 * Sirve como objeto de transporte de datos (DTO) entre las capas MVC.
 * @author Brian
 */
public class Producto {

    // Atributos privados (Encapsulamiento)
    private int id;
    private String codigo;
    private String nombre;
    private double precio; // Usamos 'double' primitivo para mayor eficiencia
    private int cantidad;

    /**
     * Constructor vacío necesario para instanciación genérica.
     */
    public Producto() {
    }

    /**
     * Constructor completo.
     * Útil para crear objetos cuando recuperamos datos de la base de datos.
     * @param id Identificador único (PK)
     * @param codigo Código de referencia
     * @param nombre Nombre del producto
     * @param precio Precio unitario
     * @param cantidad Stock disponible
     */
    public Producto(int id, String codigo, String nombre, double precio, int cantidad) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // --- GETTERS Y SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Representación en cadena del objeto.
     * Útil para depuración y logs.
     * @return String con el estado del objeto.
     */
    @Override
    public String toString() {
        return "Producto { " + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + " }";
    }
    
}