package across.control.user.project;

import across.model.application.Application;
import across.model.user.*;
import across.gui.*;
import across.gui.admin.PanelAdminUsuarios;
import across.gui.general.PanelDisplayProject;

import java.awt.event.*; 
import java.util.*;

/**
 * Clase ControladorValidar
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorValidar implements ActionListener{

    private PanelDisplayProject panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorValidar
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorValidar (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getDisplayProject();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'seguir' en el panel de visalizar proyecto
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        panel.getProject().validate();

        Map<String, Boolean> changesUsuarios = frame.getAdminUsuarios().getApplicationUpdate();
		for(User aux: model.getNonValidatedUsers()) {
			String uname = aux.getUsername();
			
			if(changesUsuarios.containsKey(uname)) {
				if(changesUsuarios.get(uname)) {
					aux.validate();
				}
			}
		}
		
		frame.updateAdminUsuarios(new PanelAdminUsuarios());
		this.frame.getAdminUsuarios().updateTable(model.getNonValidatedUsers(), model.getUsers());

        panel.update();
    }
}