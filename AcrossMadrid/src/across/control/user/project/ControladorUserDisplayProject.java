package across.control.user.project;

import across.model.application.Application;
import across.model.project.Project;
import across.gui.*;
import across.gui.user.PanelInicioUser;
import across.gui.user.tablas.TablaProyectos;

import javax.swing.JTable;
import javax.swing.event.*;

/**
 * Clase ControladorUserDisplayProject
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUserDisplayProject implements ListSelectionListener{

    private PanelInicioUser inicioUser;
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
        this.inicioUser = frame.getInicioUser();
    }

    /**
     * Accion que se realiza cuando se hace click sobre un colectivo en una tabla
     * 
     * @param e accion recibida
     */
	@Override
	public void valueChanged(ListSelectionEvent e) {

        if (e.getValueIsAdjusting()) return;

        JTable t;
        if (frame.getPerfil().isVisible())
            t = frame.getPerfil().getTableP();
        else if (frame.getInicioUser().isVisible())
            t = frame.getInicioUser().getTableP();
        else return;

        int rows = t.getSelectedRow();
        if (rows == -1) return;

        Project p = ((TablaProyectos)t.getModel()).getProjects().get(rows);
        this.frame.getDisplayProject().setProject(p);
        this.frame.showPanel("displayProject");
        
    }
}