package across.control.start;

import across.model.application.Application;
import across.gui.*;
import across.gui.start.PanelInicio;

import java.awt.event.*;

/**
 * Clase ControladorInicioLogin
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorInicioLogin implements ActionListener{

    private PanelInicio inicio;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorInicioLogin
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorInicioLogin (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.inicio = frame.getInicio();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'iniciar sesion' en el panel de inicio
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("login");
    }
}