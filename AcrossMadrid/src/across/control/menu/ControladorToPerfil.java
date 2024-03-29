package across.control.menu;

import java.awt.event.*;
import java.util.ArrayList;

import across.gui.MainFrame;
import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;

/**
 * Clase ControladorToPerfil
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorToPerfil implements ActionListener{

    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorToPerfil
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorToPerfil (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'perfil'
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	ArrayList<Project> pc = model.getCurrentUser().getCreatedProjects();
    	ArrayList<Project> pa = model.getCurrentUser().getVotedProjects();
    	ArrayList<Project> ps = model.getCurrentUser().getFollowedProjects();
    	ArrayList<Collective> cm = model.getCurrentUser().getMemberCollectives();
    	ArrayList<Collective> cc = model.getCurrentUser().getCreatedCollectives();
   

		frame.getPerfil().updateData(pc,pa,ps,cm,cc);
        this.frame.showPanel("perfil");
    }

}