package controlador_final;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Controlador principal del módulo de Productos.
 * Actúa como intermediario entre la Vista y el Modelo, implementando el patrón de arquitectura MVC.
 * Su función es desacoplar la lógica de negocio de la interfaz de usuario.
 * * @author Brian
 */
public class CtrlProducto implements ActionListener {

    private final Producto modelo;
    private final ConsultasProducto consultas;
    private final frmProducto2 vista;

    /**
     * Constructor del Controlador.
     * Inicializa los componentes y vincula los eventos de la interfaz (Listeners).
     * * @param modelo Objeto que representa la entidad del dominio (Datos).
     * @param consultas Objeto DAO para el acceso a la base de datos (Lógica).
     * @param vista Interfaz gráfica de usuario (GUI).
     */
    public CtrlProducto(Producto modelo, ConsultasProducto consultas, frmProducto2 vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;

        // Vinculación de botones con la lógica de este controlador
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

    /**
     * Configuración inicial de la ventana al arrancar la aplicación.
     */
    public void iniciar() {
        vista.setTitle("Gestión de Inventario - Productos");
        vista.setLocationRelativeTo(null); // Centrar ventana en pantalla
    }

    /**
     * Manejador central de eventos (Event Handler).
     * Determina qué acción ejecutar en función del botón presionado por el usuario.
     * * @param e Evento capturado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            guardarProducto();
        } else if (e.getSource() == vista.btnModificar) {
            modificarProducto();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarProducto();
        } else if (e.getSource() == vista.btnBuscar) {
            buscarProducto();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    // --- MÉTODOS DE LÓGICA DE NEGOCIO (Private Helpers) ---

    /**
     * Recopila datos de la vista, valida el formato y solicita al DAO la inserción.
     * Incluye manejo de excepciones para garantizar la estabilidad ante datos numéricos inválidos.
     */
    private void guardarProducto() {
        try {
            // Asignación de datos desde la Vista al Modelo
            modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));

            if (consultas.registrar(modelo)) {
                JOptionPane.showMessageDialog(null, "✅ Producto guardado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error al guardar el producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "⚠️ El precio y la cantidad deben ser números válidos.", "Error de formato", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Solicita la actualización de un registro existente.
     * Reutiliza la validación de tipos de datos.
     */
    private void modificarProducto() {
        try {
            modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));

            if (consultas.modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "✅ Registro modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error al modificar");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "⚠️ Verifica los datos numéricos.", "Error de formato", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Gestiona el borrado de registros.
     * Incluye una confirmación visual (Confirm Dialog) para mejorar la UX y evitar borrados accidentales.
     */
    private void eliminarProducto() {
        modelo.setCodigo(vista.txtCodigo.getText()); 
        
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este producto?");
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (consultas.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "🗑️ Registro eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error al eliminar");
            }
        }
    }

    /**
     * Busca un producto por código y rellena el formulario con los datos obtenidos.
     */
    private void buscarProducto() {
        modelo.setCodigo(vista.txtCodigo.getText());

        if (consultas.buscar(modelo)) {
            vista.txtCodigo.setText(modelo.getCodigo());
            vista.txtNombre.setText(modelo.getNombre());
            vista.txtPrecio.setText(String.valueOf(modelo.getPrecio()));
            vista.txtCantidad.setText(String.valueOf(modelo.getCantidad()));
        } else {
            JOptionPane.showMessageDialog(null, "🔍 No se encontró el registro");
            limpiar();
        }
    }

    /**
     * Restablece los campos del formulario a su estado inicial.
     */
    public void limpiar() {
        vista.txtCodigo.setText(null);
        vista.txtNombre.setText(null);
        vista.txtPrecio.setText(null);
        vista.txtCantidad.setText(null);
    }
}