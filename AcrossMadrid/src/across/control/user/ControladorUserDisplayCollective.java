package across.control.user;

import across.model.application.Application;
import across.gui.*;
import across.gui.user.PanelInicioUser;

import java.awt.event.*;

/**
 * Clase ControladorUserDisplayProject
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUserDisplayCollective implements ActionListener {
	
    private PanelInicioUser inicioUser;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorUserDisplayCollective(MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.inicioUser = frame.getInicioUser();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'crear proyecto' en el panel inicial del usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("displayCollective");
        this.frame.getDisplayCollective().setCollective(inicioUser.getCollective());
        
    }

}
