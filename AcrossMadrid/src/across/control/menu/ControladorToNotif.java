package across.control.menu;

import java.awt.event.*;
import java.util.ArrayList;

import across.gui.MainFrame;
import across.model.application.Application;
import across.model.notification.Notification;

/**
 * Clase ControladorToNotif
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorToNotif implements ActionListener{

    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorToNotif
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorToNotif (MainFrame frame, Application model){
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
    	frame.getNotif().update();
        ArrayList<Notification> newNotif;
        if (Application.getApplication().getCurrentUser() != null)
            newNotif = Application.getApplication().getCurrentUser().getNotifications();
        else
            newNotif = Application.getApplication().getAdmin().getNotifications();
        frame.getNotif().setNotifications(newNotif);
        this.frame.showPanel("notif");
    }

}