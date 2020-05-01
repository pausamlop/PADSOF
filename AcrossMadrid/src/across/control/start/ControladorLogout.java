package across.control.start;

import across.model.application.Application;
import across.gui.*;
import across.gui.start.PanelInicio;

import java.awt.event.*;

/**
 * Clase ControladorLogout
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorLogout implements ActionListener{

    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorLogout
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorLogout (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'logout'
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("inicio");
    }
}