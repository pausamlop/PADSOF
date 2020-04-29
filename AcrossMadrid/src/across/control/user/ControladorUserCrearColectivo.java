package across.control.user;

import across.model.application.Application;
import across.model.user.Collective;
import across.gui.*;
import across.gui.user.PanelInicioUser;

import java.awt.event.*;
import java.util.ArrayList;

import java.util.*;

/**
 * Clase ControladorUserCrearProyecto
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUserCrearColectivo implements ActionListener{

    private PanelInicioUser inicioUser;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorUserCrearColectivo (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.inicioUser = frame.getInicioUser();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'crear proyecto' en el panel inicial del usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("nuevoColectivo");
        
        
        
        ArrayList<Collective> output = new ArrayList<Collective>();
        		
        for (Collective aux: Application.getApplication().getCollectives()) {
        	if (!output.contains(aux)) output.add(aux);
        	
        }
        
        this.frame.getNewCollective().setColectivos(output);

    }
}