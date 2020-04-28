package across.control.user.menu;

import java.awt.event.*;

import across.gui.MainFrame;
import across.model.application.Application;

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
        this.frame.showPanel("perfil");
    }

}