package across.control.user.project;

import across.model.application.Application;
import across.model.project.Project;
import across.model.user.User;
import across.gui.*;
import across.gui.admin.PanelAdminUsuarios;

import javax.swing.JTable;
import javax.swing.event.*;
import java.util.*;

/**
 * Clase ControladorUserDisplayProject
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUserDisplayProject implements ListSelectionListener{

    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserDisplayProject
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorUserDisplayProject (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se hace click sobre un colectivo en una tabla
     * 
     * @param e accion recibida
     */
	@Override
	public void valueChanged(ListSelectionEvent e) {

        if (e.getValueIsAdjusting()) return;

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

        JTable t;
        if (frame.getPerfil().isVisible())
            t = frame.getPerfil().getTableP();
        else if (frame.getInicioUser().isVisible())
            t = frame.getInicioUser().getTableP();
        else if (frame.getInicioAdmin().isVisible())
            t = frame.getInicioAdmin().getTableP();
        else return;

        int rows = t.getSelectedRow();
        if (rows == -1) return;

        Project p;
        if (frame.getInicioAdmin().isVisible())
            p = ((across.gui.admin.TablaProyectos)t.getModel()).getProjects().get(rows);
        else
            p = ((across.gui.user.tablas.TablaProyectos)t.getModel()).getProjects().get(rows);
        this.frame.getDisplayProject().setProject(p);
        this.frame.showPanel("displayProject");
        
    }
}