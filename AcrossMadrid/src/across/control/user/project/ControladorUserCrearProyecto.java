package across.control.user.project;

import across.model.application.Application;
import across.gui.*;
import java.awt.event.*;

/**
 * Clase ControladorUserCrearProyecto
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUserCrearProyecto implements ActionListener{

    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorUserCrearProyecto (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'crear proyecto' en el panel inicial del usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("nuevoProyecto");
        this.frame.getNewProject().setColectivos(Application.getApplication()
                                                .getCurrentUser().getCreatedCollectives());
    }
}