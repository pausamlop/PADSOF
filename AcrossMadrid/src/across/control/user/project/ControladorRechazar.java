package across.control.user.project;

import across.model.application.Application;
import across.model.project.Project;
import across.gui.*;
import across.gui.admin.*;
import across.gui.general.PanelDisplayProject;

import java.awt.event.*; 
import java.util.*;

/**
 * Clase ControladorRechazar
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorRechazar implements ActionListener{

    private PanelDisplayProject panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorRechazar
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorRechazar (MainFrame frame, Application model){
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
        panel.getProject().reject();

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
		
		frame.updateInicioAdmin(new PanelInicioAdmin());
		ArrayList<Project> allP = new ArrayList<>();
        allP.addAll(model.getProjects());
        allP.addAll(model.getRejectedProjects());
        allP.addAll(model.getNonValidatedProjects());
        this.frame.getInicioAdmin().updateTablaProyectos(allP);

        panel.update();
    }
}