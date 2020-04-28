package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import across.gui.MainFrame;
import across.gui.admin.PanelAdminConfig;
import across.gui.admin.PanelInicioAdmin;
import across.model.application.Application;

public class ControladorAdminConfigVotes implements ActionListener{
	private PanelAdminConfig adminConfig;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorAdminConfigVotes(MainFrame frame, Application model){
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
    	
    	Integer newMinVotes = Integer.parseInt(adminConfig.getMinVotos().trim());
    	
    	Application.getApplication().setMinVotes(newMinVotes);
    	
    	//frame.setPanelAdminConfig(new PanelAdminConfig());
    	
    	//frame.getAdminConfig().setConfigButton();
        //this.frame.showPanel("adminConfig");
    }
}
