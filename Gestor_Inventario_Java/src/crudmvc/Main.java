package crudmvc; 

import controlador_final.CtrlProducto;
import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto2;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Punto de entrada (Entry Point) de la aplicación de Gestión de Inventario.
 * Su responsabilidad es configurar el entorno visual y ensamblar las piezas
 * del patrón de arquitectura MVC (Modelo-Vista-Controlador).
 * * @author Brian
 */
public class Main {

    public static void main(String[] args) {

        // 1. Configuración del Look & Feel
        // Intenta adaptar la interfaz gráfica al sistema operativo del usuario (Windows/Linux/Mac)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Advertencia: No se pudo cargar el tema nativo. Se usará el tema por defecto de Java.");
        }

        // --- INYECCIÓN DE DEPENDENCIAS (Ensamblaje del MVC) ---

        // 2. Instancia del MODELO (Entidad y DAO)
        Producto modelo = new Producto();
        ConsultasProducto consultas = new ConsultasProducto();

        // 3. Instancia de la VISTA (Interfaz Gráfica)
        frmProducto2 vista = new frmProducto2();

        // 4. Instancia del CONTROLADOR (Orquestador)
        // Se inyectan el modelo y la vista en el controlador para establecer la comunicación
        CtrlProducto controlador = new CtrlProducto(modelo, consultas, vista);

        // 5. Inicio del ciclo de vida de la aplicación
        controlador.iniciar();
        vista.setVisible(true);
    }
}
