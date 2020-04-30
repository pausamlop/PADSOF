package across.control.user.project;

import across.model.application.Application;
import across.gui.*;
import across.gui.general.PanelDisplayProject;

import java.awt.event.*; 

/**
 * Clase ControladorVotar
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorVotar implements ActionListener{

    private PanelDisplayProject panel;
    private MainFrame frame;
    private Application model;
    

    
    
    
    /**
     * Constructor de la clase ControladorVotar
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorVotar(MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getDisplayProject();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'votar' en el panel de visalizar proyecto
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        panel.getProject().vote(model.getCurrentUser());
        panel.update();
    }

}