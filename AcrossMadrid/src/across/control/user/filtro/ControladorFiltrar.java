package across.control.user.filtro;

import across.model.application.Application;
import across.model.enumerations.typeSocial;
import across.model.project.Project;
import across.gui.*;
import across.gui.user.PanelInicioUser;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase ControladorFiltrar
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorFiltrar implements ActionListener{

    private PanelInicioUser panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorFiltrar
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorFiltrar (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getInicioUser();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'filtrar proyecti' en el panel de inicio del usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        ArrayList<Project> proyectos;

        /* filtro por estado */
        if (panel.getTipoEstado().equals("Estado"))
            proyectos = model.filterProject(panel.getProjectState());

        /* filtro por ambito (proyecto social) */
        else if(panel.getTipoEstado().equals("Social")){
            if (panel.getAmbito().equals("Todos"))
                proyectos = model.filterSocialProject("");
            else if (panel.getAmbito().equals("Nacional"))
                proyectos = model.filterSocialProject(typeSocial.NACIONAL);
            else
                proyectos = model.filterSocialProject(typeSocial.INTERNACIONAL);
        }

        /* filtro por distrito (proyecto de infraestructura) */
        else{
            if (panel.getDistrito().equals("Todos"))
                proyectos = model.filterInfrProject("");    
            else
                proyectos = model.filterInfrProject(panel.getDistrito());
        }

        panel.updateData(proyectos, model.getCollectives());
    }
}