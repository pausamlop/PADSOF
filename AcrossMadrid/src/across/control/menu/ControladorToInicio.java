package across.control.menu;

import java.awt.event.*;
import java.util.ArrayList;

import across.gui.MainFrame;
import across.gui.user.PanelInicioUser;
import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;

/**
 * Clase ControladorToInicio
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorToInicio implements ActionListener{

    private PanelInicioUser panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorToInicio
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorToInicio (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getInicioUser();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'inicio'
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (model.getCurrentUser() != null){
            ArrayList<Project> proyectos = model.getProjects();
            ArrayList<Collective> colectivos = model.getCollectives();
            panel.updateData(proyectos, colectivos);
            this.frame.showPanel("inicioUser");
        }
        else
            this.frame.showPanel("inicioAdmin");
    }

}