package across.control.user.informes;

import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;
import across.model.user.User;
import across.gui.*;
import across.gui.user.PanelInformes;

import java.awt.event.*;
import java.util.*;

/**
 * Clase ControladorToInformes
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorToInformes implements ActionListener{

    private PanelInformes panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorToInformes
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorToInformes (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getInformes();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'solicitar informe' en el panel inicial del usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        User user = model.getCurrentUser();
        if (user == null) return;
        
        ArrayList<Project> proyectos = model.popularityReport();
        Map<Collective,Double> colectivos = new HashMap<Collective,Double>();
        panel.setMisColectivos(user.getMemberCollectives());
        panel.updateProjects(proyectos);
        panel.updateCollectives(colectivos);
        panel.empty();
        this.frame.showPanel("informes");
    }
}