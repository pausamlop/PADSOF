package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import across.gui.MainFrame;
import across.model.application.Application;

/**
 * Clase ControladorAdminConfig
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorAdminConfig implements ActionListener{
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorAdminConfig
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorAdminConfig (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'configuracion' de la pantalla principal del Admin
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	frame.getAdminConfig().setConfigButton();
        this.frame.showPanel("adminConfig");
    }
}

