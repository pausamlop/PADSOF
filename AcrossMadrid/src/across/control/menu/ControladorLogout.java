package across.control.menu;

import across.model.application.Application;
import across.model.project.Project;
import across.model.user.User;
import across.gui.*;

import java.awt.event.*;
import java.util.Map;

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
        if(model.getCurrentAdmin()) {
    		Map<String, Boolean> changes = frame.getInicioAdmin().getApplicationUpdate();
    		for(Project aux: model.getNonValidatedProjects()) {
    			String pname = aux.getName();
    			
    			if(changes.containsKey(pname)) {
    				if(changes.get(pname)) {
    					aux.validate();
    				}else {
    					aux.reject();
    				}
    			}
    		}
    		Map<String, Boolean> changesUsuarios = frame.getAdminUsuarios().getApplicationUpdate();
    		for(User aux: model.getNonValidatedUsers()) {
    			String uname = aux.getUsername();
    			
    			if(changesUsuarios.containsKey(uname)) {
    				if(changesUsuarios.get(uname)) {
    					aux.validate();
    				}
    			}
    		}
    		frame.getInicioAdmin().setProyectosButton();
    		model.setCurrentAdmin(false);
    	}
        model.logout();
        this.frame.showPanel("inicio");
    }
}