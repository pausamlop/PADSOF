package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import across.gui.MainFrame;
import across.gui.admin.PanelAdminConfig;
import across.model.application.Application;

/**
 * Clase ControladorAdminConfigCaducidad
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorAdminConfigCaducidad implements ActionListener{
	private PanelAdminConfig adminConfig;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorAdminConfigCaducidad(MainFrame frame, Application model){
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
    	
    	Integer newMinVotes = Integer.parseInt(adminConfig.getCaducidad().trim());
    	
    	Application.getApplication().setDaysExpiration(newMinVotes);
    	
    	//frame.setPanelAdminConfig(new PanelAdminConfig());
    	
    	frame.getAdminConfig().setConfigButton();
        this.frame.showPanel("adminConfig");
    }
}
