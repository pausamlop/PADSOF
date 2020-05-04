package across.control.user.filtro;

import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;
import across.gui.*;
import across.gui.user.PanelInicioUser;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * Clase ControladorLimpiarFiltro
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorLimpiarFiltro implements ActionListener{

    private PanelInicioUser panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorLimpiarFiltro
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorLimpiarFiltro (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getInicioUser();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'limpiar filtros/busqueda' en el panel de inicio del usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        ArrayList<Project> proyectos = model.searchProject(panel.getBusqueda());
        ArrayList<Collective> colectivos = model.searchCollective(panel.getBusqueda());

        panel.updateData(proyectos, colectivos);
    }
}