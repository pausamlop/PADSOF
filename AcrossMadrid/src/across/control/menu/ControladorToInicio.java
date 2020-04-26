package across.control.menu;

import java.awt.event.*;

import across.gui.MainFrame;
import across.model.application.Application;

/**
 * Clase ControladorToInicio
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorToInicio implements ActionListener{

    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorToInicio
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorToInicio (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'inicio'
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("inicioUser");
    }

}