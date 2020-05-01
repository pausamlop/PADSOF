package across.control.start;

import across.model.application.Application;
import across.gui.*;
import across.gui.start.PanelLogin;

import java.awt.event.*;
import javax.swing.*;

/**
 * Clase ControladorLogin
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorLogin implements ActionListener{

    private PanelLogin login;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorLogin
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorLogin (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.login = frame.getLogin();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'continuar' en el panel de iniciar sesion
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){

        String username = login.getUsername().trim();
        String password = login.getPassword();

        if (login.isAdmin()){
            if (password.equals(""))
                JOptionPane.showMessageDialog(frame, "Introduzca contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
            else if (model.getAdmin().login(password)){
                login.emptyFields();
                this.frame.showPanel("inicioAdmin");

                int numNotif = Application.getApplication().getAdmin().getNotifications().size();
                if (numNotif > 0)
                    JOptionPane.showMessageDialog(frame, "Tiene " + numNotif + " notificacione pendientes", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(frame, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if (username.equals(""))
                JOptionPane.showMessageDialog(frame, "Introduzca nombre de usuario válido", "Aviso", JOptionPane.WARNING_MESSAGE);
            else if (password.equals(""))
                JOptionPane.showMessageDialog(frame, "Introduzca contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
            
            else if (model.login(username, password)){
            	if(model.getUserByName(username) != null && model.getUserByName(username).getBlocked()) {
                	JOptionPane.showMessageDialog(frame, "Usuario bloqueado:\n"+model.getUserByName(username).getBlockedMssg(), "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                login.emptyFields();
                this.frame.showPanel("inicioUser");

                int numNotif = Application.getApplication().getCurrentUser().getNotifications().size();
                if (numNotif > 0)
                    JOptionPane.showMessageDialog(frame, "Tiene " + numNotif + " notificacione pendientes", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}