package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import across.gui.MainFrame;
import across.gui.admin.PanelAdminConfig;
import across.model.application.Application;

/**
 * Clase ControladorAdminProyectos
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorAdminProyectos implements ActionListener{
	private PanelAdminConfig adminConfig;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorAdminProyectos (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.adminConfig = frame.getAdminConfig();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'usuarios' de la pantalla principal del Admin
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	frame.getInicioAdmin().setProyectosButton();
        this.frame.showPanel("inicioAdmin");
    }
}
